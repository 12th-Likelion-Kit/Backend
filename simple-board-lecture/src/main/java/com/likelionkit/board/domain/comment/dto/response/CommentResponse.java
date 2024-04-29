package com.likelionkit.board.domain.comment.dto.response;

import com.likelionkit.board.domain.comment.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String content;
    private String username;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();;
        this.content = comment.getContent();
        this.username = comment.getUser().getUserName();
    }
}
