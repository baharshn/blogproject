package com.blog.Controller;

import com.blog.Dto.CategoryDto.CategoryRequestDto;
import com.blog.Dto.CategoryDto.CategoryResponseDto;
import com.blog.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Yeni kategori oluşturur
     * POST /api/categories
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto dto) {
        CategoryResponseDto created = categoryService.createCategory(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Tüm aktif kategorileri getirir
     * GET /api/categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     *  Kategori güncelle
     * PUT /api/categories/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto dto) {
        CategoryResponseDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     *  Kategoriyi soft delete yap
     * DELETE /api/categories/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

