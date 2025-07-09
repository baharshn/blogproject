package com.blog.Mappers;

import com.blog.Dto.CommentDto.CommentRequestDto;
import com.blog.Dto.CommentDto.CommentResponseDto;
import com.blog.Entities.Comments;
import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDto toDto(Comments comment) {
        if (comment == null) {
            return null;
        }

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setUserName(comment.getUser() != null ? comment.getUser().getUsername() : null);
        commentResponseDto.setPostTitle(comment.getPost() != null ? comment.getPost().getTitle() : null);
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setUpdatedAt(comment.getUpdatedAt());

        return commentResponseDto;
    }

    public Comments toEntity(CommentRequestDto commentRequestDto, Posts post, Users user) {
        if (commentRequestDto == null) {
            return null;
        }
        Comments comment = new Comments();
        comment.setContent(commentRequestDto.getContent());
        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }


}
