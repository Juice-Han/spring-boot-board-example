package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllArticlesResponse {
    private List<ArticleDTO> articles;

    @Builder
    public GetAllArticlesResponse(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    @Getter
    public static class ArticleDTO{
        private Integer articleId;
        private String title;
        private String content;
        private String authorName;

        @Builder
        public ArticleDTO(Integer articleId, String title, String content, String authorName) {
            this.articleId = articleId;
            this.title = title;
            this.content = content;
            this.authorName = authorName;
        }
    }
}
