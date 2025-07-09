package com.blog.Services;

import com.blog.Dto.CategoryDto.CategoryRequestDto;
import com.blog.Dto.CategoryDto.CategoryResponseDto;
import com.blog.Entities.Category;
import com.blog.Enums.Status;
import com.blog.Mappers.CategoryMapper;
import com.blog.Repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Yeni kategori oluşturur.
     */
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CategoryRequestDTO is null");
        }

        Category category = categoryMapper.toEntity(dto);
        category.setCreatedBy("system");  // audit

        Category saved = categoryRepository.save(category);
        return categoryMapper.toDTO(saved);
    }

    /**
     * Tüm ACTIVE kategorileri listeler.
     */
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findByStatus(Status.ACTIVE)
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Kategori günceller.
     */
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (dto.getCategoryName() != null) {
            category.setName(dto.getCategoryName());
        }

        category.setUpdatedBy("system");

        Category updated = categoryRepository.save(category);
        return categoryMapper.toDTO(updated);
    }

    /**
     * Soft delete: Status = PASSIVE yap.
     */
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        category.setStatus(Status.PASSIVE);
        category.setUpdatedBy("system");

        categoryRepository.save(category);
    }
}
