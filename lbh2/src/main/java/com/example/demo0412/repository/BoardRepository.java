package com.example.demo0412.repository;


import com.example.demo0412.dto.BoardDTO;

import java.util.List;

public interface BoardRepository {
    List<BoardDTO> getAllBoards();
    BoardDTO getBoardById(Long id);
    BoardDTO likeCountUp(Long id);
    void deleteBoardById(Long id);
}
