package com.mysite.board_example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetArticleResponse {
    private Integer articleId;
    private String title;
    private String content;
    private String authorName;
    private List<CommentDTO> comments;

    @Builder
    public GetArticleResponse(Integer articleId, String title, String content, String authorName, List<CommentDTO> comments) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.comments = comments;
    }

    @Getter
    public static class CommentDTO {
        private Integer commentId;
        private String content;
        private String name;

        @Builder
        public CommentDTO(Integer commentId, String content, String name) {
            this.commentId = commentId;
            this.content = content;
            this.name = name;
        }
    }
}
