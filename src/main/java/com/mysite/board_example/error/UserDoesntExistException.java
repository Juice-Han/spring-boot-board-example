package com.mysite.board_example.error;

import lombok.Getter;

@Getter
public class UserDoesntExistException extends RuntimeException{
    private ErrorCode errorCode;

    public UserDoesntExistException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
