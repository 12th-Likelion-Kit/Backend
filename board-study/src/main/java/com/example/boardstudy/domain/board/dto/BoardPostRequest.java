package com.example.boardstudy.domain.board.dto;

import com.example.boardstudy.domain.board.entity.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardPostRequest {
	@NotBlank
	private String title;
	@NotBlank
	private String content;

	public static Board toEntity(BoardPostRequest request) {
		return Board.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.build();
	}
}
