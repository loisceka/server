/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.model.AccountRole;
import com.ksm.server.service.AccountRoleService;
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
@RequestMapping("api/accountrole")
public class AccountRoleController {
    @Autowired
    AccountRoleService accountRoleService;
    
    /**
     * - GET    -> getAll() 
     * - GET    -> getById() 
     * - POST    -> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE    -> delete(Integer id)
     */
    
    @GetMapping()
    public List<AccountRole> getAll(){
        return accountRoleService.getAllAccountRole();
    }
    
    @GetMapping("/{id}") //localhost:8080/api/accountrole/1
    public AccountRole getById(@PathVariable Integer id){
        return accountRoleService.getById(id);
    }
    
    @PostMapping()
    public AccountRole save(@RequestBody AccountRole accountRole){
        return accountRoleService.insertData(accountRole);
    }
    
    @PutMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public AccountRole save(@PathVariable Integer id,@RequestBody AccountRole accountRole){
        return accountRoleService.update(id, accountRole);
    }
    
    @DeleteMapping("/{id}")
    public AccountRole delete(@PathVariable Integer id){
        return accountRoleService.deleteById(id);
    }
}
