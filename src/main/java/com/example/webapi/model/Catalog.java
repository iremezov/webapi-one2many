package com.example.webapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String Name;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "catalog")
    private List<Product> products;

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    Catalog(){}


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
