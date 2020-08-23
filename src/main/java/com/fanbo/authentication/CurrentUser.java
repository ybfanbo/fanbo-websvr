package com.fanbo.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CurrentUser extends User implements Serializable {

    private static final long serialVersionUID = -2033415772930458117L;

    private String id;
    private String email;
    private String phone;
    private Date createDate;
    private Date updateDate;

    private List<UserRole> userRoles;


    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id, String email, String phone, Date createDate, Date updateDate, List<UserRole> userRoles) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userRoles = userRoles;
    }


    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
