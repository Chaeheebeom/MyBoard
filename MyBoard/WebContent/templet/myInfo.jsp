<%@page import="myboardvo.UsertblVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="top.html"></jsp:include>
    <%
   		 UsertblVO vo=(UsertblVO)session.getAttribute("Info");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<table border="1">
	<tr>
		<td>내코드</td>
		<td>아이디</td>
		<td>이름</td>
		<td>비밀번호</td>
		<td>핸드폰번호</td>
	</tr>
	<tr>
		<td><%=vo.getPrcode() %></td>
		<td><%=vo.getId() %></td>
		<td><%=vo.getName() %></td>
		<td><%=vo.getPasswd() %></td>
		<td><%=vo.getPhonnum() %></td>
	</tr>
</table>
</body>
</html>