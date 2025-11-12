package com.study.mvc.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime reg_date;
    private LocalDateTime update_date;
}
