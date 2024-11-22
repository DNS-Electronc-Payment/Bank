package com.project.models;

import java.util.Date;

public class CustomerAccount {
    private int accountId;
    private long customerId;
    private double currentState;
    private String cardHolderName;
    private String cardPAN;
    private int cardCVC;
    private Date cardDueDate;

    public CustomerAccount() {
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getCurrentState() {
        return currentState;
    }

    public void setCurrentState(double currentState) {
        this.currentState = currentState;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public void setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
    }

    public int getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(int cardCVC) {
        this.cardCVC = cardCVC;
    }

    public Date getCardDueDate() {
        return cardDueDate;
    }

    public void setCardDueDate(Date cardDueDate) {
        this.cardDueDate = cardDueDate;
    }
}
