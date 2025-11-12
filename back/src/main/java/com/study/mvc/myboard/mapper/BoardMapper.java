package com.study.mvc.myboard.mapper;

import com.study.mvc.myboard.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll();
    BoardDTO findByNo(Long no);
    int insertBoard(BoardDTO board);
    int updateBoard(BoardDTO board);
    int deleteBoard(Long no);
}
