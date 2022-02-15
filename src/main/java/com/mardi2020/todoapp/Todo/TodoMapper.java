package com.mardi2020.todoapp.Todo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TodoMapper {

    // auto increment
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTodo(WriteTodoDTO todo);

    List<TodoDTO> getTodoAll();
}
