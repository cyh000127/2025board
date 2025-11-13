package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.UserDTO;
import com.study.mvc.myboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    //  Spring Security의 PasswordEncoder 주입
    // private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional // 두 개의 DB 작업을 하므로 트랜잭션 처리
    public boolean registerUser(UserDTO user) {
        //  비밀번호 암호화
         String encodedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(encodedPassword);

        // 1. 회원 정보 삽입
        int userResult = userMapper.insertUser(user);

        // 2. 회원 기본 권한 (ROLE_USER) 삽입
        int authResult = userMapper.insertUserAuth(user.getId(), "ROLE_USER");

        return (userResult > 0) && (authResult > 0);
    }

    @Override
    public UserDTO getUser(String id) {
        // DTO에 권한 정보도 담아서 반환
        UserDTO user = userMapper.findById(id);
        if (user != null) {
            user.setAuthList(userMapper.readAuth(id));
        }
        return user;
    }

    @Override
    public boolean deleteUser(String id) {
        // Soft Delete 실행
        return userMapper.deleteUser(id) > 0;
    }

    @Override
    public List<UserDTO> getUserList() {
        return userMapper.findAll();
    }
}