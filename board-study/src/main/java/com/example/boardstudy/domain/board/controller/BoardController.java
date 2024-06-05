package com.example.boardstudy.domain.board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boardstudy.domain.board.dto.BoardDto;
import com.example.boardstudy.domain.board.dto.BoardPostRequest;
import com.example.boardstudy.domain.board.service.BoardService;
import com.example.boardstudy.domain.user.entity.UserPrincipal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/post")
	public ResponseEntity<Void> post(@AuthenticationPrincipal UserPrincipal user, @RequestBody @Valid BoardPostRequest request) {
		boardService.post(user.getUserId(), request);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.build();
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardDto> findBoard(@PathVariable Long boardId) {
		BoardDto response = boardService.findBoard(boardId);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@GetMapping
	public ResponseEntity<Page<BoardDto>> findAllBoard(@PageableDefault(size = 10, page = 0)Pageable pageable) {
		Page<BoardDto> response = boardService.findAllBoard(pageable);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Void> delete(@PathVariable Long boardId, @AuthenticationPrincipal UserPrincipal user) {
		boardService.delete(boardId, user.getUserId());

		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}
}
