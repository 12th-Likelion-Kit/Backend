package com.likelionkit.board.domain.comment.controller;


import com.likelionkit.board.domain.comment.dto.request.CommentCreateRequest;
import com.likelionkit.board.domain.comment.dto.response.CommentResponse;
import com.likelionkit.board.domain.comment.service.CommentService;
import com.likelionkit.board.domain.user.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/{boardId}")
    public ResponseEntity<Void> create(@RequestBody CommentCreateRequest request,
                                 @PathVariable Long boardId,
                                 @AuthenticationPrincipal UserPrincipal user) {
        commentService.create(request, boardId, user.getUserId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(value = "/{boardId}")
    public ResponseEntity<List<CommentResponse>> getCommentByBoardId(@PathVariable Long boardId) {
        List<CommentResponse> response = commentService.getCommentByBoardId(boardId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // 수정

}
