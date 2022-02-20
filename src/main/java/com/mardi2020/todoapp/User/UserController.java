package com.mardi2020.todoapp.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Principal principal) {
        System.out.println("principal = " + principal);
        return "index";
    }

    @GetMapping("/user/signup")
    public String showInsertUserForm(Model model){
        model.addAttribute("user", new UserJoinDTO());
        return "user/joinForm";
    }

    @PostMapping("/user/signup")
    public String insertUser(UserJoinDTO user) {
        userService.UserJoin(user);
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login() {
        return "/user/loginForm";
    }

}
