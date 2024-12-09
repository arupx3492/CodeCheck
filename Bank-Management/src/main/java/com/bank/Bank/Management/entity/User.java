package com.bank.Bank.Management.entity;

import com.bank.Bank.Management.enums.UserOccupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

public class User {
// @Data was not working . I have created manual Getter setter and constructor


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private UserOccupation occupation ;
    private String mobileNumber;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
        private Account account;

    public User(int id, String name, int age, UserOccupation occupation, String mobileNumber, String email, Account accounts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.account = accounts;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(UserOccupation occupation) {
        this.occupation = occupation;
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

    public Account getAccounts() {
        return account;
    }

    public void setAccounts(Account accounts) {
        this.account = accounts;
    }
}
