package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.Todo;
import com.teachit.teachItBackEnd.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepo todoRepo;

    public Todo save(Todo todo) {
        //save to-do via the repository
        return todoRepo.save(todo);
    }

    public List<Todo> findByEmail(String email) {
        return todoRepo.findByEmail(email);
    }

    public Todo findById(long id) {
        return todoRepo.findById(id).get();
    }

    public void deleteById(long id) {
        todoRepo.deleteById(id);
    }


}
