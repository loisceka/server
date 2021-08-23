/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.model.Employee;
import com.ksm.server.model.Role;
import com.ksm.server.repository.RoleRepository;
import java.util.ArrayList;
import java.util.Date;
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
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    
    //Create
    public boolean save(Role role) {
        Role r = roleRepository.save(role);
        return roleRepository.existsById(r.getId());
    }

    public void dummyData() {
        List<Role> roles = new ArrayList<>();
        
        roles.add(new Role("Admin"));
        roles.add(new Role("User"));
        
        roleRepository.saveAll(roles);
    }
    
    //Read All
    public List<Role> getAllRole() {
        List<Role> role = roleRepository.findAll();
        return role;
    }
    
    //Insert Data
    public Role insertData(Role role) {
        if (role.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        return roleRepository.save(role);
    }
    
    //Read -> getById(String id) -> findById(id)
    public Role getById(Integer id){
        if (roleRepository.existsById(id)) {
            return roleRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(Integer id) 
    public Role update(Integer id, Role role){
        Role oldRole = getById(id);
        oldRole.setName(role.getName());

         return roleRepository.save(oldRole);
    } 
    //Delete -> deleteById(Integer id)
    public Role deleteById(Integer id){
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
