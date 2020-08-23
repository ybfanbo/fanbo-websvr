package com.fanbo.domain;


//JavaConfig 注册IOC测试用
public class IocInitBean {

    private Integer id;
    private String name;
    private String description;

    public IocInitBean(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public IocInitBean() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}




