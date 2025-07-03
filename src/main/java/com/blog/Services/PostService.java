package com.blog.Services;

import com.blog.Dto.PostDto.PostRequestDto;
import com.blog.Dto.PostDto.PostResponseDto;
import com.blog.Entities.Category;
import com.blog.Entities.Posts;
import com.blog.Entities.Tag;
import com.blog.Entities.Users;
import com.blog.Enums.Status;
import com.blog.Mappers.PostMapper;
import com.blog.Repositories.CategoryRepository;
import com.blog.Repositories.PostRepository;
import com.blog.Repositories.TagRepository;
import com.blog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(UserRepository userRepository,
                       TagRepository tagRepository,
                       CategoryRepository categoryRepository,
                       PostRepository postRepository,
                       PostMapper postMapper) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        //author bul
        Users author = userRepository.findById(postRequestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        //  Tags setini çeker
        Set<Tag> tags = new HashSet<>();
        if (postRequestDto.getTagIds() != null && !postRequestDto.getTagIds().isEmpty()) {
            tags = new HashSet<>(tagRepository.findAllById(postRequestDto.getTagIds()));
        }

        // categories setini çek
        Set<Category> categories = new HashSet<>();
        if (postRequestDto.getCategoryIds() != null && !postRequestDto.getCategoryIds().isEmpty()) {
            categories = new HashSet<>(categoryRepository.findAllById(postRequestDto.getCategoryIds()));
        }


        //Dto->entity
        Posts post = postMapper.toEntity(postRequestDto,author,tags,categories);
        post.setCreatedBy(author.getUsername());

        //db ye kaydet
        postRepository.save(post);

        //entity->responseDto
        return postMapper.toDto(post);

    }

    public PostResponseDto getPostById(Long id) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toDto(post);
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Posts post=postRepository.findById(id).orElseThrow( ()-> new RuntimeException("Post not found"));

        if (postRequestDto.getTitle() != null) post.setTitle(postRequestDto.getTitle());
        if (postRequestDto.getContent() != null) post.setContent(postRequestDto.getContent());


        if(postRequestDto.getTagIds() != null && !postRequestDto.getTagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(postRequestDto.getTagIds()));
            post.setTags(tags);
        }

        if (postRequestDto.getCategoryIds() != null) {
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(postRequestDto.getCategoryIds()));
            post.setCategories(categories);
        }
        //post.setUpdatedBy("system");

        if(postRequestDto.getAuthorId() != null) post.setUpdatedBy(post.getAuthor().getUsername());
        else{
            post.setUpdatedBy("system");
        }

        Posts updated = postRepository.save(post);
        return postMapper.toDto(postRepository.save(post));

    }

    public void deletePost(Long id) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        //postRepository.delete(post);

        post.setStatus(Status.PASSIVE);
        post.setUpdatedBy("system");
        postRepository.save(post);
    }

}
