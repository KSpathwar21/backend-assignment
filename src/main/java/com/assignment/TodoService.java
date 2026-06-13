package com.assignment;

import com.assignment.CustomException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;


    public Todo createTodoEntry(Todo todo){
        Todo todo1 = new Todo();

        todo1.setTitle(todo.getTitle());
        todo1.setDescription(todo.getDescription());
        todo1.setStatus(todo.getStatus());
        todo1.setUserId(todo.getUserId());
        todo1.setCreatedAt(LocalDateTime.now());
        todo1.setUpdatedAt(LocalDateTime.now());

        return repo.save(todo1);
    }

    public Todo getByTodoId(Long id){
       return repo.findByTodoId(id);
    }

    @Cacheable(value = "user_todos", key = "#userId")
    public List<Todo> getAllTodosByUserId(String userId){
        return repo.findAllTodosForUsers(userId);
    }

    public Todo updateTodoById(Long id, String status){
        Todo todo = getByTodoId(id);
        if(todo==null){
            throw new ResourceNotFoundException("Resource not found for id" + id);
        }

        todo.setStatus(status);
        return repo.save(todo);
    }

}
