package com.example.demo.board.controller;

import com.example.demo.board.repository.BoardDTO;
import com.example.demo.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    @ResponseBody
    public List<BoardDTO> boardList() {
        List<BoardDTO> list = service.boardList();
        return list;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public BoardDTO boardInsert(@RequestBody BoardDTO dto){
        int result = service.boardInsert(dto);
        if(result ==1) return dto;
        return null;
    }

    @RequestMapping("/content")
    @ResponseBody
    public BoardDTO getContent(Integer id){
        return service.getContent(id);
    }



    @RequestMapping("/update")
    @ResponseBody
    public BoardDTO boardUpdate(@RequestBody BoardDTO dto){
     int result = service.boardUpdate(dto.getId());
     if(result == 1) {
         return service.getContent(dto.getId()); // 수정 반영된 얘 반환
     }
     return null;
    }



    @RequestMapping("/delete")
    @ResponseBody
    public List<BoardDTO> boardDelete(@RequestBody BoardDTO dto){
        service.boardDelete(dto.getId());
        return boardList();
    }



}
