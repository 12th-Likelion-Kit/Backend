package com.example.kdh2.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.kdh2.dto.request.BoardRequestDto;
import com.example.kdh2.dto.response.BoardListResponseDto;
import com.example.kdh2.dto.response.BoardResponseDto;
import com.example.kdh2.entity.BoardEntity;
import com.example.kdh2.repository.BoardRepository;
import com.example.kdh2.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<BoardResponseDto> registerBoard(BoardRequestDto dto) {
        BoardEntity boardEntity = BoardEntity.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(dto.getWriter())
            .build();
        boardRepository.save(boardEntity);
        return BoardResponseDto.registerSuccess(boardEntity);
    }

    @Override
    public ResponseEntity<BoardResponseDto> getBoard(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id);
        if(boardEntity == null) {
            return BoardResponseDto.fail();
        }
        return BoardResponseDto.getSuccess(boardEntity);
    }

    @Override
    public ResponseEntity<List<BoardListResponseDto>> getBoardList() {
        List<BoardEntity> boardList = new ArrayList<>(boardRepository.getStore().values());
        List<BoardListResponseDto> responseList = boardList.stream()
            .map(BoardListResponseDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<List<BoardListResponseDto>> updateBoardByLikes(String order) {
        List<BoardEntity> boardList = new ArrayList<>(boardRepository.getStore().values());
        
        boardList.sort(Comparator.comparingInt(BoardEntity::getLikeCount));

        if (order.equalsIgnoreCase("desc")) {
            Collections.reverse(boardList);
        }
        List<BoardListResponseDto> responseList = boardList.stream()
            .map(BoardListResponseDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<Void> increaseLikeCount(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id);
        if(boardEntity == null) {
            return ResponseEntity.notFound().build();
        }
        boardEntity.increaseLikeCount();
        boardRepository.save(boardEntity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteBoard(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id);
        if(boardEntity == null) {
            return ResponseEntity.notFound().build();
        }
        boardRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
