package com.javalec.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.board.dao.BoardDAO;
import com.javalec.board.service.IBoardService;
import com.javalec.board.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private IBoardService boardService;
	
	@RequestMapping("/getboardlist.do")
	public String getBoardList(Model model, HttpSession session) {
		System.out.println("-----[Board]list controller-----");
		model.addAttribute("list", boardService.getBoardList());
		
		System.out.println("Session attribute : "+session.getAttribute("LOG_ID"));
		
		if(session.getAttribute("LOG_ID") != null) {
			return "getBoardList.jsp";
		}
		
		return "loginform.do";
	}
	
	@RequestMapping("/insertboardform.do")
	public String InsertboardformController() {
		System.out.println("-----[Board]insert form controller-----");
		
		return "insertBoard.jsp";
	}
	
	@RequestMapping("/insertboard.do")
	public String InsertboardController(BoardVO vo, HttpSession session) {
		System.out.println("-----[Board]insert controller-----");
		
		System.out.println("[insert board] title : " + vo.getTitle());
		System.out.println("[insert board] writer : " + vo.getWriter());
		System.out.println("[insert board] content : " + vo.getContent());
		System.out.println("[insert board] cnt : " + vo.getCnt());
		System.out.println("[insert board] id : " + vo.getId());
		
		boardService.insertBoardService(vo);
		
		return "getboardlist.do";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoardController(BoardVO vo, Model model) {
		System.out.println("-----[Board]getBoard controller-----");
		System.out.println("[getBoard.do] vo : " + vo.toString());
		
		BoardVO result = boardService.getBoardOneList(vo);
		System.out.println("[getBoard.do] result"+result.toString());
		
		boardService.updateCnt(result);
		System.out.println("[getBoard.do] cnt: "+result.getCnt());
		result = boardService.getBoardOneList(result);

		model.addAttribute("board", result);
		
		
		return "getBoard.jsp";
	}
	
	@RequestMapping("/updateform.do")
	public String UpdateformController(BoardVO vo, Model model) {
		System.out.println("-----[Board]update form controller-----");
		
		BoardVO resultvo = boardService.getBoardOneList(vo);
		System.out.println("[update form] resultvo : "+resultvo.toString());
		model.addAttribute("board", resultvo);
		
		return "modifyBoard.jsp";
	}
	
	
	@RequestMapping("/update.do")
	public String UpdateController(BoardVO vo, Model model) {
		System.out.println("-----[Board]update controller-----");
		System.out.println("[update] "+vo.toString());

		boardService.updateGuestBook(vo);
		
		return "getBoard.do";
	}
	
	
	@RequestMapping("/delete.do")
	public String DeleteController(BoardVO vo) {
		System.out.println("-----[Board]delete controller-----");
		System.out.println("[delete] "+vo.toString());
		
		BoardVO result = boardService.getBoardOneList(vo);
		System.out.println("[delete] "+result.toString());
		
		boardService.deleteGuestBook(result);
		
		return "getboardlist.do";
	}

}
