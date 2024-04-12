package com.example.demo0412.dto;


import com.example.demo0412.domain.Board;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int likeCount;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getAuthor();
        this.likeCount = board.getLikeCount();
    }
}
