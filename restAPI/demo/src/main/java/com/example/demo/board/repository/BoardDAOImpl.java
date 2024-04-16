package com.example.demo.board.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO{

    List<BoardDTO> list = new ArrayList<>();
    @Override
    public List<BoardDTO> boardList() {
        return list;
    }

    @Override
    public Integer boardInsert(BoardDTO dto) {
        if(list.add(dto)) return 1;
        return 0;
    }

    @Override
    public BoardDTO getContent(Integer id) {
        for(int i = 0 ; i< list.size();i++){
            BoardDTO dto = list.get(i);
            if(dto.getId() == id) return dto;
        }
        return null;
    }


    @Override
    public Integer boardUpdate(Integer id) {
        for(int i = 0 ; i < list.size() ; i ++){
            BoardDTO dto = list.get(i);
            if(dto.getId() == id){
                dto.setLikeCount(dto.getLikeCount()+1);
                list.add(dto);
                return 1;
            }
        }
        return 0;

    }

    @Override
    public Integer boardDelete(Integer id) {
        for(int i = 0 ; i < list.size() ; i ++){
            BoardDTO dto = list.get(i);
            if(dto.getId() == id){
               list.remove(i);
                return 1;
            }
        }
        return 0;

    }
}
