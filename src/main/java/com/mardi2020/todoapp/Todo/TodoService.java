package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper todoMapper;

    public List<WriteTodoDTO> insertTodo() {
        return todoMapper.insertTodo();
    }

    public List<TodoDTO> getTodoAll() {
        return todoMapper.getTodoAll();
    }
}
