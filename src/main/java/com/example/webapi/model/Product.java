package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @Column(name = "ins_date", nullable = false)
    @CreatedDate
    private Date insDate;

    @Column(nullable = false)
    @Size(max = 400)
    private Integer State;

    @Column(nullable = false)
    @Size(max = 400)
    private Double Price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "products", targetEntity = Cart.class)
    private List<Cart> cart;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog;


    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    Product(){}


}
