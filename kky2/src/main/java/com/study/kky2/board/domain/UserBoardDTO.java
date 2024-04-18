package com.study.kky2.board.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBoardDTO {
    private String title;
    private String author;
    private int likeNum;

    public UserBoardDTO(String title, String author, int likeNum) {
        this.title = title;
        this.author = author;
        this.likeNum = likeNum;
    }
}
