/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.model.AccountRole;
import com.ksm.server.model.Role;
import com.ksm.server.repository.AccountRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class AccountRoleService {
    @Autowired
    AccountRoleRepository accountRoleRepository;
    
    //Create
    public boolean save(AccountRole accountRole) {
        AccountRole ac = accountRoleRepository.save(accountRole);
      //  return accountRoleRepository.existsById(ac.getId());
       return true; 
    }

    //Read All
    public List<AccountRole> getAllAccountRole() {
        List<AccountRole> accountRole = accountRoleRepository.findAll();
        return accountRole;
    }
    
    //Insert Data
    public AccountRole insertData(AccountRole accountRole) {
        if (accountRole.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        return accountRoleRepository.save(accountRole);
    }
    
    //Read -> getById(String id) -> findById(id)
    public AccountRole getById(Integer id){
        return null;
   //     if (accountRoleRepository.existsById(id)) {
   //            return accountRoleRepository.findById(id).get();
   //     }
   //      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(Integer id) 
    public AccountRole update(Integer id, AccountRole accountRole){
        AccountRole oldAccountRole = getById(id);
        oldAccountRole.setDeleted(accountRole.isDeleted());

         return accountRoleRepository.save(oldAccountRole);
    } 
    //Delete -> deleteById(Integer id)
    public AccountRole deleteById(Integer id){
        AccountRole accountRole = getById(id);
        accountRoleRepository.delete(accountRole);
        return accountRole;
    }
}
