/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "account_role")
@Data
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    public AccountRole() {
    }

    public AccountRole(Account account, Role role) {
        this.isDeleted = false;
        this.account = account;
        this.role = role;
    }
    
    
}
