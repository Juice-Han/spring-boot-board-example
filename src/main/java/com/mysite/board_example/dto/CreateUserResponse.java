package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private Integer userId;

    @Builder
    public CreateUserResponse(Integer userId) {
        this.userId = userId;
    }
}
