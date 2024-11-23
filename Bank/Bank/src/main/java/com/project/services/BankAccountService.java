package com.project.services;

import com.project.clients.APIClient;
import com.project.enums.TransactionStatus;
import com.project.models.BankAccount;
import com.project.models.BankRequest;
import com.project.models.BankResponse;
import com.project.models.PaymentRequest;
import com.project.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    private APIClient apiClient;

    public void processCardData(BankAccount account) {
        BankAccount acquirerAccount = bankAccountRepository.findByCustomerId(account.getCustomerId());
        BankAccount issuerAccount = bankAccountRepository.findByCustomerId(account.getCustomerId());
        if((acquirerAccount != null && issuerAccount != null)) {
            if(acquirerAccount.getBankId() == issuerAccount.getBankId()) {
                PaymentRequest paymentRequest = paymentRequestService.getPaymentRequest(acquirerAccount.getCustomerId(), issuerAccount.getCustomerId()).orElse(null);
                if(paymentRequest != null) {
                    double state = acquirerAccount.getCurrentState() - paymentRequest.getAmount();
                    if(state < 0.0) {

                        apiClient.sendBankResponse();
                    }
                    else {
                        bankAccountRepository.updateCurrentState(acquirerAccount.getAccountId(), state);
                        String acquirerOrderId = UUID.randomUUID().toString();
                        String acquirerTimestamp = UUID.randomUUID().toString();
                        BankResponse bankResponse = new BankResponse(TransactionStatus.SUCCESS, paymentRequest.getMerchantOrderId(), acquirerOrderId, acquirerTimestamp, paymentRequest.getPaymentId());
                        apiClient.sendBankResponse();
                    }
                }
            }
            else {
                apiClient.sendBankRequest();
            }
        }
        else {
            apiClient.sendBankResponse();
        }
    }

    public BankAccount getAccountByCustomerId(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }

    public void processPaymentRequest(PaymentRequest paymentRequest) {
        if(paymentRequest.getCustomerId() == 0 || paymentRequest.getAmount() == 0 || paymentRequest.getErrorUrl().isEmpty() || paymentRequest.getSendingMoment() == null ||
        paymentRequest.getFailedUrl().isEmpty() || paymentRequest.getSuccessUrl().isEmpty() || paymentRequest.getMerchantId().isEmpty() ||
        paymentRequest.getTimestamp().isEmpty() || paymentRequest.getMerchantPassword().isEmpty() || paymentRequest.getMerchantOrderId().isEmpty()) {
            //baca se neka greska
        }
        else {
            //generisanje PAYMENT_ID i PAYMENT_URL
            //PAYMENT_URL je URL koji vode do sajta banke prodavca (do stranice gdje se unose podaci kartice)
        }
    }

    public void processBankRequest(BankRequest bankRequest) {
        if(bankRequest.getAcquirerOrderId().isEmpty() || bankRequest.getAcquirerTimestamp().isEmpty() ||
        bankRequest.getSendingMoment() == null || bankRequest.getCurrentState() == 0.0 || bankRequest.getCardHolderName().isEmpty() ||
        bankRequest.getCardPAN().isEmpty() || bankRequest.getCardCVC() == 0 || bankRequest.getCardDueDate() == null) {
            //baca neku gresku
        }
        else {
            //slanje Transaction Result-a PCC-u preko API CLienta
        }
    }
}

