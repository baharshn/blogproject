package com.blog.Mappers;


import com.blog.Dto.TagDto.TagRequestDto;
import com.blog.Dto.TagDto.TagResponseDto;
import com.blog.Entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    /**
     * REQUEST DTO -> ENTITY
     * Yeni tag oluştururken veya güncellerken kullanılır.
     */
    public Tag toEntity(TagRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setName(dto.getTagName());
        return tag;
    }

    /**
     * ENTITY -> RESPONSE DTO
     * Client'a veri dönerken kullanılır.
     */
    public TagResponseDto toDTO(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagResponseDto dto = new TagResponseDto();
        dto.setId(tag.getId());
        dto.setTagName(tag.getName());
        return dto;
    }
}
