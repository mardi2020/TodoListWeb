package com.mardi2020.todoapp.Todo;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final SqlSession sqlSession;

    @Transactional
    public void insertTodo(WriteTodoDTO todo) {
        sqlSession.insert("insertTodo", todo);
    }

    public List<TodoDTO> getTodoAll() {
        return sqlSession.selectList("getTodoAll");
    }

    @Transactional
    public void deleteTodo(long id) {
        sqlSession.delete("deleteTodo", id);
    }

    @Transactional
    public void updateTodo(EditTodoDTO todo) {
        sqlSession.update("updateTodo", todo);
    }

    public List<DateDTO> getTodoByDate(long userId){
        return sqlSession.selectList("getTodoByDate", userId);
    }

    public List<TodoDTO> getTodoAllByDate(String date, long userId) {
        HashMap map = new HashMap();
        map.put("date", date);
        map.put("userId", userId);
        return sqlSession.selectList("getTodoAllByDate", map);
    }

    public long loadUserPK(String loginId) {
        return sqlSession.selectOne("findUserPKByLoginId", loginId);
    }

    public long getTotalCompletedTodo(long id){
        return sqlSession.selectOne("getTotalCompletedTodo", id);
    }

    public long getTotalNotCompletedTodo(long id) {
        return sqlSession.selectOne("getTotalNotCompletedTodo", id);
    }

    public long getTotalCompletedNumber(long id) {
        return sqlSession.selectOne("getTotalCompletedNumber", id);
    }

    public TodoDTO getTodoByKey(long id) {
        return sqlSession.selectOne("getTodoByKey", id);
    }
}
