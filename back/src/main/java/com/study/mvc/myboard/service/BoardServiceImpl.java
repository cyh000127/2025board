package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.BoardDTO;
import com.study.mvc.myboard.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return boardMapper.findAll();
    }

    @Override
    public BoardDTO getBoard(Long no) {
        return boardMapper.findByNo(no);
    }

    @Override
    public boolean createBoard(BoardDTO board) {
        return boardMapper.insertBoard(board) > 0;
    }

    @Override
    public boolean updateBoard(BoardDTO board) {
        return boardMapper.updateBoard(board) > 0;
    }

    @Override
    public boolean deleteBoard(Long no) {
        return boardMapper.deleteBoard(no) > 0;
    }
}
