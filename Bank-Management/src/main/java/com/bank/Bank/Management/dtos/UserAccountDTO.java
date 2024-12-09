package com.bank.Bank.Management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountDTO {
    private int userId;
    private String name;
    private String mobileNumber;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int age;
    private String occupation;
    private String accountNumber;


    private String accountType;
    private String message;
    private double balance;
    private String nominee;


    public UserAccountDTO() {
    }

    public UserAccountDTO(int userId, String name, String mobileNumber, String email, int age, String occupation, String accountNumber, String accountType, String message, double balance, String nominee) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.age = age;
        this.occupation = occupation;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.message = message;
        this.balance = balance;
        this.nominee = nominee;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
