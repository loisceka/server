/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.model.Employee;
import com.ksm.server.repository.AccountRepository;
import com.ksm.server.repository.EmployeeRepository;
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
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AccountRepository accountRepository;

    //Create
    public boolean save(Employee employee) {
        Employee e = employeeRepository.save(employee);
    //    e.getId() = acc 
        return employeeRepository.existsById(e.getId());
    }

    //Read All
    public List<Employee> getAllEmployee() {
        List<Employee> employee = employeeRepository.findAll();
        return employee;
    }

    //Insert Data Awal
    public void dummyData() {
        List<Employee> employees = new ArrayList<>();
        Date date = new Date();
        employees.add(new Employee("0011", "Lois", "Ceka", date, "Male", "Merapi St.", "081215062206"));
        employees.add(new Employee("0012", "Dony T", "Prasetyo", date, "Male", "Salatiga", "0812xxxxxxxx"));
        employees.add(new Employee("0013", "Tarsisisus E", "Prasetyo", date, "Male", "Salatiga", "0812xxxxxxxx"));
        employeeRepository.saveAll(employees);
    }

    /*  public Employee giveNewRole(String id, Account acc) {
        Employee employee = getById(id).getId();

        Account account = new Account();
        account.setId(employee);
        Account insertedAccounts = loginRepository.save(account);
        account.setId(employee.getId());
        account.setEmail(acc.getEmail());
        account.setPassword(acc.getPassword());
        account.setRole(acc.getRole());
        account.setToken(acc.getToken());
        account.setIsdelete(acc.account.setIsdelete(acc.isIsdelete());
        employee.setAccount(account);
        Account inserted = loginRepository.save(account);
        employee.setAccount(inserted);
        employeeRepository.save(employee);
        return employee;
    }

     */
    //Insert Data
    public Employee insertData(Employee employee) {
        List<Employee> employees = employeeRepository.findAll();
        String xx = null;

        for (Employee emp : employees) {
            if (emp.getId().equals(xx)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
            } else {
                break;
            }
        }
        return employeeRepository.save(employee);
    }

    //Read -> getById(String id) -> findById(id)
    public Employee getById(String id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(String id) 
    public Employee update(String id, Employee employee) {
        Employee oldEmployee = getById(id);
        oldEmployee.setFirstName(employee.getFirstName());
        oldEmployee.setLastName(employee.getLastName());
        oldEmployee.setBirthDate(employee.getBirthDate());
        oldEmployee.setGender(employee.getGender());
        oldEmployee.setAddress(employee.getAddress());
        oldEmployee.setPhone(employee.getPhone());
        oldEmployee.setIsDeleted(employee.getIsDeleted());

        return employeeRepository.save(oldEmployee);
    }

    //Delete -> deleteById(String id)
    public Employee deleteById(String id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

}
