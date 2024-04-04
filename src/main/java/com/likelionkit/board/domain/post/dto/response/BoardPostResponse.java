package com.likelionkit.board.domain.post.dto.response;


import com.likelionkit.board.domain.post.model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardPostResponse {
    private Long id;
    private String title;
    private String content;

    public BoardPostResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
