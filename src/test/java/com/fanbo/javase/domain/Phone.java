package com.fanbo.javase.domain;

import java.math.BigDecimal;

/**
 * Created by fanbo on 2017/2/3.
 */
public class Phone {
    private Integer id;
    private String name;
    private String size;
    private BigDecimal price;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Phone(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public Phone(String name, String size, BigDecimal price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public Phone(Integer id, String name, String size, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }
}
