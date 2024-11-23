package com.project.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="bankRequests")
public class BankRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId", updatable = false, nullable = false)
    private Long requestId;
    @Column(nullable = false)
    private String acquirerOrderId;
    @Column(nullable = false)
    private String acquirerTimestamp;
    @Column(nullable = false)
    private LocalDateTime sendingMoment;
    @Column(nullable = false)
    private double currentState;
    @Column(nullable = false)
    private String cardHolderName;
    @Column(nullable = false, unique = true)
    private String cardPAN;
    @Column(nullable = false)
    private int cardCVC;
    @Column(nullable = false)
    private Date cardDueDate;
    @Column(nullable = false)
    private long customerId;
    @Column(nullable = false)
    private long paymentId;
    @Column(nullable = false)
    private String merchantOrderId;

    public BankRequest() {
    }

    public BankRequest(double currentState, String cardHolderName, String cardPAN, int cardCVC, Date cardDueDate, long customerId, String merchantOrderId, long paymentId) {
        this.acquirerOrderId = UUID.randomUUID().toString();
        this.acquirerTimestamp = UUID.randomUUID().toString();
        this.sendingMoment = LocalDateTime.now();
        this.currentState = currentState;
        this.cardHolderName = cardHolderName;
        this.cardPAN = cardPAN;
        this.cardCVC = cardCVC;
        this.cardDueDate = cardDueDate;
        this.customerId = customerId;
        this.merchantOrderId = merchantOrderId;
        this.paymentId = paymentId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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
