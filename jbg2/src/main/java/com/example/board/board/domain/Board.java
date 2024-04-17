package com.example.board.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int like;

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.like = 0;
    }

    public Board likeUp() {
        this.like++;
        return this;
    }
}
