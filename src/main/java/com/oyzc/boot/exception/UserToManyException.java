package com.oyzc.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常处理
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户数量太多")
public class UserToManyException extends RuntimeException {

    public UserToManyException() {

    }

    public  UserToManyException(String message) {
        super(message);
    }
}
