<%@page import="myboardvo.UsertblVO"%>
<%@page import="myboardvo.PageInfo"%>
<%@page import="java.util.Vector"%>
<%@page import="myboardvo.MyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../templet/top.html"></jsp:include>
<%
	UsertblVO vo=(UsertblVO)session.getAttribute("Info");
	
	String code=request.getParameter("code");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
	<img src="../mainbg.jpg">
</body>
</html>