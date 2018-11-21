<%@page import="java.util.Iterator"%>
<%@page import="com.javalec.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 목록</title>
</head>
<body>

	<%
		List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
		String id = (String)session.getAttribute("LOG_ID");
		System.out.println("[getBoardList] log_id = " + id);	
	%>
			<center>
				<h1>게시판 목록</h1>
				<h3>
					글 목록
				</h3>
				☆☆<%=id%>님 환영합니다☆☆
				<a href="logout.do">Log-out</a>
				
				<!-- 검색 시작 -->
				<form action="" method="post">
					<table border="1" cellpadding="0" cellspacing="0" width=700px>
						<tr>
							<td align="right"><select name="searchCondition">
									<option value="TITLE">제목
									<option value="CONTENT">내용 

							</select> <input name="searchKeyword" type="text" /> 
							<input type="submit" value="검색" /></td>
						</tr>
					</table>
				</form>
				<!-- 검색 종료 -->	
	<%
		Iterator iter = list.iterator();
	%>
			<table border="1" cellpadding="0" cellspacing="0" width=700px>
				<tr bgcolor="gray">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
	
	<%
		while(iter.hasNext()) {
			BoardVO b_vo = (BoardVO)iter.next();
			
	%>
				<tr>
					<td><%=b_vo.getSeq()%></td>
					<td align="left"><a href="getBoard.do?seq=<%=b_vo.getSeq()%>"><%=b_vo.getTitle()%></a></td>
					<td><%=b_vo.getWriter()%></td>
					<td><%=b_vo.getRegdate()%></td>
					<td><%=b_vo.getCnt()%></td>
				</tr>
			
	<%		
		}
	%>

		</table>
			<br>
			<div align="center"	width:100%	height:50px  margin:20px auto;>
				<a href="insertboardform.do" >게시글 등록</a>&nbsp;&nbsp;&nbsp; 
			</div>
		</center>

</body>
</html>