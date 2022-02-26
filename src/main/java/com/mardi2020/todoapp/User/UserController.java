package com.mardi2020.todoapp.User;


import com.mardi2020.todoapp.weather.WeatherDTO;
import com.mardi2020.todoapp.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final WeatherService weatherService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        String ip = userService.getIp2(request);
        WeatherDTO weather = weatherService.filteringInfo(ip);
        model.addAttribute("weather", weather);
        return "index";
    }

    @GetMapping("/user/signup")
    public String showInsertUserForm(Model model){
        model.addAttribute("user", new UserJoinDTO());
        return "user/register";
    }

    @PostMapping("/user/signup")
    public String insertUser(UserJoinDTO user) {
        userService.UserJoin(user);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login() {
        return "/user/login";
    }

    // 비밀번호 찾기
    @GetMapping("/user/forgot-password")
    public String getPasswordForm() {
        return "/user/forgot-password";
    }

    @PostMapping("/user/forgot-password")
    public String getPassword(String loginId) {
        userService.findPassword(loginId);
        return "redirect:/user/login";
    }


}
