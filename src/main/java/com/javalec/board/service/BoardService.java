package com.javalec.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.board.dao.BoardDAO;
import com.javalec.board.vo.BoardVO;

@Service("boardService")
public class BoardService implements IBoardService{
	
	@Autowired
	private BoardDAO dao;

	@Override
	public void insertBoardService(BoardVO vo) {
		dao.insert(vo);	
	}

	@Override
	public void deleteGuestBook(BoardVO vo) {
		dao.delete(vo);
	}

	@Override
	public void updateGuestBook(BoardVO vo) {
		dao.update(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		return dao.selectList();
	}

	@Override
	public BoardVO getBoardOneList(BoardVO vo) {
		return dao.select(vo);
	}
	
	@Override
	public void updateCnt(BoardVO vo) {
		dao.updateCnt(vo);
	}

}
