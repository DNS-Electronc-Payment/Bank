package com.project.services;

import com.project.models.BankRequest;
import com.project.models.CustomerAccount;
import com.project.models.PaymentRequest;
import com.project.repositories.BankRequestRepository;
import com.project.repositories.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private BankRequestRepository bankRequestRepository;

    public void processCardData(CustomerAccount customerAccount) {
        CustomerAccount account = customerAccountRepository.findByCustomerId(customerAccount.getCustomerId());
        if(account == null) {
            //baca se neka greska
        }

        //ako su banke iste: rezervisanje iznosa i
        // slanje BankResponse-a PSP-u preko API Clienta


        //ako su razlicite: generisanje ACQUIRER_ORDER_ID i ACQUIRER_TIMESTAMP i
        // slanje Bank Request-a PCC-u preko API CLienta
    }

    public CustomerAccount getAccountByCustomerId(Long customerId) {
        return customerAccountRepository.findByCustomerId(customerId);
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

