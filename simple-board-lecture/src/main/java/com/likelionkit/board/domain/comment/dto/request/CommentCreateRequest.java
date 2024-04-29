package com.likelionkit.board.domain.comment.dto.request;

import com.likelionkit.board.domain.comment.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;

    public static Comment toEntity(CommentCreateRequest request) {
        return new Comment(request.getContent());
    }
}
