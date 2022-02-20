package com.mardi2020.todoapp.Todo;

import com.mardi2020.todoapp.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * html form 띄우기
     */
    @GetMapping("/todo/new")
    public String showTodoInsertForm(Model model, Principal principal) {
        model.addAttribute("todo", new WriteTodoDTO());
        return "todo/todoForm";
    }

    /**
     * Todo 디비에 삽입
     */
    @PostMapping("/todo/new")
    public String InsertTodo(Model model, WriteTodoDTO todo, Principal principal) {
        System.out.println("todo = " + todo);
        long id = todoService.loadUserPK(principal.getName());
        todo.setUserId(id);
        todoService.insertTodo(todo);
        model.addAttribute("todo", new WriteTodoDTO());
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

    // 로그인 후 메인 페이지
    @GetMapping("/todo/main")
    public String getDate(Model model) {
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String today = format1.format(time).substring(0, 10);
        model.addAttribute("today", today);
        System.out.println("today = " + today);

        List<DateDTO> todos = todoService.getTodoByDate();
        model.addAttribute("dates", todos);

        return "/todo/todoDayList";
    }

    @GetMapping("/todo/{date}")
    public String getTodoAllByDate(Model model, @PathVariable String date) {
        List<TodoDTO> todos = todoService.getTodoAllByDate(date);
        SimpleDateFormat ParseDate = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (TodoDTO todo : todos) {
            todo.setStringDate(ParseDate.format(todo.getWritten_date()).substring(0, 10));
        }

        model.addAttribute("date", date);
        model.addAttribute("todos", todos);

        SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String today = format1.format(time).substring(0, 10);
        if (today.equals(date)){
            return "todo/todayTodoList";
        }
        else
            return "todo/todoList";
    }
}
