package com.sigvetl.twoDog.mapper;

import com.sigvetl.twoDog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> getUsers();

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT userid FROM users WHERE username = #{username}")
    Integer getUserId(String username);

    @Select("SELECT * FROM users WHERE userid = #{userId}")
    User getUserFromId(Integer userId);

    @Insert("INSERT into Users(username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);
}