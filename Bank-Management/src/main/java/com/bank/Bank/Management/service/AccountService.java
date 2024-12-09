package com.bank.Bank.Management.service;

import com.bank.Bank.Management.dtos.UserAccountDTO;

public interface AccountService {

    public String generateAccountNumber();
    public UserAccountDTO CreateAccount(UserAccountDTO userAccountDTO);
    public String deposit(int userId, double amount);
    public String withdraw(int userId, double amount);
    public String transfer(int fromUserId, double amount, int toUserId);
    public String checkBalance(int userId);

}
