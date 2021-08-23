/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
public class Account {

    @Id
    @Basic(optional = false)
    @Column(name = "employee_id", length = 5)
    private String id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "token", length = 50)
    private String token;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Employee employee;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;
    
    private boolean isEnabled;

    public Account() {
    //    super();
    //    this.enabled = false;
    }

    public Account(String id, String email, String password) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.token = null;
        this.isDeleted = false;
        this.isEnabled = false;
    }

}
