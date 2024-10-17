package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentRequest {
    private Integer userId;
    private String content;

    @Builder
    public AddCommentRequest(Integer userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
