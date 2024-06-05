package com.example.boardstudy.domain.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.boardstudy.domain.comment.entity.Comment;
import com.example.boardstudy.domain.user.entity.User;
import com.example.boardstudy.global.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	// User (글쓴이)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();

	@Builder
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void addUser(User user) {
		this.user = user;
		user.getBoards().add(this);
	}
}
