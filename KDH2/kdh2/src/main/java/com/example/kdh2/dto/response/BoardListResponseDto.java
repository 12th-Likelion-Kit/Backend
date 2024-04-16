package com.example.kdh2.dto.response;

import com.example.kdh2.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardListResponseDto {
    private String title;
    private String writer;
    private int likeCount;

    public BoardListResponseDto(BoardEntity boardEntity) {
        this.title = boardEntity.getTitle();
        this.writer = boardEntity.getWriter();
        this.likeCount = boardEntity.getLikeCount();
    }
}
