package com.example.todoList.controller;

import com.example.todoList.entity.Todo;
import com.example.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/customer/{customerId}")
    public List<Todo> getAllTodosByCustomerId(@PathVariable Long customerId) {
        return todoService.getAllTodosByCustomerId(customerId);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId) {
        Optional<Todo> todo = todoService.getTodoById(todoId);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Todo> createTodoForCustomer(@PathVariable Long customerId, @RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodoForCustomer(customerId, todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PutMapping("/user/{userId}/{todoId}")
    public ResponseEntity<Todo> updateTodoForCustomer(@PathVariable Long customerId, @PathVariable Long todoId, @RequestBody Todo updatedTodo) {
        try {
            Todo todo = todoService.updateTodoForCustomer(customerId, todoId, updatedTodo);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/customer/{customerId}/{todoId}")
    public ResponseEntity<Void> deleteTodoForUser(@PathVariable Long customerId, @PathVariable Long todoId) {
        try {
            todoService.deleteTodoForCustomer(customerId, todoId);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
