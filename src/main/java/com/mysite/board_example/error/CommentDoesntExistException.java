package com.mysite.board_example.error;

import lombok.Getter;

@Getter
public class CommentDoesntExistException extends RuntimeException{
    private ErrorCode errorCode;

    public CommentDoesntExistException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
