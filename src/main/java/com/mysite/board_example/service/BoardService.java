package com.mysite.board_example.service;

import com.mysite.board_example.dto.*;
import com.mysite.board_example.entity.Article;
import com.mysite.board_example.entity.Comment;
import com.mysite.board_example.entity.User;
import com.mysite.board_example.error.*;
import com.mysite.board_example.repository.ArticleRepository;
import com.mysite.board_example.repository.CommentRepository;
import com.mysite.board_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public AddArticleResponse saveArticle(AddArticleRequest addArticleRequest) {
        Optional<User> user = userRepository.findById(addArticleRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserDoesntExistException("user doesnt exist", ErrorCode.USER_DOESNT_EXIST);
        }

        Article article = Article.builder()
                .title(addArticleRequest.getTitle())
                .content(addArticleRequest.getContent())
                .user(user.get())
                .build();

        Article savedArticle = articleRepository.save(article);

        return AddArticleResponse.builder()
                .articleId(savedArticle.getArticleId())
                .title(savedArticle.getTitle())
                .content(savedArticle.getContent())
                .authorName(savedArticle.getUser().getName())
                .build();
    }

    public GetAllArticlesResponse findAllArticles() {
        List<GetAllArticlesResponse.ArticleDTO> articles = articleRepository.findAll().stream()
                .map((Article a) -> GetAllArticlesResponse.ArticleDTO.builder()
                        .articleId(a.getArticleId())
                        .title(a.getTitle())
                        .content(a.getContent())
                        .authorName(a.getUser().getName())
                        .build())
                .toList();

        return GetAllArticlesResponse.builder()
                .articles(articles)
                .build();
    }

    public GetArticleResponse findArticleById(Integer id) {
        Optional<Article> op = articleRepository.findById(id);
        if (op.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }
        Article article = op.get();

        return GetArticleResponse.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .authorName(article.getUser().getName())
                .comments(article.getComments().stream().map((Comment c) -> GetArticleResponse.CommentDTO.builder()
                        .content(c.getContent())
                        .commentId(c.getCommentId())
                        .name(c.getUser().getName())
                        .build()).toList())
                .build();
    }

    @Transactional
    public UpdateArticleResponse updateArticle(Integer id, UpdateArticleRequest updateArticleRequest) {
        Optional<Article> op = articleRepository.findById(id);
        if (op.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }

        Article article = op.get();
        article.updateTitle(updateArticleRequest.getTitle());
        article.updateContent(updateArticleRequest.getContent());
        Article savedArticle = articleRepository.save(article);

        return UpdateArticleResponse.builder()
                .articleId(savedArticle.getArticleId())
                .title(savedArticle.getTitle())
                .content(savedArticle.getContent())
                .authorName(savedArticle.getUser().getName())
                .build();
    }

    public void deleteArticle(Integer id) {
        Optional<Article> op = articleRepository.findById(id);
        if (op.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }
        articleRepository.deleteById(id);
    }

    @Transactional
    public AddCommentResponse saveComment(Integer id, AddCommentRequest addCommentRequest) {
        Optional<Article> op_article = articleRepository.findById(id);
        if (op_article.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }
        Optional<User> op_user = userRepository.findById(addCommentRequest.getUserId());
        if (op_user.isEmpty()) {
            throw new UserDoesntExistException("user doesnt exist", ErrorCode.USER_DOESNT_EXIST);
        }

        User user = op_user.get();
        Article article = op_article.get();

        Comment savedComment = commentRepository.save(Comment.builder()
                .user(user)
                .article(article)
                .content(addCommentRequest.getContent())
                .build());

        return AddCommentResponse.builder()
                .commentId(savedComment.getCommentId())
                .userId(user.getUserId())
                .articleId(article.getArticleId())
                .content(savedComment.getContent())
                .build();
    }

    @Transactional
    public void deleteComment(Integer articleId, Integer commentId){
        Optional<Comment> op_comment = commentRepository.findById(commentId);
        if(op_comment.isEmpty()){
            throw new CommentDoesntExistException("comment doesnt exist", ErrorCode.COMMENT_DOESNT_EXIST);
        }
        Comment comment = op_comment.get();

        if(!comment.getArticle().getArticleId().equals(articleId)){
            throw new CommentArticleNotMatchException("comment article not match", ErrorCode.COMMENT_ARTICLE_NOT_MATCH);
        }
        commentRepository.delete(comment);
    }
}
