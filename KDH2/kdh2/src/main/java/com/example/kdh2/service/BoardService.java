package com.example.kdh2.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.kdh2.dto.request.BoardRequestDto;
import com.example.kdh2.dto.response.BoardListResponseDto;
import com.example.kdh2.dto.response.BoardResponseDto;

public interface BoardService {
    public ResponseEntity<BoardResponseDto> registerBoard(BoardRequestDto dto);
    public ResponseEntity<BoardResponseDto> getBoard(Long id);
    public ResponseEntity<List<BoardListResponseDto>> getBoardList();
    public ResponseEntity<List<BoardListResponseDto>> updateBoardByLikes(String order);
    public ResponseEntity<Void> increaseLikeCount(Long id);
    public ResponseEntity<Void> deleteBoard(Long id);
}
