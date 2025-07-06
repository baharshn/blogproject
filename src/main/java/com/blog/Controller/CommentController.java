package com.blog.Controller;


import com.blog.Dto.CommentDto.CommentRequestDto;
import com.blog.Dto.CommentDto.CommentResponseDto;
import com.blog.Services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto newComment = commentService.createComment(commentRequestDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        List <CommentResponseDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable int id, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto updatedComment = commentService.updateComment();
        return ResponseEntity.ok(updatedComment);
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        CommentResponseDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }
}
