package com.javalec.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.board.dao.UsersDAO;
import com.javalec.board.vo.UsersVO;

@Service("usersService")
public class UsersService implements IUsersService{

	@Autowired
	private UsersDAO dao;
	
	@Override
	public void insertUserService(UsersVO vo) {
		dao.insert(vo);		
	}

	@Override
	public UsersVO getUserInfoService(UsersVO vo) {
		return dao.selecOne(vo);
	}

	@Override
	public boolean check_id(UsersVO vo) {
		String input_id = vo.getId();
		String input_pw = vo.getPassword();
		
		UsersVO checkVO = new UsersVO();
		checkVO.setId(vo.getId());
		System.out.println("[check_id 함수] checkVO.getID : " + checkVO.getId());
		
		UsersVO resultFind = dao.selecOne(checkVO);
		
		String pw = resultFind.getPassword();
		System.out.println("[check_id 함수] db pw: " + pw);
			
		if(!input_pw.equals(pw)) {
			return false;
		}
		
		return true;
	}
	
	

}
