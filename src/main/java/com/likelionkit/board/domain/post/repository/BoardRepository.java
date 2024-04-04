package com.likelionkit.board.domain.post.repository;

import com.likelionkit.board.domain.post.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
