package com.blog.Mappers;

import com.blog.Dto.LikeDto.LikeRequestDto;
import com.blog.Dto.LikeDto.LikeResponseDto;
import com.blog.Entities.Like;
import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    /**
     * REQUEST DTO -> ENTITY
     * Yeni Like oluştururken kullanılır.
     */
    public Like toEntity(LikeRequestDto dto, Users user, Posts post) {
        if (dto == null || user == null || post == null) {
            return null;
        }
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        return like;
    }

    /**
     * ENTITY -> RESPONSE DTO
     * Client'a veri dönerken kullanılır.
     */
    public LikeResponseDto toDTO(Like like) {
        if (like == null) {
            return null;
        }
        LikeResponseDto dto = new LikeResponseDto();
        dto.setId(like.getId());
        dto.setUserName(like.getUser() != null ? like.getUser().getUsername() : null);
        dto.setPostTitle(like.getPost() != null ? like.getPost().getTitle() : null);
        dto.setCreatedAt(like.getCreatedAt());
        dto.setUpdatedAt(like.getUpdatedAt());
        return dto;
    }
}

