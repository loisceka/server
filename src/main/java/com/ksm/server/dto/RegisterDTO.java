/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Data
public class RegisterDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDate;
    private String phone;
    private String email;
    private String password;
}
