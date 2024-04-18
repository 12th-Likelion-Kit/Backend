package com.study.kky2.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private long id;
    private String title;
    private String content;
    private String author;
    private int likeNum;
}


