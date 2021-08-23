/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.model.Account;
import com.ksm.server.model.AccountRole;
import com.ksm.server.model.Employee;
import com.ksm.server.model.Role;
import com.ksm.server.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    
    String hashCode = "";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Create
    public boolean save(Account account) {
        Account a = accountRepository.save(account);
        return accountRepository.existsById(a.getId());
    }

    //Read All
    public List<Account> getAllAccount() {
        List<Account> account = accountRepository.findAll();
        return account;
    }
    
    
    //Insert Data
    public Account insertData(Account account) {
        if (account.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        return accountRepository.save(account);
    }
    
    //Read -> getById(String id) -> findById(id)
    public Account getById(String id){
        if (accountRepository.existsById(id)) {
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(String id) 
    public Account update(String id, Account account){
        hashCode = passwordEncoder.encode(account.getPassword());
        Account oldAccount = getById(id);
        oldAccount.setEmail(account.getEmail());
        oldAccount.setPassword(hashCode);
        oldAccount.setIsDeleted(false);


         return accountRepository.save(oldAccount);
    } 
    //Delete -> deleteById(String id)
    public Account deleteById(String id){
        Account account = getById(id);
        accountRepository.delete(account);
        return account;
    }
     }
