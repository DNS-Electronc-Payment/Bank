package com.project.services;

import com.project.models.PaymentRequest;
import com.project.repositories.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    public List<PaymentRequest> getPaymentRequest(long customerId) {
        //proslijedi string a ne long 
        return paymentRequestRepository.findAllByCustomerId(customerId);
    }

    public void save(PaymentRequest paymentRequest) {
        paymentRequestRepository.save(paymentRequest);
    }
}
