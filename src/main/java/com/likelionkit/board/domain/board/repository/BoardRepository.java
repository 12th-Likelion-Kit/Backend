package com.likelionkit.board.domain.board.repository;

import com.likelionkit.board.domain.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
