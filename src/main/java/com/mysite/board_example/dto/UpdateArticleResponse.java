package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArticleResponse {
    private Integer articleId;
    private String title;
    private String content;
    private String authorName;

    @Builder
    public UpdateArticleResponse(Integer articleId, String title, String content, String authorName) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }
}
