package com.likelionkit.board.domain.post.service;

import com.likelionkit.board.domain.post.dto.request.BoardPostRequest;
import com.likelionkit.board.domain.post.dto.response.BoardFindResponse;
import com.likelionkit.board.domain.post.dto.response.BoardPostResponse;
import com.likelionkit.board.domain.post.model.Board;
import com.likelionkit.board.domain.post.repository.BoardRepository;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.repository.UserRepository;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardPostResponse post(BoardPostRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Board board = BoardPostRequest.toEntity(request);
        board.addUser(user);
        Board savedboard = boardRepository.save(board);
        return new BoardPostResponse(savedboard);
    }

    public BoardFindResponse findBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));
        return new BoardFindResponse(board);
    }

    public List<BoardFindResponse> findAll() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(BoardFindResponse::new)
                .toList();
    }

    @Transactional
    public BoardPostResponse update(Long userId, BoardPostRequest request, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));
        if(board.getUser().getId() != userId){
            throw new CustomException(ErrorCode.NOT_OWNER_BOARD);
        }
        board.update(request);
        return new BoardPostResponse(board);
    }

    @Transactional
    public void delete(Long userId, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));
        if(board.getUser().getId() != userId){
            throw new CustomException(ErrorCode.NOT_OWNER_BOARD);
        }
        boardRepository.delete(board);
    }
}
