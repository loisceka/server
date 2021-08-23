/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.model.Account;
import com.ksm.server.model.AccountRole;
import com.ksm.server.model.ConfirmationToken;
import com.ksm.server.model.Employee;
import com.ksm.server.model.Role;
import com.ksm.server.repository.AccountRepository;
import com.ksm.server.repository.AccountRoleRepository;
import com.ksm.server.repository.ConfirmationTokenRepository;
import com.ksm.server.repository.EmployeeRepository;
import com.ksm.server.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author loisceka
 */
@Service
public class UserManagementService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    AccountService accountService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    Boolean matched = false;
    String hashCode = "";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginData) {
        Account account = accountRepository.findByEmail(loginData.getEmail());
        // cek email ada di db ato ngga,
        // cek loginData.password == account.password
        // Response -> 503, 404, 200
        matched = passwordEncoder.matches(loginData.getPassword(), account.getPassword());
        System.out.println(hashCode);
        System.out.println(account.getPassword());
        if (account.getEmail().equalsIgnoreCase(loginData.getEmail()) && matched) {
            List<String> accountRoles = new ArrayList<String>();

            for (AccountRole accountRole : account.getAccountRoles()) {
                accountRoles.add(accountRole.getRole().getName());
            }

            LoginResponseDTO loginResponse = new LoginResponseDTO(
                    account.getId(),
                    account.getEmail(),
                    accountRoles);

            return ResponseEntity.ok(loginResponse);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or Password Invalid");

    }

    public ResponseEntity<Boolean> register(RegisterDTO registerData) {
        //jika employee baru sudah ada di database, maka jangan bikin employee baru.
        //Account accid = accountRepository.getById(registerData.getId());
        hashCode = passwordEncoder.encode(registerData.getPassword());
        Account email = accountRepository.findByEmail(registerData.getEmail());        
//        //jika email sudah ada
//        if(accountRepository.existByEmail(registerData.getEmail())){
//            System.out.println("Email Already registered !");
//            return ResponseEntity.accepted().body(false);
//        }
//        //jika id sudah ada
//        if (accountRepository.existsById(registerData.getId())) {
//            System.out.println("ID Already Registered !");
//            return ResponseEntity.accepted().body(false);
//        }
        //jika memang memenuhi semua pengecualian, maka save, dan return true
        Employee employee = new Employee(
                registerData.getId(),
                registerData.getFirstName(),
                registerData.getLastName(),
                registerData.getBirthDate(),
                registerData.getGender(),
                registerData.getAddress(),
                registerData.getPhone()
        );
        employeeRepository.save(employee);
        Account account = new Account(
                registerData.getId(),
                registerData.getEmail(),
                hashCode
        );
        accountRepository.save(account);
        if (roleRepository.findAll().isEmpty()) {
            setRole();
        }
        //ROLE DEFAULT = USER(3)
        Role roles = roleService.getById(3);
        //Tambahkan Default Roles
        AccountRole accountRole = new AccountRole(
                account,
                roles
        );
        accountRoleRepository.save(accountRole);
        
        ConfirmationToken confirmationToken = new ConfirmationToken(account);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(account.getEmail());
        mailMessage.setSubject("Complete Registration !");
        mailMessage.setFrom("triprasetyodony@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.accepted().body(true);

    }
    
    public Employee getByIdEmpl(String id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
     public Account getByIdAccount(String id) {
        if (accountRepository.existsById(id)) {
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
    public void setRole() {
        Role role1 = new Role("SUPER_ADMIN");
        Role role2 = new Role("ADMIN");
        Role role3 = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roleRepository.saveAll(roles);
    }

    public Role getByIdRole(Integer id) {
        if (roleRepository.existsById(id)) {
            return roleRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

}
