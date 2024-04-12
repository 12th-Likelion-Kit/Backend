package com.example.demo0412.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int likeCount;
}
