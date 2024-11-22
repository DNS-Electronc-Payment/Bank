package com.project.models;

import java.time.LocalDateTime;
import java.util.Date;

public class BankRequest {
    private Long requestId; //pitanje da li treba?
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private LocalDateTime sendingMoment;
    private double currentState;
    private String cardHolderName;
    private String cardPAN;
    private int cardCVC;
    private Date cardDueDate;

    public BankRequest() {
    }

    public BankRequest(String acquirerOrderId, String acquirerTimestamp, LocalDateTime sendingMoment, double currentState, String cardHolderName, String cardPAN, int cardCVC, Date cardDueDate) {
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.sendingMoment = sendingMoment;
        this.currentState = currentState;
        this.cardHolderName = cardHolderName;
        this.cardPAN = cardPAN;
        this.cardCVC = cardCVC;
        this.cardDueDate = cardDueDate;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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

    public LocalDateTime getSendingMoment() {
        return sendingMoment;
    }

    public void setSendingMoment(LocalDateTime sendingMoment) {
        this.sendingMoment = sendingMoment;
    }

    public double getCurrentState() {
        return currentState;
    }

    public void setCurrentState(double currentState) {
        this.currentState = currentState;
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
