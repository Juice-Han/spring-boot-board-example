package com.mysite.board_example.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_DOESNT_EXIST(400,"USER DOESNT EXIST"),
    ARTICLE_DOESNT_EXIST(400, "ARTICLE_DOESNT_EXIST"),
    COMMENT_DOESNT_EXIST(400, "COMMENT_DOESNT_EXIST"),
    COMMENT_ARTICLE_NOT_MATCH(400, "COMMENT_ARTICLE_NOT_MATCH");

    private int status;
    private String message;
}
