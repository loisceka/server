/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @Basic(optional = false)
    @Column(name = "id", length = 5)
    private String id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToOne(mappedBy = "employee")
    private Account account;

    public Employee() {
    }

    public Employee(String id, String firstName, String lastName, Date birthDate, String gender, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.isDeleted = false;
    }

    public Employee(String id, String firstName, String lasttName, Date birthDate, String gender, String address, String phone, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.isDeleted = false;
        this.account = account;
    }

}
