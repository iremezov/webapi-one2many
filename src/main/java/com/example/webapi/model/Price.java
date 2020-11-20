package com.example.webapi.model;

public class Price {

    public Double Price;
    public Long inPersonId;
    public Long inCartId;

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Long getInPersonId() {
        return inPersonId;
    }

    public void setInPersonId(Long inPersonId) {
        this.inPersonId = inPersonId;
    }

    public Long getInCartId() {
        return inCartId;
    }

    public void setInCartId(Long inCartId) {
        this.inCartId = inCartId;
    }

    public Price(){}
}
