package com.likelionkit.board.domain.board.dto.request;

import com.likelionkit.board.domain.board.model.Board;
import lombok.Getter;

@Getter
public class BoardPostRequest {

    private String title;

    private String content;

    public static Board toEntity(BoardPostRequest request) {
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}
