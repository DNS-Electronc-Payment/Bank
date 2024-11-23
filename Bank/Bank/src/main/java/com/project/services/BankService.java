package com.project.services;

import com.project.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Long> getBankAccounts(long bankId) {
        return bankRepository.getAllAccountsById(bankId);
    }
}
