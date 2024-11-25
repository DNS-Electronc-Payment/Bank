package com.project.services;

import com.project.clients.APIClient;
import com.project.enums.TransactionStatus;
import com.project.models.*;
import com.project.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private PaymentRequestService paymentRequestService;
    @Autowired
    private BankRequestService bankRequestService;
    @Autowired
    private APIClient apiClient;

    public BankAccountService(BankAccountRepository bankAccountRepository, PaymentRequestService paymentRequestService, BankRequestService bankRequestService, APIClient apiClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.paymentRequestService = paymentRequestService;
        this.bankRequestService = bankRequestService;
        this.apiClient = apiClient;
    }

    public void processCardData(BankAccount account) {
        if(!isValidCardNumber(account.getCardPAN())) {
            return;
        }

        BankAccount acquirerAccount = bankAccountRepository.findByCustomerId(account.getCustomerId());
        List<PaymentRequest> paymentRequests = paymentRequestService.getPaymentRequest(acquirerAccount.getCustomerId());
        PaymentRequest paymentRequest = paymentRequests.getFirst();
        BankAccount issuerAccount = bankAccountRepository.findByCustomerId(paymentRequest.getMerchantId());

        if(acquirerAccount == null || issuerAccount == null || paymentRequest == null) {
            String acquirerOrderId = UUID.randomUUID().toString();
            String acquirerTimestamp = UUID.randomUUID().toString();
            BankResponse bankResponse = new BankResponse(TransactionStatus.ERROR, paymentRequest.getMerchantOrderId(), acquirerOrderId, acquirerTimestamp, paymentRequest.getPaymentId());
            apiClient.sendBankResponse(bankResponse);
            return;
        }

        if(acquirerAccount.getBankId() == issuerAccount.getBankId()) {
            double state = acquirerAccount.getCurrentState() - paymentRequest.getAmount();
            if(state < 0.0) {
                String acquirerOrderId = UUID.randomUUID().toString();
                String acquirerTimestamp = UUID.randomUUID().toString();
                BankResponse bankResponse = new BankResponse(TransactionStatus.FAIL, paymentRequest.getMerchantOrderId(), acquirerOrderId, acquirerTimestamp, paymentRequest.getPaymentId());
                apiClient.sendBankResponse(bankResponse);
            }
            else {
                bankAccountRepository.updateCurrentState(acquirerAccount.getAccountId(), state);
                String acquirerOrderId = UUID.randomUUID().toString();
                String acquirerTimestamp = UUID.randomUUID().toString();
                BankResponse bankResponse = new BankResponse(TransactionStatus.SUCCESS, paymentRequest.getMerchantOrderId(), acquirerOrderId, acquirerTimestamp, paymentRequest.getPaymentId());
                apiClient.sendBankResponse(bankResponse);
            }
        }
        else {
            BankRequest bankRequest = new BankRequest(acquirerAccount.getCurrentState(), acquirerAccount.getCardHolderName(), acquirerAccount.getCardPAN(), acquirerAccount.getCardCVC(), acquirerAccount.getCardDueDate(), acquirerAccount.getCustomerId(), paymentRequest.getMerchantOrderId(), paymentRequest.getPaymentId());
            bankRequestService.save(bankRequest);
            apiClient.sendBankRequest(bankRequest);
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }

        int totalSum = 0;
        boolean shouldDouble = false;

        // Iteracija unazad kroz cifre broja kartice
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char c = cardNumber.charAt(i);
            // Provera da li je svaki karakter cifra
            if (!Character.isDigit(c)) {
                return false;
            }

            int digit = Character.getNumericValue(c);
            // Ako treba duplirati trenutnu cifru
            if (shouldDouble) {
                digit *= 2;
                // Ako je rezultat veći od 9, oduzmi 9
                if (digit > 9) {
                    digit -= 9;
                }
            }

            totalSum += digit;
            // Prebacivanje stanja za narednu iteraciju
            shouldDouble = !shouldDouble;
        }

        // Broj je validan ako je suma deljiva sa 10
        return totalSum % 10 == 0;
    }

    public void processPaymentRequest(PaymentRequest paymentRequest) {
        boolean isRequestValid = paymentRequest.getCustomerId() == 0 && paymentRequest.getAmount() == 0 && paymentRequest.getErrorUrl().isEmpty() && paymentRequest.getSendingMoment() == null &&
                paymentRequest.getFailedUrl().isEmpty() && paymentRequest.getSuccessUrl().isEmpty() && paymentRequest.getMerchantId() == 0L &&
                paymentRequest.getTimestamp().isEmpty() && paymentRequest.getMerchantPassword().isEmpty() && paymentRequest.getMerchantOrderId().isEmpty();

        if(isRequestValid) {

            long paymentId = Long.parseLong(UUID.randomUUID().toString());
            String paymentUrl = UUID.randomUUID().toString();

            paymentRequest.setPaymentId(paymentId);
            paymentRequest.setPaymentUrl(paymentUrl);
            //odvedi kupca na payment url !!!

            paymentRequestService.save(paymentRequest);
        }
    }

    public void processTransactionResult(TransactionResult transactionResult) {
        apiClient.forwardTransactionResult(transactionResult);
    }

    //metoda za Issuer-a
    public void processBankRequest(BankRequest bankRequest) {
        if(!isValidCardNumber(bankRequest.getCardPAN())) {
            return;
        }

        boolean isRequestValid = bankRequest.getAcquirerOrderId().isEmpty() && bankRequest.getAcquirerTimestamp().isEmpty() &&
                bankRequest.getSendingMoment() == null && bankRequest.getCurrentState() == 0.0 && bankRequest.getCardHolderName().isEmpty() &&
                bankRequest.getCardPAN().isEmpty() && bankRequest.getCardCVC() == 0 && bankRequest.getCardDueDate() == null;

        if(isRequestValid) {
            BankAccount acquirerAccount = bankAccountRepository.findByCustomerId(bankRequest.getCustomerId());
            if(acquirerAccount == null) {
                return;
            }

            double state = acquirerAccount.getCurrentState() - bankRequest.getCurrentState();
            if(state < 0.0) {
                TransactionResult transactionResult = new TransactionResult(TransactionStatus.FAIL, bankRequest.getAcquirerOrderId(), bankRequest.getAcquirerTimestamp());
                apiClient.sendTransactionResult(transactionResult);
            }
            else {
                bankAccountRepository.updateCurrentState(acquirerAccount.getAccountId(), state);
                TransactionResult transactionResult = new TransactionResult(TransactionStatus.SUCCESS, bankRequest.getAcquirerOrderId(), bankRequest.getAcquirerTimestamp());
                apiClient.sendTransactionResult(transactionResult);
            }
        }
        else {
            TransactionResult transactionResult = new TransactionResult(TransactionStatus.ERROR, bankRequest.getAcquirerOrderId(), bankRequest.getAcquirerTimestamp());
            apiClient.sendTransactionResult(transactionResult);
        }
    }
}

