package com.mardi2020.todoapp.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void UserJoin(UserJoinDTO user);

    User findUserByLoginId(String loginId);

}
