package com.likelionkit.board.domain.board.dto.response;


import com.likelionkit.board.domain.board.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardFindResponse {
    private Long id;

    private String title;

    private String content;

    private String userName;

    public BoardFindResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userName = board.getUser().getUserName();
    }
}
