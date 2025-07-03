package com.blog.Dto.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private String title;
    private String content;
    private String author_username;
    private Set<String> categoryNames;
    private Set<String> tagNames;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
