package com.blog.Repositories;

import com.blog.Entities.Like;
import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(Users user, Posts post);
    Optional<Like> findByUserAndPost(Users user, Posts post);
    List<Like> findByPostId(Long postId);
}
