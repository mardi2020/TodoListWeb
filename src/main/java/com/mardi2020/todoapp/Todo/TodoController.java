package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

//    @GetMapping
//    public String getTodoAll(Model model) {
//        List<TodoDTO> todos = todoService.getTodoAll();
//        // YYYY-MM-DD hh:mm:ss
//        SimpleDateFormat ParseDate = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        for (TodoDTO todo : todos) {
//            // 시간은 필요없으므로 날짜만 슬라이싱
//            todo.setStringDate(ParseDate.format(todo.getWritten_date()).substring(0, 10));
//        }
//        model.addAttribute("todos", todos);
//
//        return "index";
//    }

    @PostMapping("/todo/delete/{id}")
    public String deleteTodoById(@PathVariable long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public String EditTodoPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        return "todo/todoEdit";
    }

    @PostMapping("/todo/edit/{id}")
    public String EditTodoById(EditTodoDTO todo) {
        todoService.updateTodo(todo);
        return "redirect:/";
    }

    // 메인 페이지
    @GetMapping
    public String getDate(Model model) {
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String today = format1.format(time).substring(0, 10);
        model.addAttribute("today", today);
        System.out.println("today = " + today);

        List<DateDTO> todos = todoService.getTodoByDate();
        model.addAttribute("dates", todos);
        return "index";
    }

    @GetMapping("/todo/{date}")
    public String getTodoAllByDate(Model model, @PathVariable String date) {
        List<TodoDTO> todos = todoService.getTodoAllByDate(date);
        SimpleDateFormat ParseDate = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (TodoDTO todo : todos) {
            // 시간은 필요없으므로 날짜만 슬라이싱
            todo.setStringDate(ParseDate.format(todo.getWritten_date()).substring(0, 10));
        }
        System.out.println("todos = " + todos);
        model.addAttribute("date", date);
        model.addAttribute("todos", todos);

        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String today = format1.format(time).substring(0, 10);
        if (today.equals(date)){
            return "todo/todayTodoList";
        }
        else
            return "todo/todoList";
    }
}
