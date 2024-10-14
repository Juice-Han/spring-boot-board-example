package com.mysite.board_example.controller;

import com.mysite.board_example.dto.AddArticleRequest;
import com.mysite.board_example.dto.AddArticleResponse;
import com.mysite.board_example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final BoardService boardService;

    @PostMapping("/api/articles")
    public ResponseEntity<AddArticleResponse> saveArticle(@RequestBody AddArticleRequest addArticleRequest) {
        AddArticleResponse addArticleResponse = boardService.save(addArticleRequest);
        return new ResponseEntity<>(addArticleResponse, HttpStatus.CREATED);
    }
}
