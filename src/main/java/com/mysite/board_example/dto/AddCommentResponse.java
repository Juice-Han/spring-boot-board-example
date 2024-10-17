package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentResponse {
    private Integer commentId;
    private Integer userId;
    private Integer articleId;
    private String content;

    @Builder
    public AddCommentResponse(Integer commentId, Integer userId, Integer articleId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
    }
}
