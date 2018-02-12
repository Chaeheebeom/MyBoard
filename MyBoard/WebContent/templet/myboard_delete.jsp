<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num=request.getParameter("num");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="../delete.do" method="post">
		비밀번호 
		<input type="password" value="passwd">
		<input type="hidden" value="<%=num%>">
		<br>
		<input type="submit" value="삭제">
		<input type="reset" value="취소">
		<input type="button" value="이전" onclick="history.back();">
	</form>
	
<%response.sendRedirect("../view/mainBoard_list?code=delete"); %>
</body>
</html>