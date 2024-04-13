package com.example.demo0412.repository;

import com.example.demo0412.domain.Board;
import com.example.demo0412.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class BoardRepositoryImpl implements BoardRepository{
    Map<Long, Board> boards = new HashMap<>(
            Map.of(
                    1L, Board.builder()
                            .id(1L)
                            .title("title1")
                            .content("content1")
                            .author("test1")
                            .likeCount(0)
                            .build(),
                    2L, Board.builder()
                            .id(2L)
                            .title("title2")
                            .content("content2")
                            .author("test2")
                            .likeCount(1)
                            .build())
    );


    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardDTOList = boards.values().stream()
                .map(BoardDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
        if(boardDTOList.isEmpty()) log.error("Board ID does not exist");
        else log.info("getAllBoards success");
        return boardDTOList;
    }

    @Override
    public BoardDTO getBoardById(Long id) {
        if(boards.containsKey(id)) log.info("get board by id: " + id);
        else log.error("Board ID does not exist: " + id);
        return new BoardDTO(boards.get(id));
    }

    @Override
    public BoardDTO likeCountUp(Long id) {
        Board board = null;
        if(boards.containsKey(id)) {
            board = boards.get(id);
            board.setLikeCount(board.getLikeCount() + 1);
            log.info("like count: " + board.getLikeCount());
        }
        else log.error("Board ID does not exist: " + id);
        return new BoardDTO(board);
    }

    @Override
    public void deleteBoardById(Long id) {
        if(boards.containsKey(id)) {
            boards.remove(id);
            log.info("Deleted board with id: " + id);
        }
        else log.error("Board ID does not exist: " + id);
    }
}
