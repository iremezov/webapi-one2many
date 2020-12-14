package com.example.webapi.model;

public class Response {

    public String Msg;
    public int Code;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }
    public Response(){}

}
