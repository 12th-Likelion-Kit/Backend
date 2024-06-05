package com.example.boardstudy.domain.board.dto;

import com.example.boardstudy.domain.board.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDto {
	private Long id;
	private String title;
	private String content;
	private String username;

	public BoardDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.username = board.getUser().getUsername();
	}
}
