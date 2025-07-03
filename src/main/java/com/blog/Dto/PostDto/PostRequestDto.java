package com.blog.Dto.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private Long authorId;
    private Set<Long> categoryIds;
    private Set<Integer> tagIds;
}
