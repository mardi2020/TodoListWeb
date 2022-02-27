package com.mardi2020.todoapp.Todo;

import lombok.Data;

@Data
public class EditTodoDTO {

    private long id;

    private String title;

    private String memo;

    private boolean completed;
}
