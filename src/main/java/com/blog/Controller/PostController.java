package com.blog.Controller;

import com.blog.Dto.CategoryDto.CategoryResponseDto;
import com.blog.Dto.CommentDto.CommentResponseDto;
import com.blog.Dto.LikeDto.LikeResponseDto;
import com.blog.Dto.PostDto.PostRequestDto;
import com.blog.Dto.PostDto.PostResponseDto;
import com.blog.Dto.TagDto.TagResponseDto;
import com.blog.Services.CommentService;
import com.blog.Services.LikeService;
import com.blog.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        PostResponseDto post = postService.createPost(postRequestDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostRequestDto postRequestDto,@PathVariable Long id) {
        PostResponseDto updatedPost = postService.updatePost(id, postRequestDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<List<LikeResponseDto>> getLikesByPostId(@PathVariable Long postId) {
        List<LikeResponseDto> likes = likeService.getLikesByPostId(postId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{postId}/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesByPostId(@PathVariable Long postId) {
        List<CategoryResponseDto> categories = postService.getCategoriesByPostId(postId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{postId}/tags")
    public ResponseEntity<List<TagResponseDto>> getTagsByPostId(@PathVariable Long postId) {
        List<TagResponseDto> tags = postService.getTagsByPostId(postId);
        return ResponseEntity.ok(tags);
    }


}
