package com.project.repositories;

import com.project.models.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

    //ovdje je bacalo gresku da ti je merchantId u klasi string, a ti si proslijedjivao long, pa nije mogao to da namapira
    //u servisu je zbog toga stavljeno da se ovoj metodi prosledjuje string
    List<PaymentRequest> findAllByCustomerId(Long customerId);
}

