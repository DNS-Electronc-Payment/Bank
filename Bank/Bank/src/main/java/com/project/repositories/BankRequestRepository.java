package com.project.repositories;

import com.project.models.BankRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRequestRepository extends JpaRepository<BankRequest, Long> {
    public void Save(BankRequest request);
}
