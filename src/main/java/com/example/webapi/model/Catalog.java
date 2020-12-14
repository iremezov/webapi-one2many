package com.example.webapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
@ApiModel(description = "Class representing a product catalog.")
public class Catalog {

    @ApiModelProperty(notes = "ID of catalog", example = "1", required = false, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Catalog name.", example = "Food", required = true, position = 2)
    @NotNull
    @Max(value = 100, message = "Name can only have a maximum length of 100")
    private String Name;

    //@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "catalog")
    //private List<Product> products;


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

/*
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

 */
}
