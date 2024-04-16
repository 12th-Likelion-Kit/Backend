package com.example.kdh2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int likeCount;

    public BoardEntity(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.likeCount = 0;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }
}
