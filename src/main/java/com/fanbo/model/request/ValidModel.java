package com.fanbo.model.request;

import javax.validation.constraints.NotBlank;

public class ValidModel {

//    @NotBlank(message = "id不能为空")  //这里不能用于整型
    private Integer id;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotBlank(message = "address不能为空")
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
