package com.example.kdh2.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.kdh2.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int likeCount;

    public BoardResponseDto(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.writer = boardEntity.getWriter();
        this.likeCount = boardEntity.getLikeCount();
    }

    public static ResponseEntity<BoardResponseDto> registerSuccess(BoardEntity boardEntity) {
        BoardResponseDto responseBody = new BoardResponseDto(boardEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    public static ResponseEntity<BoardResponseDto> getSuccess(BoardEntity boardEntity) {
        BoardResponseDto responseBody = new BoardResponseDto(boardEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    public static ResponseEntity<BoardResponseDto> fail() {
        return ResponseEntity.notFound().build();
    }
}
