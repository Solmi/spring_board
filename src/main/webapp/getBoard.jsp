<%@page import="com.javalec.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 상세</title>
</head>
<body>

	<%
		BoardVO result = (BoardVO)request.getAttribute("board");
	%>
				<center>
					<h1>게시글 상세</h1>
					<hr>
						<table border="1" cellpadding="0" cellspacing="0" width=700px>
							<tr>
								<td  width=20%>제목</td>
								<td >&nbsp;&nbsp;<input name="title" type="text"
									 disabled="disabled" value="<%=result.getTitle() %>"/></td>
							</tr>
							<tr>
								<td >작성자</td>
								<td >&nbsp;&nbsp;<input name="writer" type="text"
									disabled="disabled" value="<%=result.getWriter()%>"/></td>
							</tr>
							<tr>
								<td >내용</td>
								<td >&nbsp;&nbsp;<textarea name="content" cols="70" rows="10" 
									disabled="disabled" ><%=result.getContent() %></textarea>
								</td>
							</tr>
							<tr>
								<td >등록일</td>
								<td ><%=result.getRegdate()%></td>
							</tr>
							<tr>
								<td >조회수</td>
								<td ><%=result.getCnt()%></td>
							</tr>
						</table>
					<hr>
				</center>
				<div align="center"	width:100%	height:50px  margin:20px auto;>
				<%
					String sessionID = (String)session.getAttribute("LOG_ID");
				
					if(sessionID.equals(result.getId())) { %>
						<a href="updateform.do?seq=<%=result.getSeq()%>" >변경</a>&nbsp;&nbsp;&nbsp; 
						<a href="delete.do?seq=<%=result.getSeq()%>" >삭제</a>&nbsp;&nbsp;&nbsp;
				<%		
					}
				%>
					<a href="getboardlist.do" >목록</a>
				</div>
</body>
</html>
