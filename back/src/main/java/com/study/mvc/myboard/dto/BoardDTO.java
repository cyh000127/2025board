package com.study.mvc.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long no; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private LocalDateTime reg_date; // 게시일
    private LocalDateTime update_date; // 수정일
}
