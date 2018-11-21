package com.javalec.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javalec.board.vo.UsersVO;

@Repository
public class UsersDAO {
	
	private String insertSQL = "INSERT INTO users VALUES (?, ?, ?, ?) ";
	private String deleteSQL = "DELETE FROM users WHERE ID = ? AND PASSWORD = ?";
	private String seleteOneSQL = "SELECT id, password, name, role FROM users WHERE id=?";
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			System.out.println("DB Connection Success");
		} catch (ClassNotFoundException e) {
			System.out.println("DB Connection Error " + e);
		}
		return conn;
	}
	
	public int insert(UsersVO vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// Statement Connection
			String sql = insertSQL;
			pstmt = conn.prepareStatement(sql);
			
			// binding
			pstmt.setString(1, vo.getId() );
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getRole());

			// SQL execute
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("INSERT SQLException error : " + e);
		} finally {
			// close
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				System.out.println("insert close error");
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int delete(UsersVO vo) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = deleteSQL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DELETE SQLException error : " + e);
		} finally {
			// close
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("delete close error");
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public UsersVO selecOne(UsersVO vo) {		// 비밀번호 찾기
		UsersVO resultVO = new UsersVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = seleteOneSQL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String role = rs.getString(4);
				
				resultVO.setId(id);
				resultVO.setPassword(password);
				resultVO.setName(name);
				resultVO.setRole(role);
				
			}
		} catch(SQLException e) {
			System.out.println("SELECT SQLException error : " + e);
		} finally {
			// close
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("select close error: " + e);
			}
		}
		return resultVO;
	}
	
	
}
