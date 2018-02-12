<%@page import="myboardvo.UsertblVO"%>
<%@page import="myboardvo.PageInfo"%>
<%@page import="java.util.Vector"%>
<%@page import="myboardvo.MyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../templet/top.html"></jsp:include>
     <%
    
    	Vector<MyBoardVO> list=(Vector<MyBoardVO>)request.getAttribute("list");
    	
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
<form action="qSearch.do" method="post">
<table border="1">
<caption>게시판 목록</caption>
<tr>
	<th>번호</th>
	<th width="200">제목</th>
	<th>작성자</th>
	<th>날짜</th>
	<th>조회수</th>
</tr>
<%	

for(MyBoardVO vo:list){ 
		
%>
<tr>
	<td><%=vo.getBoard_num() %></td>
	<td>
		<a href="hitupdate.do?num=<%=vo.getBoard_num()%>">
		<!-- 조회수 갔다가 뷰로가기 -->
		<%=vo.getBoard_subject()%>
		</a>
	</td>
	<td><%=vo.getUser_name()%></td>
	<td><%=vo.getBoard_date()%></td>
	<td><%=vo.getBoard_readcount()%></td>
</tr>
<%} %>
<tr>
	<td colspan="5" align="center">
	[이전]
	[다음]	
	</td>
</tr>
<tr>
 	<td colspan="4">
 		<select name="criti">
 			<option value="board_name">작성자</option>
 			<option value="board_subject">제목</option>
 			<option value="board_content">글내용</option>
 		</select>
 		<input type="text" name="text" required>
 		<input type="submit" value="검색">
 	</td>
 	<td>
 		<a href="writerview.do">[글쓰기]</a>
 	</td>
</tr>
</table>
</form>
<%}catch(Exception e){%>
	e.printStackTrace();
} %>
<%response.sendRedirect("view/mainBoard.jsp"); %>


</body>
</html>