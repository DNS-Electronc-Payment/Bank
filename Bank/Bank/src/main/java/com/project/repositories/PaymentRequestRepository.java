package com.project.repositories;

import com.project.models.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

    Optional<PaymentRequest> findByCustomerIdAndMerchantId(Long customerId, Long merchantId);
}

