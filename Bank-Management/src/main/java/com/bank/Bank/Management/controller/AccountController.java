package com.bank.Bank.Management.controller;

import com.bank.Bank.Management.dtos.UserAccountDTO;
import com.bank.Bank.Management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @PostMapping(value = "/newAccount")
    public ResponseEntity<UserAccountDTO> createAccount(@RequestBody UserAccountDTO userAccountDTO) {
        return ResponseEntity.ok(accountService.CreateAccount(userAccountDTO));
    }

    @GetMapping(value = "/checkBalance/{userId}")
    public ResponseEntity<String> checkBalance(@PathVariable int userId) {
        return ResponseEntity.ok(accountService.checkBalance(userId));
    }

    @PutMapping(value = "/deposit/{userId}/{amount}")
    public ResponseEntity<String> deposit(@PathVariable int userId,@PathVariable double amount) {
        return ResponseEntity.ok(accountService.deposit(userId,amount));
    }

    @PutMapping(value = "/withdraw/{userId}/{amount}")
    public ResponseEntity<String> withdraw(@PathVariable int userId,@PathVariable double amount) {
        return ResponseEntity.ok(accountService.withdraw(userId,amount));
    }

    @PutMapping(value = "/transfer/{fromUserId}/{toUserId}/{amount}")
    public ResponseEntity<String> transfer(@PathVariable int fromUserId,@PathVariable int toUserId,@PathVariable double amount) {
        return ResponseEntity.ok(accountService.transfer(fromUserId,amount,toUserId));
    }


}
