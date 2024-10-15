package com.mysite.board_example.service;

import com.mysite.board_example.dto.AddArticleRequest;
import com.mysite.board_example.dto.AddArticleResponse;
import com.mysite.board_example.dto.GetAllArticlesResponse;
import com.mysite.board_example.dto.GetArticleDTO;
import com.mysite.board_example.entity.Article;
import com.mysite.board_example.entity.User;
import com.mysite.board_example.error.ErrorCode;
import com.mysite.board_example.error.UserDoesntExistException;
import com.mysite.board_example.repository.ArticleRepository;
import com.mysite.board_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public AddArticleResponse save(AddArticleRequest addArticleRequest){
        Optional<User> user = userRepository.findById(addArticleRequest.getUserId());
        if(user.isEmpty()){
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

    public GetAllArticlesResponse findAllArticles(){
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

}
