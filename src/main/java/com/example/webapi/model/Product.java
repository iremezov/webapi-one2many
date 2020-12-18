package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Product {

    @ApiModelProperty(notes = "ID of product", example = "1", required = false, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Product name", example = "product #1", position = 1)
    @Column(nullable = false)
    @Size(max = 50)
    private String Name;

    @ApiModelProperty(notes = "Product name", example = "very good product", position = 2)
    @Column(nullable = true)
    @Size(max = 400)
    private String Description;

    @ApiModelProperty(notes = "Product insert date (auto-insert)", position = 3)
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @Column(name = "ins_date", nullable = false)
    @CreatedDate
    private Date insDate;

    @ApiModelProperty(notes = "Product status 1-active, 0-inactive", example = "1", position = 4)
    @Column(nullable = false)
    @Size(max = 400)
    private Integer State;

    @ApiModelProperty(notes = "Product price", example = "3500", position = 5)
    @Column(nullable = false)
    @Size(max = 400)
    private Double Price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "products", targetEntity = Cart.class)
    private List<Cart> cart;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog;


    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    //private List<ProductDetail> productDetails;

    /*
        public List<ProductDetail> getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(List<ProductDetail> productDetails) {
            this.productDetails = productDetails;
        }
    */


    @OneToOne(mappedBy = "product")
    private ProductDetail productDetails;

    public ProductDetail getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetail productDetails) {
        this.productDetails = productDetails;
    }

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
