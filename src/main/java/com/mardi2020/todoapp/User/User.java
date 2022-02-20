package com.mardi2020.todoapp.User;

import lombok.Data;

@Data
public class User {

    private long id;

    private String loginId;

    private String password;

    private String nickname;

}