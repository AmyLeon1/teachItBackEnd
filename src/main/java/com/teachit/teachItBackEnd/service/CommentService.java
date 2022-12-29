package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    /* save comment via the repository */
    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    /* Retrieve all comments for a particular blog via the repository */
    public List<Comment> getByBlog(Blog blog){
        return commentRepo.findByBlog(blog);
    }
}
