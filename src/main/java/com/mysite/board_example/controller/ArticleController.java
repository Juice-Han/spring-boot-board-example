package com.mysite.board_example.controller;

import com.mysite.board_example.dto.AddArticleRequest;
import com.mysite.board_example.dto.AddArticleResponse;
import com.mysite.board_example.dto.GetAllArticlesResponse;
import com.mysite.board_example.dto.GetArticleDTO;
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
        AddArticleResponse addArticleResponse = boardService.save(addArticleRequest);
        return new ResponseEntity<>(addArticleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<GetAllArticlesResponse> findAllArticles(){
        GetAllArticlesResponse getAllArticlesResponse = boardService.findAllArticles();
        return new ResponseEntity<>(getAllArticlesResponse, HttpStatus.OK);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<GetArticleDTO> findArticleById(@PathVariable("id") Integer id){
        GetArticleDTO articleDTO = boardService.findArticleById(id);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }
}
