package com.bank.Bank.Management.entity;

import com.bank.Bank.Management.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;


@Entity
public class Account {
// Lombok was not working . I have created manual Getter setter and constructor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNo;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private double balance;
    private String nominee;

    @OneToOne(mappedBy = "account")
    private User user;


    public void deposit(double amount) throws IllegalAccessException {

        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalAccessException("Deposit amount must be positive");
        }
    }

    public void withdraw(double amount) throws IllegalAccessException {
        if (amount <= 0) {
            throw new IllegalAccessException("Withdraw amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalAccessException("Insufficient funds");
        }
        balance -= amount;
    }


    public Account() {
    }

    public Account(int id, String accountNo, AccountType accountType, double balance, String nominee, User user) {
        this.id = id;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.nominee = nominee;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
