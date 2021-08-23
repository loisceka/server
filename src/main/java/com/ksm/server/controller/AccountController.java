/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.model.Account;
import com.ksm.server.model.Employee;
import com.ksm.server.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("api/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;
    
    /**
     * - GET    -> getAll() 
     * - GET    -> getById() 
     * - POST    -> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE    -> delete(Integer id)
     */
    
    @GetMapping()
    public List<Account> getAll(){
        return accountService.getAllAccount();
    }
    

    
    @GetMapping("/{id}") //localhost:8080/api/account/1
    public Account getById(@PathVariable String id){
        return accountService.getById(id);
    }
    
    @PostMapping()
    public Account save(@RequestBody Account account){
        return accountService.insertData(account);
    }
    
    @PutMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Account save(@PathVariable String id, @RequestBody Account account){
        return accountService.update(id, account);
    }
    
    @DeleteMapping("/{id}")
    public Account delete(@PathVariable String id){
        return accountService.deleteById(id);
    }
}
