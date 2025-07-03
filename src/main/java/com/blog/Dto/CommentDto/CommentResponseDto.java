package com.blog.Dto.CommentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private String content;
    private String userName;
    private String postTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
