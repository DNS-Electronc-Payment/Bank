package com.project.repositories;

import com.project.models.BankRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<BankRequest, Long> {
    List<Long> getAllAccountsById(long bankId);
}
