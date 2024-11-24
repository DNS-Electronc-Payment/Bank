package com.project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    //veza onetomany , u List<> moras staviti entitet , a ne njihove ideve da bi ti uvezao
    @OneToMany(mappedBy = "bankId")
    private List<BankAccount> accounts;

    public Bank() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
}
