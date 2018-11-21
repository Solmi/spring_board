package com.javalec.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.board.dao.UsersDAO;
import com.javalec.board.service.IUsersService;
import com.javalec.board.vo.UsersVO;

@Controller
public class UsersController{
	
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping("/loginform.do")
	public String LoginformController() {
		System.out.println("-----[USER]loginform controller-----");
		
		return "loginform.jsp";
	}
	
	@RequestMapping("/login.do")
	public String LoginController(UsersVO vo, UsersDAO dao, Model model, HttpSession session) {
		System.out.println("-----[USER]login controller-----");
		
		String id = vo.getId();
		String pw = vo.getPassword();
		
		System.out.println("id = " + id);
		System.out.println("pw = " + pw);
		
		//비밀번호 일치 확인
		boolean pwCheck = false;
		pwCheck = usersService.check_id(vo);
		
		System.out.println("[login.do] pwCheck : " + pwCheck);
		
		if(pwCheck == true) {	
			System.out.println("[login.do] 1");
			//세션 넣기
			HttpServletRequest request = null;
			System.out.println("[login.do] 2");
			System.out.println("[login.do] id : " + vo.getId());
			session.setAttribute("LOG_ID", vo.getId());
			
			System.out.println("[login.do] 3");
			//model.addAttribute("LOG_ID", id);
			System.out.println("[login.do] session id : "+ session.getAttribute("LOG_ID"));
			
			return "getboardlist.do";
		}
		
		model.addAttribute("msg", "아이디/비밀번호를 확인해주세요");
		return "loginform.do";
	}
	
	@RequestMapping("/logout.do")
	public String LogoutController(HttpSession session) {
		
		if(session != null) {
			session.invalidate();
		}
		
		return "getboardlist.do";
	}
	
	@RequestMapping("/joinform.do")
	public String JoinformController() {
		System.out.println("-----[USER]joinform controller-----");
		
		return "joinform.jsp";
	}
	
	@RequestMapping("/write.do")
	public String JoinWriteController(UsersVO vo, UsersDAO dao, 
			@RequestParam(value="role", defaultValue="", required=false) String role) {
		System.out.println("-----[USER]joinwrite controller-----");
		
		System.out.println("id : " + vo.getId());
		System.out.println("pw : " + vo.getPassword());
		System.out.println("name : " + vo.getName());
		System.out.println("role : " + role);
		
		vo.setRole(role);
		usersService.insertUserService(vo);
		
		return "loginform.do";
	}
	
	@RequestMapping("/findpasswordform.do")
	public String FindpasswordformController() {
		System.out.println("-----[USER]find pw form controller-----");
		
		return "findpassword.jsp";
	}
	
	@RequestMapping("/findpassword.do")
	public String FindpasswordController(UsersVO vo, UsersDAO dao, Model model) {
		System.out.println("-----[USER]find pw controller-----");
		
		UsersVO result = usersService.getUserInfoService(vo);		
		if("null".equals(result.getPassword())) {
			model.addAttribute("msg", " ");
		}
		model.addAttribute("msg", result.getPassword());
		
		return "findpasswordform.do";
	}
	



}
