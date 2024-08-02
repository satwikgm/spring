package com.example.todoList.repository;

import com.example.todoList.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCustomerId(Long customerId);
}
