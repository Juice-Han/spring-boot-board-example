package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteUserResponse {
    private Integer userId;

    @Builder
    public DeleteUserResponse(Integer userId) {
        this.userId = userId;
    }
}
