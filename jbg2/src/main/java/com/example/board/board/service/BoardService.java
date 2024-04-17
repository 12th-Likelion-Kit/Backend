package com.example.board.board.service;

import com.example.board.board.Dto.BoardListDto;
import com.example.board.board.domain.Board;
import com.example.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board addBoard(Board board) {
        boardRepository.save(board);
        return board;
    }

    public Board findBoard(Long id) {
        return boardRepository.findId(id);
    }

    public List<BoardListDto> findAllBoardList() {
        return boardRepository.findAll().stream()
                .map(board -> new BoardListDto(board.getTitle(), board.getWriter(), board.getLike()))
                .collect(Collectors.toList());
    }

    public List<BoardListDto> likeSortedBoardList(String method) {
        List<BoardListDto> BoardList = this.findAllBoardList();
        if (method.equals("ascend"))
            Collections.sort(BoardList, (b1, b2) -> b1.getLike() - b2.getLike());
        else
            Collections.sort(BoardList, (b1, b2) -> b2.getLike() - b1.getLike());
        return BoardList;
    }

    public Board updateBoardLike(Long id) {
        return this.findBoard(id).likeUp();
    }

    public String deleteBoard(Long id) {
        boardRepository.delete(id);
        return "delete Success";
    }
}
