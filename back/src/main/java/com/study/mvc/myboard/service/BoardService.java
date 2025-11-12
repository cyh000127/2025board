package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getBoardList();
    BoardDTO getBoard(Long no);
    boolean createBoard(BoardDTO board);
    boolean updateBoard(BoardDTO board);
    boolean deleteBoard(Long no);
}
