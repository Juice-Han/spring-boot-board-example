package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArticleResponse {
    private GetArticleDTO article;

    @Builder
    public UpdateArticleResponse(GetArticleDTO article) {
        this.article = article;
    }
}
