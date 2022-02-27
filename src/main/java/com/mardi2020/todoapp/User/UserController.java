package com.mardi2020.todoapp.User;


import com.mardi2020.todoapp.covid19.Covid19DTO;
import com.mardi2020.todoapp.covid19.VirusInfoService;
import com.mardi2020.todoapp.geoLocation.GeoLocationService;
import com.mardi2020.todoapp.geoLocation.GeoResults;
import com.mardi2020.todoapp.weather.WeatherDTO;
import com.mardi2020.todoapp.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final WeatherService weatherService;

    private final VirusInfoService virusInfoService;

    private final GeoLocationService geoLocationService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String ip = userService.getIp2(request);
        GeoResults geoResults = geoLocationService.fiteringInfo(ip);
        WeatherDTO weather = weatherService.filteringInfo(geoResults.getGeoLoaction().getLat(), geoResults.getGeoLoaction().getLon());
        Covid19DTO covid19 = virusInfoService.filteringInfo(geoResults.getGeoLoaction().getR1());
        model.addAttribute("covid", covid19);
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
