package com.javalec.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javalec.board.vo.BoardVO;

@Repository
public class BoardDAO {
	private String insertSQL = "INSERT INTO board VALUES (board_seq.nextval, ?, ?, ?, sysdate, ?, ?) ";
	private String deleteSQL = "DELETE FROM board WHERE seq = ?";
	private String selectListSQL = "SELECT seq, title, writer, content, TO_CHAR(REGDATE, 'yyyy/mm/dd hh:mi:ss'), cnt, id "
			+ "FROM board order by seq desc";
	private String updateSQL = "UPDATE board SET CONTENT=?, title=?, writer=? WHERE seq=?";
	private String selectOneSQL = "SELECT seq, title, writer, content, TO_CHAR(REGDATE, 'yyyy/mm/dd hh:mi:ss'), cnt, id "
			+ "FROM board WHERE seq=?";
	private String updateCntSQL = "UPDATE board SET cnt=nvl(CNT,0)+1 WHERE seq=?";
	
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
	
	public int insert(BoardVO vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// Statement Connection
			String sql = insertSQL;
			pstmt = conn.prepareStatement(sql);
			
			// binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getCnt());
			pstmt.setString(5, vo.getId());

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
	
	public int delete(BoardVO vo) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = deleteSQL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			
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
	
	public BoardVO select(BoardVO vo) {	//단건조회
		BoardVO resultVO = new BoardVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = selectOneSQL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String regdate = rs.getString(5);
				int cnt = rs.getInt(6);
				String id = rs.getString(7);
				
				resultVO.setSeq(seq);
				resultVO.setTitle(title);
				resultVO.setWriter(writer);
				resultVO.setContent(content);
				resultVO.setRegdate(regdate);
				resultVO.setCnt(cnt);
				resultVO.setId(id);
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
	
	public List<BoardVO> selectList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// Statement Connection
			String sql = selectListSQL;
			pstmt = conn.prepareStatement(sql);
			// SQL execute
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();

				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String regdate = rs.getString(5);
				int cnt = rs.getInt(6);
				String id = rs.getString(7);
				
				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);
				vo.setId(id);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SELECT_LIST SQLException error: " + e);
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
				System.out.println("select list Close error: " + e);
			}
		}
		return list;
	}
	
	public int update(BoardVO vo) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = updateSQL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getSeq());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UPDATE SQLException error : " + e);
		} finally {
			// close
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("UPDATE close error");
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int updateCnt(BoardVO vo) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = updateCntSQL;
			pstmt = conn.prepareStatement(sql);
			System.out.println("[updateCnt SQL] cnt: "+vo.getCnt());
			pstmt.setInt(1, vo.getSeq());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UPDATE Cnt SQLException error : " + e);
		} finally {
			// close
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("UPDATE Cnt close error");
				e.printStackTrace();
			}
		}
		return count;
	}

}
