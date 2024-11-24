package com.project.clients;

import com.project.models.BankRequest;
import com.project.models.BankResponse;
import com.project.models.TransactionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIClient {

    @Autowired
    private RestTemplate restTemplate;

    public void sendBankResponse(BankResponse bankResponse) {
        //REST poziv prema PSP-u: slanje podataka o uspjesnosti transakcije (tacka 6 u tacki 1.1: Placanje karticom)

        String url = "https://localhost:8081/payment/response-to-credit-card-payment" ;

        restTemplate.postForEntity(url, bankResponse, Void.class);

    }

    public void sendBankRequest(BankRequest bankRequest) {
        //REST poziv prema PCC-u: salje se zahtjev koji ce PCC provjeriti i poslati Bank Issuer-u
        //odnosi se na tacku 3 u tacki 1.1: Placanje karticom

        String url = "https://localhost:8086/api/pcc/bankRequest" ;

        restTemplate.postForEntity(url, bankRequest, Void.class);


    }

    public void sendTransactionResult(TransactionResult transactionResult) {
        //REST poziv prema PCC-u: salje se zahtjev koji ce PCC proslijediti Bank Acquirer-u
        //odnosi se na tacku 5 u tacki 1.1: Placanje karticom

        String url = "https://localhost:8086/api/pcc/transactionResult" ;

        restTemplate.postForEntity(url, transactionResult, Void.class);

    }

    public void forwardTransactionResult(TransactionResult transactionResult) {
        //REST poziv prema PSP-u
        //odnosi se na tacku 6 u tacki 1.1: Placanje karticom
        String url = "https://localhost:8081/payment/transactionResult" ;

        restTemplate.postForEntity(url, transactionResult, Void.class);
    }
}
