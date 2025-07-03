package com.blog.Mappers;

import com.blog.Dto.PostDto.PostRequestDto;
import com.blog.Dto.PostDto.PostResponseDto;
import com.blog.Entities.Category;
import com.blog.Entities.Posts;
import com.blog.Entities.Tag;
import com.blog.Entities.Users;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostMapper {


    //postları kullanıcıya getirmek için
    public PostResponseDto toDto(Posts post){
        if(post==null)
            return null;
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setContent(post.getContent());
        postResponseDto.setAuthor_username(post.getAuthor().getUsername());

        // Categories
        if (post.getCategories() != null) {
            postResponseDto.setCategoryNames(
                    post.getCategories()
                            .stream()
                            .map(Category::getName)
                            .collect(Collectors.toSet())
            );
        }

        // Categories
        if (post.getTags() != null) {
            postResponseDto.setTagNames(
                    post.getTags()
                            .stream()
                            .map(Tag::getName)
                            .collect(Collectors.toSet())
            );
        }

        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setUpdatedAt(post.getUpdatedAt());

        return postResponseDto;

    }

    //yeni post kayıt
    public Posts toEntity(PostRequestDto postResponseDto, Users author, Set<Tag> tags, Set <Category> categories){
        if (postResponseDto==null)
            return null;

        Posts post = new Posts();
        post.setTitle(postResponseDto.getTitle());
        post.setContent(postResponseDto.getContent());
        post.setAuthor(author);
        post.setTags(tags);
        post.setCategories(categories);

        return post;
    }


    //var olan postun güncellenmesi
    public void updateEntity(Posts post ,PostRequestDto postRequestDto, Users author, Set<Tag> tags, Set <Category> categories){
        if (postRequestDto.getTitle() != null) {
            post.setTitle(postRequestDto.getTitle());
        }
        if (postRequestDto.getContent() != null) {
            post.setContent(postRequestDto.getContent());
        }
        if (author != null) {
            post.setAuthor(author);
        }
        if (categories != null) {
            post.setCategories(categories);
        }
        if (tags != null) {
            post.setTags(tags);
        }
    }
}
