package com.project.repositories;

import com.project.models.BankAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByCustomerId(Long customerId);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.currentState = :state WHERE b.accountId = :accountId")
    void updateCurrentState(Long accountId, double state);

    @Query("SELECT b FROM BankAccount b WHERE b.cardHolderName = :cardHolderName AND b.cardPAN = :cardPAN AND b.cardCVC = :cardCVC AND b.cardDueDate = :cardDueDate")
    BankAccount findByCardDetails(String cardHolderName, String cardPAN, int cardCVC, Date cardDueDate);

}
