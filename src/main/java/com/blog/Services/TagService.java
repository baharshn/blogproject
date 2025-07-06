package com.blog.Services;

import com.blog.Dto.TagDto.TagRequestDto;
import com.blog.Dto.TagDto.TagResponseDto;
import com.blog.Entities.Tag;
import com.blog.Enums.Status;
import com.blog.Mappers.CategoryMapper;
import com.blog.Mappers.TagMapper;
import com.blog.Repositories.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final CategoryMapper categoryMapper;


    public TagService(TagRepository tagRepository, TagMapper tagMapper, CategoryMapper categoryMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.categoryMapper = categoryMapper;
    }

    public List<TagResponseDto> getAllTags() {
        return tagRepository.findByStatus(Status.ACTIVE)
                .stream()
                .map(tagMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TagResponseDto createTag(TagRequestDto tagRequestDto) {
        if(tagRequestDto == null){
            throw new IllegalArgumentException("TagRequestDto is null");
        }

        Tag tag = tagMapper.toEntity(tagRequestDto);
        tag.setStatus(Status.ACTIVE);
        tag.setCreatedBy("system");

        Tag saved = tagRepository.save(tag);
        return tagMapper.toDTO(saved);
    }

    public TagResponseDto updateTag(TagRequestDto tagRequestDto,Long id) {
        if(tagRequestDto == null){
            throw new IllegalArgumentException("TagRequestDto is null");
        }

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found"));

        if(tagRequestDto.getTagName()!=null){
            tag.setName(tagRequestDto.getTagName());
        }

        Tag updated = tagRepository.save(tag);
        return tagMapper.toDTO(updated);
    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        tag.setStatus(Status.PASSIVE);
        tag.setUpdatedBy("system");

        tagRepository.save(tag);
    }
}
