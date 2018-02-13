<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="top.html"></jsp:include>
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
		<input type="password" name="passwd">
		<input type="hidden" name="num" value="<%=num%>">
		<br>
		<input type="submit" value="삭제">
		<input type="reset" value="취소">
		<input type="button" value="이전" onclick="history.back();">
	</form>
</body>
</html>