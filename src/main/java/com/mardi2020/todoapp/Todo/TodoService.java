package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

//    private final TodoMapper todoMapper;

    private final SqlSession sqlSession;

    public void insertTodo(WriteTodoDTO todo) {
//        todoMapper.insertTodo(todo);
        sqlSession.insert("insertTodo", todo);
    }

    public List<TodoDTO> getTodoAll() {
//        return todoMapper.getTodoAll();
        return sqlSession.selectList("getTodoAll");
    }

    public void deleteTodo(long id) {
//        todoMapper.deleteTodo(id);
        sqlSession.delete("deleteTodo", id);
    }

    public void updateTodo(EditTodoDTO todo) {
//        todoMapper.updateTodo(todo);
        sqlSession.update("updateTodo", todo);
    }

    public List<DateDTO> getTodoByDate(){
//        return todoMapper.getTodoByDate();
        return sqlSession.selectList("getTodoByDate");
    }

    public List<TodoDTO> getTodoAllByDate(String date) {
//        return todoMapper.getTodoAllByDate(date);
        return sqlSession.selectList("getTodoAllByDate", date);
    }

    public long loadUserPK(String loginId) {
        return sqlSession.selectOne("findUserPKByLoginId", loginId);
    }
}
