package com.project.services;

import com.project.models.PaymentRequest;
import com.project.repositories.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    public Optional<PaymentRequest> getPaymentRequest(long customerId, long merchantId) {
        //proslijedi string a ne long 
        return paymentRequestRepository.findByCustomerIdAndMerchantId(customerId, String.valueOf(merchantId));
    }

    public void save(PaymentRequest paymentRequest) {
        paymentRequestRepository.save(paymentRequest);
    }
}
