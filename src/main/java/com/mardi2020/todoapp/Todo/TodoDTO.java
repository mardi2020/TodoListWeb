package com.mardi2020.todoapp.Todo;

import lombok.Data;

@Data
public class TodoDTO {

    private String title; // 제목

    private String memo; // 내용 최대 50자 까지 허용

    private boolean star; // 중요한 내용이면 true, 아니면 일반 메모

}
