package com.mardi2020.todoapp.Todo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TodoMapper {

    List<WriteTodoDTO> insertTodo();

    List<TodoDTO> getTodoAll();
}
