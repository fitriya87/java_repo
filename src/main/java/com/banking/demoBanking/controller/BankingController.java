package com.banking.demoBanking.controller;

import com.banking.demoBanking.Customer;
import com.banking.demoBanking.Account;
import com.banking.demoBanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    private CustomerService bankingService;

    // Create a new customer
    @PostMapping("/customer")
    public Customer createCustomer(@RequestParam String name) {
        return bankingService.createCustomer(name);
    }

    // Inquire customer details by ID
    @GetMapping("/customer/{id}")
    

    // Create a new account for a customer
    @PostMapping("/account")
    public Account createAccount(@RequestParam Long customerId, @RequestParam String accountType) {
        return bankingService.createAccount(customerId, accountType);
    }

    // Deposit cash into an account
    @GetMapping("/account/deposit")
    public Account depositCash(@RequestParam Long accountNumber, @RequestParam double amount) {
        return bankingService.depositCash(accountNumber, amount);
    }

    // Withdraw cash from an account
    @PostMapping("/account/withdraw")
    public Account withdrawCash(@RequestParam Long accountNumber, @RequestParam double amount) {
        return bankingService.withdrawCash(accountNumber, amount);
    }

    // Close an account
    @GetMapping("/account/close")
    public Account closeAccount(@RequestParam Long accountNumber) {
        return bankingService.closeAccount(accountNumber);
    }

    // Inquire account details by account number
     
}