package com.example.boardstudy.domain.comment.dto;

import com.example.boardstudy.domain.comment.entity.Comment;

import lombok.Getter;

@Getter
public class CommentPostRequest {
	private String content;

	public static Comment toEntity(CommentPostRequest request) {
		return new Comment(request.getContent());
	}
}
