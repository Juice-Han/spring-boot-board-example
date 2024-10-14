package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddArticleResponse {
    private Integer articleId;
    private String title;
    private String content;
    private String authorName;

    @Builder
    public AddArticleResponse(Integer articleId, String title, String content, String authorName) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }
}
