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
	<%if(code.equals("mainPage")) {%>
		<img src="../mainbg.jpg">
	<%}else if(code.equals("myInfo")){ %>
		<jsp:include page="../templet/myInfo.jsp"></jsp:include>
	<%}else if(code.equals("write")){ %>
		<jsp:include page="myboard_write.jsp"></jsp:include>
	<%}else if(code.equals("view")){ %>
		<jsp:include page="../templet/myboard_view.jsp"></jsp:include>
	<%}else if(code.equals("delete")) {%>
		<jsp:include page="../templet/myboard_view.jsp"></jsp:include>
		<jsp:include page="../templet/myboard_delete.jsp"></jsp:include>
	<%} %>
</body>
</html>