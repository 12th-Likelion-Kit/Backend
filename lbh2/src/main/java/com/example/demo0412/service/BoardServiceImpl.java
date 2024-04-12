package com.example.demo0412.service;


import com.example.demo0412.dto.BoardDTO;
import com.example.demo0412.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardRepository.getAllBoards();
    }

    @Override
    public BoardDTO getBoardById(Long id) {
        return boardRepository.getBoardById(id);
    }

    @Override
    public List<BoardDTO> getBoardsSortedByLikesCount(boolean asc) {
        List<BoardDTO> boards = boardRepository.getAllBoards();

        if (asc)
            Collections.sort(boards, (a, b) -> a.getLikeCount() - b.getLikeCount());
        else
            Collections.sort(boards, (a, b) -> b.getLikeCount() - a.getLikeCount());

        return boards;
    }

    @Override
    public BoardDTO likeCountUp(Long id) {
        return boardRepository.likeCountUp(id);
    }

    @Override
    public void deleteBoardById(Long id) {
        boardRepository.deleteBoardById(id);
    }
}
