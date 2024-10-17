package com.mysite.board_example.service;

import com.mysite.board_example.dto.*;
import com.mysite.board_example.entity.Article;
import com.mysite.board_example.entity.User;
import com.mysite.board_example.error.ArticleDoesntExistException;
import com.mysite.board_example.error.ErrorCode;
import com.mysite.board_example.error.UserDoesntExistException;
import com.mysite.board_example.repository.ArticleRepository;
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
        List<GetArticleDTO> articles = articleRepository.findAll().stream()
                .map((Article a) -> GetArticleDTO.builder()
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

    public GetArticleDTO findArticleById(Integer id) {
        Optional<Article> op = articleRepository.findById(id);
        if (op.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }
        Article article = op.get();

        return GetArticleDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .authorName(article.getUser().getName())
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
        articleRepository.save(article);

        GetArticleDTO articleDTO = GetArticleDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .authorName(article.getUser().getName())
                .build();

        return UpdateArticleResponse.builder()
                .article(articleDTO)
                .build();
    }

    public void deleteArticle(Integer id) {
        Optional<Article> op = articleRepository.findById(id);
        if (op.isEmpty()) {
            throw new ArticleDoesntExistException("article doesnt exist", ErrorCode.ARTICLE_DOESNT_EXIST);
        }
        articleRepository.deleteById(id);
    }
}
