package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    List<Blog> findByEmail(String email);
}
