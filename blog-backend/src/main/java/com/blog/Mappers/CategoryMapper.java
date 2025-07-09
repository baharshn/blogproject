package com.blog.Mappers;


import com.blog.Dto.CategoryDto.CategoryRequestDto;
import com.blog.Dto.CategoryDto.CategoryResponseDto;
import com.blog.Entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    /**
     * RequestDTO -> Entity
     * Yeni kategori oluştururken kullanılacak
     */
    public Category toEntity(CategoryRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setName(dto.getCategoryName());
        return category;
    }

    /**
     * Entity -> ResponseDTO
     * Client'a veri dönerken kullanılacak
     */
    public CategoryResponseDto toDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setCategoryName(category.getName());
        return dto;
    }
}

