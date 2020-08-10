package com.svc.myproject.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity{

    @Column(name = "number")
    private int number;


    @Column(name = "create_date")
    private LocalDate createDate;


    @Column(name = "last_update")
    private LocalDate lastUpdate;


    @Column(name = "status")
    private boolean status;


    @Column(name = "name_user")
    private String nameUser;

    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @NotBlank
    @Column(name = "telephone_number")
    private String telephone;


    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Ticket() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
