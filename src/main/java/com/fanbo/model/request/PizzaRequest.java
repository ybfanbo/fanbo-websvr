package com.fanbo.model.request;


import com.fanbo.domain.PizzaOptions;

import java.util.List;

public class PizzaRequest {

    private String name;
    private String description;
    private List<PizzaOptions> options;


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

    public List<PizzaOptions> getOptions() {
        return options;
    }

    public void setOptions(List<PizzaOptions> options) {
        this.options = options;
    }
}
