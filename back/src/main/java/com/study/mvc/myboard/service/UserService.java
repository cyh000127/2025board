package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.UserDTO;

public interface UserService {
    boolean register(UserDTO user);
    UserDTO findByUsername(String username);
}
