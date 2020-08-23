package com.fanbo.model.basemodel;

public class Response {

    private Integer status = 1;
    private String message = "ok";

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
