/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.model.Account;
import com.ksm.server.model.ConfirmationToken;
import com.ksm.server.repository.AccountRepository;
import com.ksm.server.repository.ConfirmationTokenRepository;
import com.ksm.server.service.EmailSenderService;
import com.ksm.server.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("/api")
public class UserManagementController {
    
    @Autowired
    UserManagementService userManagementService;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("login") //email + password
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return userManagementService.login(loginRequest);
    }

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO registerData) {
        return userManagementService.register(registerData);
    }
    
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Account account = accountRepository.findByEmail(token.getAccount().getEmail());
            System.out.println(account.getEmail());
            account.setEnabled(true);
            accountRepository.save(account);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    
}
