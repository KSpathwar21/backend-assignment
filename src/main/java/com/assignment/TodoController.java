package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService service;


    @PostMapping
    ResponseEntity<?> createTodos(@RequestBody Todo todo, @RequestHeader List <String> authorization){
        String role = authorization.get(0);
        if(role.equals("USER")){
            String key = authorization.get(1);
            if(!key.equals(todo.getUserId())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authorized");
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodoEntry(todo));

    }

    @GetMapping("/me")
    ResponseEntity<List<Todo>> getTodoForUser(@RequestHeader List <String> authorization){
        String userId = authorization.get(1);
        return ResponseEntity.ok(service.getAllTodosByUserId(userId));
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<?> getTodoByUserId(@PathVariable String userId, @RequestHeader List <String> authorization){
        String role = authorization.get(0);
        if(!role.equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not Authorized");
        }
        return ResponseEntity.ok(service.getAllTodosByUserId(userId));
    }

    @PutMapping("/{id}/status")
    ResponseEntity<?> updateTodoStatus(@PathVariable Long id, @RequestBody String status,@RequestHeader List <String> authorization){
        String role = authorization.get(0);
        Todo todo = service.getByTodoId(id);
        if(role.equals("USER")){
            String key = authorization.get(1);
            if(!key.equals(todo.getUserId())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authorized");
            }
        }
        return ResponseEntity.ok(service.updateTodoById(id, status));

    }

}
