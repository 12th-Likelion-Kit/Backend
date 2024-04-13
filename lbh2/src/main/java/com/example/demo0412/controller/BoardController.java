package com.example.demo0412.controller;

import com.example.demo0412.dto.BoardDTO;
import com.example.demo0412.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/all")
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/")
    public BoardDTO getBoardById(@RequestParam Long id) {
        return boardService.getBoardById(id);
    }

    @GetMapping("/sort")
    public List<BoardDTO> getBoardList(@RequestParam boolean asc) {
        return boardService.getBoardsSortedByLikesCount(asc);
    }

    @PostMapping("/likeup")
    public BoardDTO likeUp(@RequestParam Long id) {
        return boardService.likeCountUp(id);
    }

    @DeleteMapping("/")
    public void deleteBoardById(@RequestParam Long id) {
        boardService.deleteBoardById(id);
    }
}
