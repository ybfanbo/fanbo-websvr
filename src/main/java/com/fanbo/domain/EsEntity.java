package com.fanbo.domain;

import java.io.Serializable;

public class EsEntity implements Serializable {

    private static final long serialVersionUID = -4990474972410279669L;


    public static final String INDEX_NAME = "index_entity";
    public static final String TYPE = "tstype";


    private Long id;
    private String name;

    public EsEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EsEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
