package com.example.webapi.model;

public class Request{
    public String StrParam;
    public Integer IntParam;

    public String getStrParam() {
        return StrParam;
    }

    public void setStrParam(String strParam) {
        StrParam = strParam;
    }

    public Integer getIntParam() {
        return IntParam;
    }

    public void setIntParam(Integer intParam) {
        IntParam = intParam;
    }

    Request(){}
}