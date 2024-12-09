package com.bank.Bank.Management.serviceImpl;

import com.bank.Bank.Management.dtos.UserAccountDTO;
import com.bank.Bank.Management.entity.Account;
import com.bank.Bank.Management.entity.User;
import com.bank.Bank.Management.enums.AccountType;
import com.bank.Bank.Management.enums.UserOccupation;
import com.bank.Bank.Management.exceptions.InsufficientFund;
import com.bank.Bank.Management.exceptions.UserNotFoundException;
import com.bank.Bank.Management.repository.AccountRepo;
import com.bank.Bank.Management.repository.UserRepo;
import com.bank.Bank.Management.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AccountRepo accountRepo;


    private User findUserById(int userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
    }
    @Override
    public String generateAccountNumber() {

        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(random.nextInt(9) + 1);

        for (int i = 0; i < 15; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    @Override
    public UserAccountDTO CreateAccount(UserAccountDTO userAccountDTO) {

        User user = new User();
        user.setName(userAccountDTO.getName());
        user.setAge(userAccountDTO.getAge());
        user.setEmail(userAccountDTO.getEmail());
        user.setMobileNumber(userAccountDTO.getMobileNumber());
        user.setOccupation(UserOccupation.valueOf(userAccountDTO.getOccupation()));

        Account account = new Account();
        account.setAccountNo(generateAccountNumber());
        account.setAccountType(AccountType.valueOf(userAccountDTO.getAccountType()));
        account.setBalance(0);
        account.setNominee(userAccountDTO.getNominee());
        account.setUser(user);

        user.setAccounts(account);

        User savedUser = userRepo.save(user);
        accountRepo.save(account);

        UserAccountDTO userAccountDTO1 = new UserAccountDTO();
        userAccountDTO1.setName(user.getName());
        userAccountDTO1.setMobileNumber(user.getMobileNumber());
        userAccountDTO1.setAccountNumber(account.getAccountNo());
        userAccountDTO1.setAccountType(String.valueOf(account.getAccountType()));
        userAccountDTO1.setNominee(account.getNominee());
        userAccountDTO1.setBalance(account.getBalance());
        userAccountDTO1.setUserId(user.getId());
        userAccountDTO1.setMessage("Congratulations! Your account has been successfully created. Welcome to our banking family!");
        return userAccountDTO1;

    }

    @Override
    public String deposit(int userId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        User user = findUserById(userId);
        Account account = user.getAccounts();
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        return "Deposit Successful! Your current Balance is " + account.getBalance();
    }

    @Override
    public String withdraw(int userId, double amount) {
        User user = findUserById(userId);
        Account account = user.getAccounts();

        double currentBalance = account.getBalance();

        if (currentBalance >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepo.save(account);
        } else {
            throw new InsufficientFund("Insufficient Fund");
        }


        return "Withdraw Successful! Your current Balance is " + account.getBalance();
    }

    @Override
    @Transactional
    public String transfer(int fromUserId, double amount, int toUserId) {
        User fromUser = findUserById(fromUserId);
        User toUser = findUserById(toUserId);



        Account fromAccount = fromUser.getAccounts();
        Account toAccount = toUser.getAccounts();

        if (fromAccount.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepo.saveAll(Arrays.asList(fromAccount, toAccount));

        return "Transfer successful! Your current balance is " + fromAccount.getBalance();
    }

    @Override
    public String checkBalance(int userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return "Your current Balance is " + user.getAccounts().getBalance();
    }

}
