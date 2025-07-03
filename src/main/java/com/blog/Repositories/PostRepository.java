package com.blog.Repositories;

import com.blog.Entities.Posts;
import com.blog.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts,Long> {
    // Kullanıcının yazdığı postları bul
    List<Posts> findByAuthor(Users author);

    // Başlığa göre arama (ignore case)
    List<Posts> findByTitle(String title);

    // Status filtresi (BaseEntity'den geliyor)
    List<Posts> findByStatus(String status);
}
