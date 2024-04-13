package com.likelionkit.board.domain.board.repository;

import com.likelionkit.board.domain.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b left join fetch b.comments")
    List<Board> findAllJPQLFetch();
}
