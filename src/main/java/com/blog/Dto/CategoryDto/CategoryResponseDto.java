package com.blog.Dto.CategoryDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String categoryName;
}
