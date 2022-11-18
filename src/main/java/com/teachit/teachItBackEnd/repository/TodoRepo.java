package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByEmail(String email);
}
