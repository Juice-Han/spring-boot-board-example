package com.mysite.board_example.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_DOESNT_EXIST(400,"USER DOESNT EXIST");

    private int status;
    private String message;
}
