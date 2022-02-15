package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * html form 띄우기
     * @param model
     * @param todo
     * @return
     */
    @GetMapping("/todo/new")
    public String showTodoInsertForm() {
        return "todo/todoForm";
    }

    /**
     * Todo 디비에 삽입
     */
    @PostMapping("/todo/new")
    public String InsertTodo(WriteTodoDTO todo) {
        todoService.insertTodo(todo);
        return "todo/todoForm";
    }

    @GetMapping
    public String getTodoAll(Model model) {
        List<TodoDTO> todos = todoService.getTodoAll();
        model.addAttribute("todos", todos);

        return "index";
    }
}
