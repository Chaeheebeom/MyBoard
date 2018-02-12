<%@page import="myboardvo.UsertblVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UsertblVO vo=(UsertblVO)session.getAttribute("Info");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th{
		background-color:#FFD9EC
	}
	td{
		background-color:#E4F7BA
	}
</style>
</head>
<body>
<form action="../qWrite.do" method="post" enctype="multipart/form-data">
<table>  <!--  td는 기본 th는 재목 -->
<tr>  
	<th>글쓴이</th>
	<td><input type="text" name="id" value="<%=vo.getId() %>" readonly></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" name="subject" size="50" required></td> <!-- size는 길이 -->
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="content" cols="60" rows="20" required></textarea></td><!-- cols는 가로길이 rows는 세로길이 -->
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="hidden" name="passwd" value="<%=vo.getPasswd()%>"></td>
	<td><input type="hidden" name="prcode" value="<%=vo.getPrcode()%>"></td>
	<td><input type="hidden" name="name" value="<%=vo.getName()%>"></td>
</tr>
<tr>
	<th>파일첨부</th>
	<td><input type="file" name="file"></td>
</tr>
<tr>
	<th colspan="2" >
		<input type="submit" value="등록">
		<input type="reset" value="취소">
		<a href="../list.do"><input type="button" value="목록보기"></a>
	</th>
</tr>
</table>
</form>
</body>
</html>