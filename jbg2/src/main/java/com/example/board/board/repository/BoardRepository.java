package com.example.board.board.repository;

import com.example.board.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardRepository {
    private HashMap<Long, Board> store = new HashMap<>();
    private Long sequence = 0L;

    public Board save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    public Board findId(Long id) {
        return store.get(id);
    }

    public List<Board> findAll() {
        return store.values().stream().toList();
    }

    public Board update(Long id, Board board) {
        store.replace(id, board);
        return board;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}