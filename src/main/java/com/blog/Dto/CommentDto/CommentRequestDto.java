package com.blog.Dto.CommentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

