package com.likelionkit.board.domain.comment.service;

import com.likelionkit.board.domain.board.model.Board;
import com.likelionkit.board.domain.board.repository.BoardRepository;
import com.likelionkit.board.domain.comment.dto.request.CommentCreateRequest;
import com.likelionkit.board.domain.comment.dto.response.CommentResponse;
import com.likelionkit.board.domain.comment.model.Comment;
import com.likelionkit.board.domain.comment.repository.CommentRepository;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.repository.UserRepository;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(CommentCreateRequest request, Long boardId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));

        Comment comment = CommentCreateRequest.toEntity(request);
        comment.setUser(user);
        comment.setBoard(board);

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));
        List<Comment> comments = commentRepository.findByBoardId(board.getId());

        List<CommentResponse> response = new ArrayList<>();
        for(Comment comment : comments){
            response.add(new CommentResponse(comment));
        }

        return response;
    }
}
