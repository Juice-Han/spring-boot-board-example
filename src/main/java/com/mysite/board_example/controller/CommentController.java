package com.mysite.board_example.controller;

import com.mysite.board_example.dto.AddCommentRequest;
import com.mysite.board_example.dto.AddCommentResponse;
import com.mysite.board_example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;

    @PostMapping("/api/articles/{id}/comment")
    public ResponseEntity<AddCommentResponse> saveComment(@PathVariable("id") Integer id, @RequestBody AddCommentRequest addCommentRequest) {
        AddCommentResponse addCommentResponse = boardService.saveComment(id, addCommentRequest);
        return new ResponseEntity<>(addCommentResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/articles/{article_id}/comment/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("article_id") Integer articleId, @PathVariable("comment_id") Integer commentId){
        boardService.deleteComment(articleId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
