package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * html form 띄우기
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
        // YYYY-MM-DD hh:mm:ss
        SimpleDateFormat ParseDate = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (TodoDTO todo : todos) {
            todo.setStringDate(ParseDate.format(todo.getWritten_date()));
        }
        model.addAttribute("todos", todos);

        return "index";
    }

    @PostMapping("/todo/delete/{id}")
    public String deleteTodoById(@PathVariable long id) {
        todoService.deleteTodo(id);
        System.out.println("deleteId = " + id);
        return "redirect:/";
    }
}
