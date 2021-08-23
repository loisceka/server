/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.model.AccountRole;
import com.ksm.server.model.Role;
import com.ksm.server.service.RoleService;
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
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    
    /**
     * - GET    -> getAll() 
     * - GET    -> getById() 
     * - POST    -> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE    -> delete(Integer id)
     */
    
    @GetMapping()
    public List<Role> getAll(){
        return roleService.getAllRole();
    }
    
    @GetMapping("/{id}") //localhost:8080/api/role/1
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }
    
    @PostMapping()
    public Role save(@RequestBody Role role){
        return roleService.insertData(role);
    }
    
    @PutMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Role save(@PathVariable Integer id,@RequestBody Role role){
        return roleService.update(id, role);
    }
    
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id){
        return roleService.deleteById(id);
    }
}
