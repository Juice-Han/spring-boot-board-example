package com.mysite.board_example.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDoesntExistException.class)
    public ResponseEntity<ErrorResponse> handleUserDoesntExistException(UserDoesntExistException e) {
        log.error("handleUserDoesntExistException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ArticleDoesntExistException.class)
    public ResponseEntity<ErrorResponse> handleArticleDoesntExistException(ArticleDoesntExistException e) {
        log.error("handleArticleDoesntExistException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CommentDoesntExistException.class)
    public ResponseEntity<ErrorResponse> handleCommentDoesntExistException(CommentDoesntExistException e) {
        log.error("handleCommentDoesntExistException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CommentArticleNotMatchException.class)
    public ResponseEntity<ErrorResponse> handleCommentArticleNotMatchException(CommentArticleNotMatchException e) {
        log.error("handleCommentArticleNotMatchException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
