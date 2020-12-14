package com.example.webapi.model;

import io.swagger.annotations.ApiModelProperty;

public class Price {

    @ApiModelProperty(notes = "Output product price", example = "3500", required = false, position = 0)
    public Double Price;

    @ApiModelProperty(notes = "Input person is", example = "34", required = true, position = 1)
    public Long inPersonId;

    @ApiModelProperty(notes = "Input card id", example = "2", required = true, position = 2)
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

