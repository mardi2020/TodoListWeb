package com.mardi2020.todoapp.User;

import lombok.Data;

@Data
public class UserJoinDTO {

    private long id; // primary key

    private String loginId;

    private String password;

    private String nickname;
}
