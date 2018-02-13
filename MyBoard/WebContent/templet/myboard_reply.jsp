<%@page import="myboardvo.UsertblVO"%>
<%@page import="myboardvo.MyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="top.html"></jsp:include>
<%
	MyBoardVO vo=(MyBoardVO)request.getAttribute("vo");
	UsertblVO vo2=(UsertblVO)session.getAttribute("Info");
	String current_page=request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변달기</title>
</head>
<body>
<form action="reply.do" method="post">
<table>  <!--  td는 기본 th는 재목 -->
<tr>  
	<th>제목</th>
	<td><input type="text" value="Re: <%=vo.getBoard_subject() %>" name="board_subject" size="50"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" size="50" name="board_name" value="<%=vo2.getId() %>" readonly></td> <!-- size는 길이 -->
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="board_content" cols="60" rows="20" >Re: <%=vo.getBoard_subject()%></textarea></td><!-- cols는 가로길이 rows는 세로길이 -->
</tr>
<tr>
	<th colspan="2" >
	<input type="hidden" name="board_pass" value="<%=vo2.getPasswd()%>">
		<input type="hidden" name="board_num" value="<%=vo.getBoard_num()%>">
		<input type="hidden" name="board_re_ref" value="<%=vo.getBoard_re_ref()%>">
		<input type="hidden" name="board_re_lev" value="<%=vo.getBoard_re_lev()%>">
		<input type="hidden" name="board_rs_seq" value="<%=vo.getBoard_rs_seq()%>">
		<input type="hidden" name="prcode" value="<%=vo2.getPrcode()%>">
		<input type="hidden" name="id" value="<%=vo2.getId()%>">
		<input type="hidden" name="page" value="<%=current_page%>">
		<input type="submit" value="등록">
		<a href="view.do?board_num=<%=vo.getBoard_num() %>"><input type="button" value="뒤로"></a>
	</th>
</tr>
</table>
</form>
</body>
</html>