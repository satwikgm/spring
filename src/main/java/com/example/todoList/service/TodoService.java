package com.example.todoList.service;

import com.example.todoList.entity.Customer;
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

    public List<Todo> getAllTodosByCustomer(Long customerId) {
        return todoRepository.findByCustomerId(customerId);
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo updateTodoForCustomer(Long customerId, Long todoId, Todo updatedTodo) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo not found"));
        if(!todo.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("Unauthorized operation");
        }
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setCompleted(updatedTodo.getCompleted());
        return todoRepository.save(todo);
    }

    public Todo createTodoForCustomer(Long customerId, Todo todo) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        todo.setCustomer(customer);
        return todoRepository.save(todo);
    }

    public void deleteTodoForCustomer(Long customerId, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo not found"));
        if(!todo.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("unauthorized operation");
        }
        todoRepository.delete(todo);
    }

    public List<Todo> getAllTodosByCustomerId(Long customerId) {
        return todoRepository.findByCustomerId(customerId);
    }
}
