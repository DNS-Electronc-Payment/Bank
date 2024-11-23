package com.project.models;

import com.project.enums.TransactionStatus;

import java.util.UUID;

public class TransactionResult {
    private TransactionStatus transactionResult;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;

    public TransactionResult() {
    }

    public TransactionResult(TransactionStatus transactionResult, String acquirerOrderId, String acquirerTimestamp) {
        this.transactionResult = transactionResult;
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.issuerOrderId = UUID.randomUUID().toString();
        this.issuerTimestamp = UUID.randomUUID().toString();
    }

    public TransactionStatus getTransactionStatus() {
        return transactionResult;
    }

    public void setTransactionStatus(TransactionStatus transactionResult) {
        this.transactionResult = transactionResult;
    }

    public String getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(String acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public String getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(String acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public String getIssuerOrderId() {
        return issuerOrderId;
    }

    public void setIssuerOrderId(String issuerOrderId) {
        this.issuerOrderId = issuerOrderId;
    }

    public String getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(String issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }
}
