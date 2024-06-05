package com.example.boardstudy.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardstudy.domain.board.dto.BoardDto;
import com.example.boardstudy.domain.board.dto.BoardPostRequest;
import com.example.boardstudy.domain.board.entity.Board;
import com.example.boardstudy.domain.board.repository.BoardRepository;
import com.example.boardstudy.domain.user.entity.User;
import com.example.boardstudy.domain.user.repository.UserRepository;
import com.example.boardstudy.global.base.exception.CustomException;
import com.example.boardstudy.global.base.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void post(Long userId, BoardPostRequest request) {
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		Board board = BoardPostRequest.toEntity(request);
		board.addUser(user);
		boardRepository.save(board);
	}

	public BoardDto findBoard(Long boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("보드 없음"));
		return new BoardDto(board);
	}

	public Page<BoardDto> findAllBoard(Pageable pageable) {
		Page<Board> boards = boardRepository.findAll(pageable);

		return boards.map(BoardDto::new);
	}

	@Transactional
	public void delete(Long boardId, Long userId) {
		// 게시글 정보를 받아와서 실제 게시글의 작성자인지 확인하는 작업이 필요

		// 해당 보드에 달린 댓글을 모두 삭제

		boardRepository.deleteById(boardId);
	}
}
