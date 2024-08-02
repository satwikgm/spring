package com.example.todoList.service;

import com.example.todoList.entity.Todo;
import com.example.todoList.repository.CustomerRepository;
import com.example.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    public CustomerRepository customerRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }


    public List<Todo> getAllTodosByCustomerId(Long customerId) {
        return todoRepository.findByCustomerId(customerId);
    }
}
