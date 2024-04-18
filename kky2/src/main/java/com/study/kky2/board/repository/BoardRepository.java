package com.study.kky2.board.repository;

import com.study.kky2.board.domain.BoardDTO;

import java.util.List;

public interface BoardRepository {
    BoardDTO save(BoardDTO boardDTO);
    List<BoardDTO> findAll();
    BoardDTO findById(Long id);
    BoardDTO updateWithLikeNum(Long id);
    void delete(Long id);
}
