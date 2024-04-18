package com.study.kky2.board.service;

import com.study.kky2.board.domain.UserBoardDTO;
import com.study.kky2.board.repository.BoardRepository;
import com.study.kky2.board.domain.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardDTO save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO);
    }

    @Override
    public List<UserBoardDTO> findAll(String orderBy) {
        List<UserBoardDTO> userBoardList = new ArrayList<>(boardRepository.findAll()
                                            .stream().map(x -> new UserBoardDTO(x.getTitle(), x.getContent(), x.getLikeNum())).toList());

        if (orderBy.equals("asc")) {
            userBoardList.sort(Comparator.comparingInt(UserBoardDTO::getLikeNum));
        } else {
            userBoardList.sort(Comparator.comparingInt(UserBoardDTO::getLikeNum).reversed());
        }

        return userBoardList;
    }

    @Override
    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public BoardDTO updateWithLikeNum(Long id) {
        return boardRepository.updateWithLikeNum(id);
    }

    @Override
    public void delete(Long id) {
        boardRepository.delete(id);
    }
}
