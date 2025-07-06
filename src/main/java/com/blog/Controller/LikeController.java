package com.blog.Controller;

import com.blog.Dto.LikeDto.LikeRequestDto;
import com.blog.Dto.LikeDto.LikeResponseDto;
import com.blog.Services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeResponseDto> addLike(@RequestBody LikeRequestDto likeRequestDto) {
        LikeResponseDto newLike = likeService.addLike(likeRequestDto);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeLike(@RequestBody LikeRequestDto likeRequestDto) {
        likeService.removeLike(likeRequestDto);
        return ResponseEntity.noContent().build();
    }
}
