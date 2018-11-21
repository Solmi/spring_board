package com.javalec.board.service;

import java.util.List;

import com.javalec.board.vo.BoardVO;

public interface IBoardService {
	public void insertBoardService(BoardVO vo);
	public void deleteGuestBook(BoardVO vo);
	public void updateGuestBook(BoardVO vo);
	public List<BoardVO> getBoardList();
	public BoardVO getBoardOneList(BoardVO vo);
	public void updateCnt(BoardVO vo);
}
