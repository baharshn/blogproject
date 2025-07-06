package com.blog.Repositories;

import com.blog.Entities.Category;
import com.blog.Entities.Tag;
import com.blog.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByStatus(Status status);
}
