/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.dto;

import com.ksm.server.model.AccountRole;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author loisceka
 */
@Getter
@Setter
public class LoginResponseDTO {
    
    private String id;
    private String email;
    private List<String> roles; //employee, admin, HR

    public LoginResponseDTO(String id, String email, List<String> accountRoles) {
        this.id = id;
        this.email = email;
        this.roles = accountRoles;
    }
}
