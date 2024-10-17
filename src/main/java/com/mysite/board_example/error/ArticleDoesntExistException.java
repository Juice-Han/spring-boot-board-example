package com.mysite.board_example.error;

import lombok.Getter;

@Getter
public class ArticleDoesntExistException extends RuntimeException {
    private ErrorCode errorCode;

    public ArticleDoesntExistException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
