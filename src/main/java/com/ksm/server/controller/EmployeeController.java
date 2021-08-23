/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.model.Employee;
import com.ksm.server.service.EmployeeService;
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
@RequestMapping("api/employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    /**
     * - GET    -> getAll() 
     * - GET    -> getById() 
     * - POST    -> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE    -> delete(Integer id)
     */
    
    @GetMapping()
    public List<Employee> getAll(){
        return employeeService.getAllEmployee();
    }
    
    @GetMapping("/{id}") //localhost:8080/api/employee/1
    public Employee getById(@PathVariable String id){
        return employeeService.getById(id);
    }
    
    @PostMapping()
    public Employee save(@RequestBody Employee employee){
        return employeeService.insertData(employee);
    }
    
    @PutMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Employee save(@PathVariable String id, @RequestBody Employee employee){
        return employeeService.update(id, employee);
    }
    
    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable String id){
        return employeeService.deleteById(id);
    }
}
