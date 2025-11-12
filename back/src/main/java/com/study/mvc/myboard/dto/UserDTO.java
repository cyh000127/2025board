package com.study.mvc.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;      // 아이디 (PK)
    private String password;      // 비밀번호
    private String email;         // 이메일
    private String mbti;          // MBTI
    private LocalDateTime regDate;     // 가입일
    private LocalDateTime updateDate;  // 수정일
}
