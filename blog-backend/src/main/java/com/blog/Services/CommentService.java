package com.blog.Services;

import com.blog.Dto.CommentDto.CommentRequestDto;
import com.blog.Dto.CommentDto.CommentResponseDto;
import com.blog.Entities.Comments;
import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import com.blog.Enums.Status;
import com.blog.Mappers.CommentMapper;
import com.blog.Repositories.CommentRepository;
import com.blog.Repositories.PostRepository;
import com.blog.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Users user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Posts post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comments newComment = commentMapper.toEntity(commentRequestDto,post,user);
        newComment.setCreatedBy(user.getUsername());
        Comments savedComment = commentRepository.save(newComment);
        return commentMapper.toDto(savedComment);
    }

    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentResponseDto getCommentById(Long id) {
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return commentMapper.toDto(comment);
    }

    public void deleteCommentById(Long id) {
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setStatus(Status.PASSIVE);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comments> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    //updateComment methodu da yazılabilir
}
