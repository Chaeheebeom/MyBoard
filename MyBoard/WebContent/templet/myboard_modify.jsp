<%@page import="myboardvo.MyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="top.html"></jsp:include>
    <%
    	MyBoardVO vo=(MyBoardVO)request.getAttribute("vo");
    	String current_page=request.getParameter("page");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글수정</title>
</head>
<body>
<form action="qUpdate.do" method="post" enctype="multipart/form-data">
<table>  <!--  td는 기본 th는 재목 -->
<tr>  
	<th>제목</th>
	<td><input type="text" value="<%=vo.getBoard_subject() %>" name="board_subject" size="50"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" size="50" value="<%=vo.getUser_name()%>" readonly></td> <!-- size는 길이 -->
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="board_content" cols="60" rows="20" ><%=vo.getBoard_content()%></textarea></td><!-- cols는 가로길이 rows는 세로길이 -->
</tr>
<tr>
	<th>비밀번호</th>
	<td>
		<input type="password" name="board_pass">
	</td>
</tr>
<tr>
	<th>파일첨부</th>
	<td>
		<input type="file" name="board_file">
	</td>	
</tr>
<tr>
	<th colspan="2" >
		<input type="hidden" name="board_num" value="<%=vo.getBoard_num()%>">
		<input type="hidden" name="page" value="<%=current_page%>">
		<input type="submit" value="수정">
		<a href="qView.do?board_num=<%=vo.getBoard_num() %>"><input type="button" value="뒤로"></a>
	</th>
</tr>
</table>
</form>
</body>
</html>