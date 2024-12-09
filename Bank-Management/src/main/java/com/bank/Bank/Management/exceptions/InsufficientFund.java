package com.bank.Bank.Management.exceptions;

public class InsufficientFund extends RuntimeException{
    public InsufficientFund(String message) {
        super(message);
    }
}
