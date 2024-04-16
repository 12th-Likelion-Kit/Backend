package com.example.kdh2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kdh2.dto.request.BoardRequestDto;
import com.example.kdh2.dto.response.BoardListResponseDto;
import com.example.kdh2.dto.response.BoardResponseDto;
import com.example.kdh2.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/register")
    public ResponseEntity<BoardResponseDto> registerBoard(@RequestBody BoardRequestDto dto){
        return boardService.registerBoard(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardListResponseDto>> getBoardList(){
        return boardService.getBoardList();
    }

    @GetMapping("/updateList")
    public ResponseEntity<List<BoardListResponseDto>> updateBoardByLikes(@RequestParam String order){
        return boardService.updateBoardByLikes(order);
    }

    @PatchMapping("/like/{id}")
    public ResponseEntity<Void> increaseLikeCount(@PathVariable Long id){
        return boardService.increaseLikeCount(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id){
        return boardService.deleteBoard(id);
    }
}
