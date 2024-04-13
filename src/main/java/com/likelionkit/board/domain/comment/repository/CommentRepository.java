package com.likelionkit.board.domain.comment.repository;

import com.likelionkit.board.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment WHERE board_id = ?", nativeQuery = true)
    List<Comment> findByBoardId(Long boardId);
}
