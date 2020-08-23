package com.fanbo.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNoRoleException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNoRoleException(String msg) {
        super(msg);
    }

    public UserNoRoleException(String msg, Throwable t) {
        super(msg, t);
    }
}

