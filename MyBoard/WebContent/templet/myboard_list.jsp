<%@page import="myboardvo.UsertblVO"%>
<%@page import="myboardvo.PageInfo"%>
<%@page import="java.util.Vector"%>
<%@page import="myboardvo.MyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="top.html"></jsp:include>
<%
   	Vector<MyBoardVO> list=(Vector<MyBoardVO>)request.getAttribute("list");
	PageInfo info=(PageInfo)request.getAttribute("info");


int totalPage=info.getTotalPage();
int current_page=info.getPage();
int endPage=info.getEndPage();
int startPage=info.getStartPage();
int totalRows=info.getTotalRows();

//cmd가 null이라면 listaction에서 온것 
//cmd에 search값이 있으면 searchAction에서 리스트가 넘어온것임
String search=request.getParameter("cmd");
String criti=(String)request.getParameter("criti");
String text=(String)request.getAttribute("text");
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
			<%for(int i=0;i<vo.getBoard_re_lev();i++){ %>
				&nbsp&nbsp
			<%} %>
		<a href="hitupdate.do?num=<%=vo.getBoard_num()%>&page=<%=current_page%>">
		<%=vo.getBoard_subject()%>
		
		</a></td>
	<td><%=vo.getUser_id()%></td>
	<td><%=vo.getBoard_date()%></td>
	<td><%=vo.getBoard_readcount()%></td>
</tr>
<%} %>
<tr>
	<td colspan="5" align="center">
	<%if(search==null){ %>
		<%if(current_page<=1){ %>
			[이전]&nbsp
		<%}else{ %>	
			<a href="list.do?page=<%=current_page-1%>&cmd=<%=search%>">[이전]</a>&nbsp
		<%} %>
		
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="list.do?page=<%=i%>&cmd=<%=search%>"><%=i %></a>&nbsp
		<%} %>
		
		<%if(current_page>=totalPage){ %>
			[다음]
		<%}else{ %>
			<a href="list.do?page=<%=current_page+1%>&cmd=<%=search%>">[다음]</a>
		<%} %>
	<%}else{ %>
		<%if(current_page<=1){ %>
			[이전]&nbsp
		<%}else{ %>	
			<a href="list.do?page=<%=current_page-1%>&criti=<%=criti%>&text=<%=text%>&cmd=<%=search%>">[이전]</a>&nbsp
		<%} %>
		
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="list.do?page=<%=i%>&criti=<%=criti%>&text=<%=text%>&cmd=<%=search%>"><%=i %></a>&nbsp
		<%} %>
		
		<%if(current_page>=totalPage){ %>
			[다음]
		<%}else{ %>
			<a href="list.do?page=<%=current_page+1%>&criti=<%=criti%>&text=<%=text%>&cmd=<%=search%>">[다음]</a>
		<%} %>
	<%} %>
	
		
	</td>
</tr>
<tr>
 	<td colspan="4">
 		<select name="criti">
 			<option value="user_id">작성자</option>
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



</body>
</html>