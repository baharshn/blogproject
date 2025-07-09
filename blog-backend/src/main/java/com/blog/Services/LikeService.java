package com.blog.Services;

import com.blog.Dto.CommentDto.CommentResponseDto;
import com.blog.Dto.LikeDto.LikeRequestDto;
import com.blog.Dto.LikeDto.LikeResponseDto;
import com.blog.Entities.Comments;
import com.blog.Entities.Like;
import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import com.blog.Enums.Status;
import com.blog.Mappers.LikeMapper;
import com.blog.Repositories.LikeRepository;
import com.blog.Repositories.PostRepository;
import com.blog.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeMapper likeMapper;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeMapper = likeMapper;
    }

    public LikeResponseDto addLike(LikeRequestDto likeRequestDto) {
        if(likeRequestDto == null){
            throw new IllegalArgumentException("likeRequestDto cannot be null");
        }

        Users user = userRepository.findById(likeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Posts post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(()-> new IllegalArgumentException("post not found"));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);
        if(alreadyLiked){
            throw new IllegalArgumentException("post already liked");
        }

        Like newLike = new Like();
        newLike.setUser(user);
        newLike.setPost(post);
        newLike.setCreatedBy(user.getUsername());

        Like saved =likeRepository.save(newLike);
        return likeMapper.toDTO(saved);
    }

    public void removeLike(LikeRequestDto dto) {
            Users user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            Posts post = postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found with id: " + dto.getPostId()));

            Like existingLike = likeRepository.findByUserAndPost(user, post)
                    .orElseThrow(() -> new RuntimeException("Like not found for this user and post"));

            existingLike.setStatus(Status.PASSIVE);
            likeRepository.save(existingLike);
            //likeRepository.delete(existingLike);
    }

    public List<LikeResponseDto> getLikesByPostId(Long postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        return likes.stream()
                .map(likeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
