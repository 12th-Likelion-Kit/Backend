package com.likelionkit.board.domain.comment.model;

import com.likelionkit.board.domain.board.model.Board;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.global.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }
}
