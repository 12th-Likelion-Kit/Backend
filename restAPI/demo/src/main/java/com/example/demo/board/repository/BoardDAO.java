package com.example.demo.board.repository;

import java.util.List;

public interface BoardDAO {

    public List<BoardDTO> boardList();
    public Integer boardInsert(BoardDTO dto);
    public BoardDTO getContent(Integer id);


    public Integer boardUpdate(Integer id);
    public Integer boardDelete(Integer id);

}
