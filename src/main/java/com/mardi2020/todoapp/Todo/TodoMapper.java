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

    void deleteTodo(long id);

    void updateTodo(EditTodoDTO todo);

    List<DateDTO> getTodoByDate(long userId);

    List<TodoDTO> getTodoAllByDate(String date, long userId);

    long findUserPKByLoginId(String loginId);

    long getTotalCompletedTodo(long id);

    long getTotalNotCompletedTodo(long id);

    long getTotalCompletedNumber(long id);

    TodoDTO getTodoByKey();
}
