package com.assignment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * from todo WHERE user_id = :userId", nativeQuery = true)
    List<Todo> findAllTodosForUsers(@Param("userId") String userId);

    @Query(value = "SELECT * from todo WHERE todo_id = :todoId", nativeQuery = true)
    Todo findByTodoId(@Param("todoId") Long todoId);
}
