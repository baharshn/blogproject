package com.blog.Repositories;

import com.blog.Entities.Category;
import com.blog.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByStatus(Status status);
}
