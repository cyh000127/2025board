package com.study.mvc.myboard.controller;

import com.study.mvc.myboard.dto.BoardDTO;
import com.study.mvc.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시글 조회
    @GetMapping("/list")
    public List<BoardDTO> getBoardList() {
        return boardService.getBoardList();
    }

    // 게시글 단건 조회
    @GetMapping("/{no}")
    public BoardDTO getBoard(@PathVariable Long no) {
        return boardService.getBoard(no);
    }

    // 게시글 작성
    @PostMapping("/create")
    public String createBoard(@RequestBody BoardDTO board) {
        return boardService.createBoard(board) ? "게시글 작성 성공!" : "게시글 작성 실패";
    }

    // 게시글 수정
    @PutMapping("/update")
    public String updateBoard(@RequestBody BoardDTO board) {
        return boardService.updateBoard(board) ? "게시글 수정 성공!" : "게시글 수정 실패";
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{no}")
    public String deleteBoard(@PathVariable Long no) {
        return boardService.deleteBoard(no) ? "게시글 삭제 성공!" : "게시글 삭제 실패";
    }
}
