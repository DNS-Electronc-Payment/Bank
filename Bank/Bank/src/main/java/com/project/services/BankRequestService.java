package com.project.services;

import com.project.models.BankRequest;
import com.project.repositories.BankRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankRequestService {

    @Autowired
    private BankRequestRepository bankRequestRepository;

    public void save(BankRequest bankRequest) { bankRequestRepository.save(bankRequest); }
}
