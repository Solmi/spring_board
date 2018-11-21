package com.javalec.board.service;

import com.javalec.board.vo.UsersVO;

public interface IUsersService {
	public void insertUserService(UsersVO vo);
	public UsersVO getUserInfoService(UsersVO vo);
	public boolean check_id(UsersVO vo); 
}
