<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%@ page session = "true" %>

	<center>
		<h1> 로그인 </h1>
	
		<form action="login.do" method="post">
			<table cellpadding="2" cellspacing="0" width=300px>
				<tr>
					<td align="left"> 아이디 </td> <td> <input type="text" name="id" > </td>
				</tr>
				<tr>
					<td align="left"> 비밀번호 </td> <td> <input type="text" name="password" > </td>
				</tr>
			</table>
				<br>
				<input type="submit" value="로그인">
		</form>
		<br>
		
	<%
		String msg = (String)request.getAttribute("msg");
	
		if(msg != null){	%>
		<h3><font color='red'> <%=msg%> </font></h3>
	<%		
		}
	%>
		
		<a href="joinform.do">회원가입  &nbsp; <a href="findpasswordform.do">비밀번호 찾기

	</center>
</body>
</html>