package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 50)
    private String Name;

    @Column(nullable = true)
    @Size(max = 400)
    private String Description;

    @Column(name = "ins_date", nullable = false)
    @CreatedDate
    private Date insDate;

    @Column(nullable = false)
    @Size(max = 400)
    private Integer State;

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Date getInsDate() {
        return insDate;
    }

    public Integer getState() {
        return State;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public void setState(Integer state) {
        State = state;
    }

    Product(){}

    @JsonProperty(access = JsonProperty.Access.AUTO)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Catalog catalog;



}
