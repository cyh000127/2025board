package com.study.mvc.myboard.controller;

import com.study.mvc.myboard.dto.UserDTO;
import com.study.mvc.myboard.mapper.UserMapper;
import com.study.mvc.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        boolean success = userService.register(user);
        System.out.println("회원가입 성공");
        return success ? "회원가입 성공!" : "이미 존재하는 아이디입니다.";
    }

    @GetMapping("/{username}")
    public UserDTO getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

//    @GetMapping("/test-db")
//    public String testDB() {
//        try {
//            userMapper.findAll(); // UserMapper.xml에 간단한 select문 필요
//            return "✅ DB 연결 성공!";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "❌ DB 연결 실패: " + e.getMessage();
//        }
    }


