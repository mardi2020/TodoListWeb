package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public String insertTodo(@RequestBody WriteTodoDTO todo) {
        todoService.insertTodo();

        return "redirect:/";

//        @PostMapping("/members/new")
//        public String create(MemberForm form) {
//            Member member = new Member();
//            member.setName(form.getName());
//
//            memberService.join(member);
//            return "redirect:/";
//        }
    }

    @GetMapping("/todo")
    public String getTodoAll(Model model) {
        List<TodoDTO> todos = todoService.getTodoAll();
        model.addAttribute("todos", todos);
        return "todo/todoList";
    }
}
