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
		<h1> �α��� </h1>
	
		<form action="login.do" method="post">
			<table cellpadding="2" cellspacing="0" width=300px>
				<tr>
					<td align="left"> ���̵� </td> <td> <input type="text" name="id" > </td>
				</tr>
				<tr>
					<td align="left"> ��й�ȣ </td> <td> <input type="text" name="password" > </td>
				</tr>
			</table>
				<br>
				<input type="submit" value="�α���">
		</form>
		<br>
		
	<%
		String msg = (String)request.getAttribute("msg");
	
		if(msg != null){	%>
		<h3><font color='red'> <%=msg%> </font></h3>
	<%		
		}
	%>
		
		<a href="joinform.do">ȸ������  &nbsp; <a href="findpasswordform.do">��й�ȣ ã��

	</center>
</body>
</html>