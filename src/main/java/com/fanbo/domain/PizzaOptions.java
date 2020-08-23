package com.fanbo.domain;

//《Vue2.x全家桶实战点餐系统(axios/fetch/vuex/router/导航守卫)》
public class PizzaOptions {

    private Integer size;  //尺寸
    private Integer price; //价格

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
