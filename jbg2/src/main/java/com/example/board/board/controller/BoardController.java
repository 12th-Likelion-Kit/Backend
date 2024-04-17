package com.example.board.board.controller;

import com.example.board.board.Dto.BoardListDto;
import com.example.board.board.Dto.BoardRequestDto;
import com.example.board.board.domain.Board;
import com.example.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public ResponseEntity<List<BoardListDto>> getBoardList() {
        return ResponseEntity.ok(boardService.findAllBoardList());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<BoardListDto>> getSortedBoardList(@RequestParam("method") String method) {
        return ResponseEntity.ok(boardService.likeSortedBoardList(method));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findBoard(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Board> boardCreate(@RequestBody BoardRequestDto request) throws IOException {
        Board board = new Board(request.getTitle(), request.getContent(), request.getWriter());
        return ResponseEntity.ok(boardService.addBoard(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> boardLike(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.updateBoardLike(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> boardDelete(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }
}
