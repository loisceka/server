/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.repository;

import com.ksm.server.model.Account;
import com.ksm.server.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author loisceka
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Account getEmployeeByAccount(String email);
    
}
