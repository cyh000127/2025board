package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.UserDTO;
import com.study.mvc.myboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public boolean register(UserDTO user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false; // 중복 아이디 방지
        }
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
