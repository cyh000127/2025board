package com.study.mvc.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;  //id
    private String password; // pw
    private String email; // 이메일
    private String name; // 이름

    //  'status' 컬럼
    private String status; // "ACTIVE", "DELETED", "BANNED"

    private LocalDateTime reg_date;
    private LocalDateTime update_date;

    // 권한 정보를 담을 리스트 (tbl_member_auth)
    private List<String> authList;
}