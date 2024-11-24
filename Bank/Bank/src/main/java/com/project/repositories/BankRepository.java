package com.project.repositories;

import com.project.models.BankRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankRepository extends JpaRepository<BankRequest, Long> {

    //bio je los naziv metode , pa je bacalo gresku, upit ovaj sam dodala
    @Query("SELECT a.id FROM Bank b JOIN b.accounts a WHERE b.id = :bankId")
    List<Long> getAllAccountsById(@Param("bankId") Long bankId);
}
