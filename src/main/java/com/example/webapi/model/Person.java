package com.example.webapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table
@ApiModel(description = "Class representing a user model.")
public class Person {

    @ApiModelProperty(notes = "ID of person", example = "1", position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Person name", example = "Joe", position = 2)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @ApiModelProperty(notes = "Person last name", example = "Doe", position = 3)
    @Size(max = 200)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ApiModelProperty(notes = "Person email address", example = "Joe.Doe@mail.com", required = true, position = 4)
    @Size(max = 200)
    @Column(name = "email_address", nullable = false)
    private String email;

    @ApiModelProperty(notes = "Date of creation, auto-insert", required = true, position = 5)
    @Column(name = "created_at", nullable = true)
    @CreatedDate
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "person")
    @OrderBy("ins_date DESC")
    private List<Msisdn> msisdns;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    private List<Cart> carts;


    public List<Msisdn> getMsisdn() {
        return msisdns;
    }

    public void setMsisdn(List<Msisdn> msisdn) {
        this.msisdns = msisdn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Person() {
    }
}