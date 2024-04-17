package com.example.board.board.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDto {
    private String title;
    private String writer;
    private int like;

    public BoardListDto(String title, String writer, int like) {
        this.title = title;
        this.writer = writer;
        this.like = like;
    }
}
