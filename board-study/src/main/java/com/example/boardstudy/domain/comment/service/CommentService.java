package com.example.boardstudy.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardstudy.domain.board.entity.Board;
import com.example.boardstudy.domain.board.repository.BoardRepository;
import com.example.boardstudy.domain.comment.dto.CommentPostRequest;
import com.example.boardstudy.domain.comment.entity.Comment;
import com.example.boardstudy.domain.comment.repostiory.CommentRepository;
import com.example.boardstudy.domain.user.entity.User;
import com.example.boardstudy.domain.user.repository.UserRepository;
import com.example.boardstudy.global.base.exception.CustomException;
import com.example.boardstudy.global.base.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public void post(Long userId, Long boardId, CommentPostRequest request) {
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("보드 없음"));
		Comment comment = CommentPostRequest.toEntity(request);
		comment.addUser(user);
		comment.addBoard(board);
		commentRepository.save(comment);
	}
}
