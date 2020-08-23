package com.fanbo.exception;

import org.springframework.security.core.AuthenticationException;

public class WhiteListAuthException  extends AuthenticationException {

    public WhiteListAuthException(String msg) {
        super(msg);
    }

    public WhiteListAuthException(String msg, Throwable t) {
        super(msg, t);
    }
}
