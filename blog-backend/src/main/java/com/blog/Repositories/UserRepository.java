package com.blog.Repositories;

import com.blog.Entities.Users;
import com.blog.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByStatus(Status status);
    Optional<Users> findByUsername(String username);
}
