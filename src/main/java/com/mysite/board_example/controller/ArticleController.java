package com.mysite.board_example.controller;

import com.mysite.board_example.dto.*;
import com.mysite.board_example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final BoardService boardService;

    @PostMapping("/api/articles")
    public ResponseEntity<AddArticleResponse> saveArticle(@RequestBody AddArticleRequest addArticleRequest) {
        AddArticleResponse addArticleResponse = boardService.saveArticle(addArticleRequest);
        return new ResponseEntity<>(addArticleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<GetAllArticlesResponse> findAllArticles(){
        GetAllArticlesResponse getAllArticlesResponse = boardService.findAllArticles();
        return new ResponseEntity<>(getAllArticlesResponse, HttpStatus.OK);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<GetArticleResponse> findArticleById(@PathVariable("id") Integer id){
        GetArticleResponse articleDTO = boardService.findArticleById(id);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<UpdateArticleResponse> updateArticle(@PathVariable("id") Integer id, @RequestBody UpdateArticleRequest updateArticleRequest){
        UpdateArticleResponse updateArticleResponse = boardService.updateArticle(id, updateArticleRequest);
        return new ResponseEntity<>(updateArticleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id){
        boardService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
