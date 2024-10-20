package com.mysite.board_example.error;

import lombok.Getter;

@Getter
public class CommentArticleNotMatchException extends RuntimeException{
    private ErrorCode errorCode;

    public CommentArticleNotMatchException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
