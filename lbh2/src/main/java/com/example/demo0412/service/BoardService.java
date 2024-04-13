package com.example.demo0412.service;


import com.example.demo0412.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getAllBoards();
    BoardDTO getBoardById(Long id);
    List<BoardDTO> getBoardsSortedByLikesCount(boolean asc);
    BoardDTO likeCountUp(Long id);
    void deleteBoardById(Long id);
}
