package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.model.HomeworkFile;
import com.teachit.teachItBackEnd.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepo repo;

    /*Method to create a new blog*/
    public Blog saveBlog(Blog blog) {
        //save blog via the repository
        return repo.save(blog);
    }

    /* Method to delete blog via the repository */
    public void deleteBlog(long id){
        //call repository's deleteById method and pass in supplied blog id
        repo.deleteById(id);
    }

    /* Method to get all blogs owned by a particular user */
    public List<Blog> getByEmail(String email){
        return repo.findByEmail(email);
    }

    /* Method to retrieve a blog by its id */
    public Blog findById(long id){
        Optional<Blog> blogOptional = repo.findById(id);
       return blogOptional.get();
    }
}
