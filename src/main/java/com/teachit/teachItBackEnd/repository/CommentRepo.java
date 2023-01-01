package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    /* Retrieve all comments that belong to a certain  blog*/
    List<Comment> findByBlog(Blog blog);

}
