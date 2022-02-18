package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper todoMapper;

    public void insertTodo(WriteTodoDTO todo) {
        todoMapper.insertTodo(todo);
    }

    public List<TodoDTO> getTodoAll() {
        return todoMapper.getTodoAll();
    }

    public void deleteTodo(long id) {
        todoMapper.deleteTodo(id);
    }
}
