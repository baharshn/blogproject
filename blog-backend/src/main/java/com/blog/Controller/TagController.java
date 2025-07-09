package com.blog.Controller;

import com.blog.Dto.CategoryDto.CategoryRequestDto;
import com.blog.Dto.CategoryDto.CategoryResponseDto;
import com.blog.Dto.TagDto.TagRequestDto;
import com.blog.Dto.TagDto.TagResponseDto;
import com.blog.Services.CategoryService;
import com.blog.Services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;


    @PostMapping
    public ResponseEntity<TagResponseDto> createTag(@RequestBody TagRequestDto dto) {
        TagResponseDto created = tagService.createTag(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        List<TagResponseDto> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDto> updateTag(
            @PathVariable Long id,
            @RequestBody TagRequestDto dto) {
        TagResponseDto updated = tagService.updateTag(dto, id);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
