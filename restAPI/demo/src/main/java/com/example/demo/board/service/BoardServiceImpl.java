package com.example.demo.board.service;

import com.example.demo.board.repository.BoardDAO;
import com.example.demo.board.repository.BoardDTO;
import com.example.demo.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO dao;
    @Override
    public List<BoardDTO> boardList() {
        return dao.boardList();
    }

    @Override
    public Integer boardInsert(BoardDTO dto) {
        if(dto != null) return dao.boardInsert(dto);
        return 0;
    }

    @Override
    public BoardDTO getContent(Integer id) {
        if(id != null) return dao.getContent(id);
        return null;
    }


    @Override
    public Integer boardUpdate(Integer id) {
        if(id != null) return dao.boardUpdate(id);
        return null;
    }

    @Override
    public Integer boardDelete(Integer id) {
        if(id != null) return dao.boardDelete(id);
        return null;
    }
}
