package com.project.clients;

import com.project.models.BankRequest;
import com.project.models.BankResponse;
import com.project.models.TransactionResult;
import org.springframework.stereotype.Component;

@Component
public class APIClient {

    public void sendBankResponse(BankResponse bankResponse) {
        //REST poziv prema PSP-u: slanje podataka o uspjesnosti transakcije (tacka 6 u tacki 1.1: Placanje karticom)
    }

    public void sendBankRequest(BankRequest bankRequest) {
        //REST poziv prema PCC-u: salje se zahtjev koji ce PCC provjeriti i poslati Bank Issuer-u
        //odnosi se na tacku 3 u tacki 1.1: Placanje karticom
    }

    public void sendTransactionResult(TransactionResult transactionResult) {
        //REST poziv prema PCC-u: salje se zahtjev koji ce PCC proslijediti Bank Acquirer-u
        //odnosi se na tacku 5 u tacki 1.1: Placanje karticom
    }
}
