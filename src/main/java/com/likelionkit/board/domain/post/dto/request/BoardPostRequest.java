package com.likelionkit.board.domain.post.dto.request;

import com.likelionkit.board.domain.post.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
