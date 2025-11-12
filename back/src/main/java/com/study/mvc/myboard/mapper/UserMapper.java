package com.study.mvc.myboard.mapper;

import com.study.mvc.myboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(UserDTO user);
    UserDTO findByUsername(String username);

    List<UserDTO> findAll();
}
