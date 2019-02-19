package com.crud.library.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "READERS")
@NoArgsConstructor
public class Reader {
    private int id;
    private String firstname;
    private String lastname;
    private Date accountCreated;
    private List<Rent> rents = new ArrayList<>();

    public Reader(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreated = new Date();
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @NotNull
    @Column(name = "ACCOUNT_CREATED")
    public Date getAccountCreated() {
        return accountCreated;
    }

    @OneToMany(targetEntity = Rent.class, mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Rent> getRents() {
        return rents;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    private void setRents(List<Rent> rents) { this.rents = rents; }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", accountCreated=" + accountCreated +
                ", rents=" + rents +
                '}';
    }
}
