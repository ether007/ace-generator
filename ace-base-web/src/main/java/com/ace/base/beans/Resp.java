package com.ace.base.beans;

import java.io.Serializable;

public class Resp implements Serializable{

    private int code;

    private String message;

    private Object data;

    public Resp() {
    }

    public Resp(int code) {
        this.code = code;
    }

    public Resp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resp(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object date) {
        this.data = date;
    }
}
