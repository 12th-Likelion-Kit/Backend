package com.likelionkit.board.domain.board.controller;

import com.likelionkit.board.domain.board.dto.request.BoardPostRequest;
import com.likelionkit.board.domain.board.dto.response.BoardFindResponse;
import com.likelionkit.board.domain.board.dto.response.BoardPostResponse;
import com.likelionkit.board.domain.board.service.BoardService;
import com.likelionkit.board.domain.user.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/post")
    public ResponseEntity<BoardPostResponse> post(@RequestBody BoardPostRequest request,
                                                  @AuthenticationPrincipal UserPrincipal user) {
        BoardPostResponse response = boardService.post(request, user.getUserId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardFindResponse> findBoard(@PathVariable Long boardId) {
        BoardFindResponse response = boardService.findBoard(boardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<BoardFindResponse>> findAll() {
        List<BoardFindResponse> response = boardService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardPostResponse> update(@AuthenticationPrincipal UserPrincipal user,
                                                    @RequestBody BoardPostRequest request,
                                                    @PathVariable Long boardId) {
        BoardPostResponse response = boardService.update(user.getUserId(), request, boardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserPrincipal user,
                                       @PathVariable Long boardId) {
        boardService.delete(user.getUserId(), boardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
