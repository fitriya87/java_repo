package com.banking.demoBanking.service;

import com.banking.demoBanking.Account;
import com.banking.demoBanking.Customer;
import com.banking.demoBanking.repository.AccountRepository;
import com.banking.demoBanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService<AccountRepository> {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    public Customer createCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customerRepository.save(customer);
    }

    public Optional<Customer> inquireCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public Account createAccount(Long customerId, String accountType) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        Account account = new Account();
        account.setAccountType(accountType);
        account.setCustomer(customer);
        
        return ((CrudRepository<Account,Long>) accountRepository).save(account);
    }

    public Account depositCash(Long accountNumber, double amount) {
        Account account = ((CrudRepository<Account,Long>) accountRepository).findById(accountNumber)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        
        account.setBalance(account.getBalance() + amount);
        return ((CrudRepository<Account,Long>) accountRepository).save(account);
    }

    public Account withdrawCash(Long accountNumber, double amount) {
        Account account = ((CrudRepository<Account,Long>) accountRepository).findById(accountNumber)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        
        account.setBalance(account.getBalance() - amount);
        return ((CrudRepository<Account,Long>) accountRepository).save(account);
    }

    public Account closeAccount(Long accountNumber) {
        Account account = ((CrudRepository<Account,Long>) accountRepository).findById(accountNumber)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        
        account.setStatus("Closed");
        return ((CrudRepository<Account,Long>) accountRepository).save(account);
    }

    public Optional<Account> inquireAccount(Long accountNumber) {
        return ((CrudRepository<Account,Long>) accountRepository).findById(accountNumber);
    }
}