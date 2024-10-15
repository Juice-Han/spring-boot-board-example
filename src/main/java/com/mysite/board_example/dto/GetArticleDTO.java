package com.mysite.board_example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class GetArticleDTO {
    private Integer articleId;
    private String title;
    private String content;
    private String authorName;

    @Builder
    public GetArticleDTO(Integer articleId, String title, String content, String authorName) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }
}
