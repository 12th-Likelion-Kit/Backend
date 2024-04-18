package com.study.kky2;

import com.study.kky2.board.domain.BoardDTO;
import com.study.kky2.board.repository.BoardRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Kky2ApplicationTests {
	BoardRepositoryImp repository = new BoardRepositoryImp();

	@Test
	public void save() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle("테스트 해봅시다");
		boardDTO.setContent("assertEquals를 사용해서 비교합니다.");
		boardDTO.setAuthor("2001Kaye");
		boardDTO.setLikeNum(0);

		repository.save(boardDTO);

		BoardDTO result = repository.findById(boardDTO.getId());
		Assertions.assertEquals(boardDTO, result);
	}

	@Test
	public void updateWithLikeNum(){
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle("테스트 해봅시다");
		boardDTO.setContent("assertEquals를 사용해서 비교합니다.");
		boardDTO.setAuthor("2001Kaye");
		boardDTO.setLikeNum(0);
		repository.save(boardDTO);

		BoardDTO result = repository.updateWithLikeNum(boardDTO.getId());
		Assertions.assertEquals(result, boardDTO);
	}

	@Test
	public void findAll() {
		BoardDTO boardDTO1 = new BoardDTO();
		boardDTO1.setTitle("테스트 해봅시다");
		boardDTO1.setContent("assertEquals를 사용해서 비교합니다.");
		boardDTO1.setAuthor("2001Kaye");
		boardDTO1.setLikeNum(0);
		repository.save(boardDTO1);

		BoardDTO boardDTO2 = new BoardDTO();
		boardDTO2.setTitle("테스트 해봅시다2");
		boardDTO2.setContent("assertEquals를 사용해서 비교합니다2.");
		boardDTO2.setAuthor("2001Kaye");
		boardDTO2.setLikeNum(2);
		repository.save(boardDTO2);

		List<BoardDTO> result = repository.findAll();
		Assertions.assertEquals(result.size(), 2);
	}

	@Test
	public void delete(){
		BoardDTO boardDTO = new BoardDTO();
		repository.delete(boardDTO.getId());

		List<BoardDTO> resultList = repository.findAll();
		Assertions.assertEquals(resultList.size(), 0);
	}
}
