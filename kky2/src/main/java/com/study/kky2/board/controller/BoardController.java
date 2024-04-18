package com.study.kky2.board.controller;

import com.study.kky2.board.domain.BoardDTO;
import com.study.kky2.board.domain.UserBoardDTO;
import com.study.kky2.board.service.BoardServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    public final BoardServiceImp boardService;

    @PostMapping("/add")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        try {
            log.info("Create board 요청: {}", boardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(boardService.save(boardDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable long id) {
        try {
            log.info("Get board by id 요청: {}", id);
            return ResponseEntity.ok(boardService.findById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    // api/board/all?orderBy=acs(or desc)
    @GetMapping("/all")
    public ResponseEntity<List<UserBoardDTO>> getAllBoard(@RequestParam String orderBy) {
        try {
            log.info("Get all board by orderBy 요청: {}", orderBy);
            return ResponseEntity.ok(boardService.findAll(orderBy));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> updateBoardWithLike(@PathVariable long id) {
        try {
            log.info("Update board with like 요청: {}", id);
            return ResponseEntity.status(HttpStatus.CREATED).body(boardService.updateWithLikeNum(id));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable long id) {
        try {
            log.info("Delete board with id 요청: {}", id);
            boardService.delete(id);
            // body가 없을 때 build() 사용
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
