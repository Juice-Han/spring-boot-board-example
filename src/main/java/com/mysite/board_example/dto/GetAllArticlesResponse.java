package com.mysite.board_example.dto;

import com.mysite.board_example.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllArticlesResponse {
    private List<GetArticleDTO> articles;

    @Builder
    public GetAllArticlesResponse(List<GetArticleDTO> articles) {
        this.articles = articles;
    }
}
