package com.study.kky2.board.repository;

import com.study.kky2.board.domain.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class BoardRepositoryImp implements BoardRepository{
    private static HashMap<Long, BoardDTO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public BoardDTO save(BoardDTO boardDTO) {
        try {
            boardDTO.setId(++sequence);
            store.put(boardDTO.getId(), boardDTO);
            return boardDTO;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BoardDTO> findAll() {
        try {
            return store.values().stream().toList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public BoardDTO findById(Long id) {
        try {
            return store.get(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public BoardDTO updateWithLikeNum(Long id) {
        try {
            BoardDTO boardDTO = store.get(id);
            boardDTO.setLikeNum(boardDTO.getLikeNum() + 1);
            store.put(id, boardDTO);
            return boardDTO;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            store.remove(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
