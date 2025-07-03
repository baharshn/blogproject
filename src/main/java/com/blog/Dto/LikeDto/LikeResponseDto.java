package com.blog.Dto.LikeDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponseDto {
    private Long id;
    private String userName;
    private String postTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

