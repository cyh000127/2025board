package com.study.mvc.myboard.controller;

import com.study.mvc.myboard.dto.UserDTO;
import com.study.mvc.myboard.mapper.UserMapper;
import com.study.mvc.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO user) {
        // DTO에는 id, password, email, name 4개가 넘어옴
        return userService.registerUser(user) ? "회원가입 성공" : "회원가입 실패";
    }

    // 회원 정보 조회 (id로 조회)
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable String id) {
        // 로그인 및 상태(status) 확인 로직은 Spring Security가 처리 (가정)
        return userService.getUser(id);
    }

    // 회원 탈퇴 (Soft Delete)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        // (가정) Spring Security에서 본인 확인 또는 관리자 확인
        return userService.deleteUser(id) ? "회원 탈퇴 성공" : "회원 탈퇴 실패";
    }

    //  전체 회원 목록
    @GetMapping("/list")
    public List<UserDTO> getUserList() {
        return userService.getUserList();
    }
}