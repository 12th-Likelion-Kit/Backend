package com.example.boardstudy.domain.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boardstudy.domain.board.dto.BoardPostRequest;
import com.example.boardstudy.domain.comment.dto.CommentPostRequest;
import com.example.boardstudy.domain.comment.service.CommentService;
import com.example.boardstudy.domain.user.entity.UserPrincipal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/post/{boardId}")
	public ResponseEntity<Void> post(
		@PathVariable Long boardId,
		@AuthenticationPrincipal UserPrincipal user,
		@RequestBody @Valid CommentPostRequest request) {

		commentService.post(user.getUserId(), boardId, request);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.build();
	}
}
