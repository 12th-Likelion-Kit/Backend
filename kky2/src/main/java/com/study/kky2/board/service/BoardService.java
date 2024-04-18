package com.study.kky2.board.service;

import com.study.kky2.board.domain.UserBoardDTO;
import com.study.kky2.board.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO save(BoardDTO boardDTO);
    List<UserBoardDTO> findAll(String orderBy);
    BoardDTO findById(Long id);
    BoardDTO updateWithLikeNum(Long id);
    void delete(Long id);
}
