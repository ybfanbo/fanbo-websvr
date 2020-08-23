package com.fanbo.authentication;

public enum UserRole {

    Super(1, "super"),
    Admin(2, "admin"),
    Developer(3, "developer"),
    Standard(4, "standard");

    private final int id;
    private final String name;

    UserRole(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
