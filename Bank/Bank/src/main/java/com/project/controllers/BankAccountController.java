package com.project.controllers;

import com.project.models.BankAccount;
import com.project.models.BankRequest;
import com.project.models.PaymentRequest;
import com.project.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    //ovu metodu gadja Bank Front (za odjeljak posvecen Acquirer-u) nakon unosenja podataka o kartici
    @PostMapping("/process-card-data")
    public void processCardData(@RequestBody BankAccount customerAccount) {
        bankAccountService.processCardData(customerAccount);
    }

    //ovu metodu gadja API Client PSP-a u svojoj metodi: sendPaymentRequest
    @PostMapping("/send-payment-request")
    public void sendPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
        bankAccountService.processPaymentRequest(paymentRequest);
    }

    //ovu metodu gadja API Client PCC-a kada salje zahtjev Bank Issuer-u (tacka 3 u tacki 1.1: Placanje karticom)
    @PostMapping("/send-bank-request")
    public void sendBankRequest(@RequestBody BankRequest bankRequest) {
        bankAccountService.processBankRequest(bankRequest);
    }
}
