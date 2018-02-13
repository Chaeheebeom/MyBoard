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
<title></title>
<script>
	function reply_submit() {
		location.href="replyview.do?num=<%=vo.getBoard_num()%>&page=<%=current_page%>"
	}
	function modify_list() {
		location.href="modifyview.do?num=<%=vo.getBoard_num()%>&page=<%=current_page%>";
	}
	function list_submit() {
		location.href="list.do?page=<%=current_page%>";
	}
	function delete_submit(){
		location.href="showdelete.do?num=<%=vo.getBoard_num()%>&page=<%=current_page%>";
	}
</script>
</head>
<body>
<form>
<table>  <!--  td는 기본 th는 재목 -->
<tr>  
	<th>글쓴이</th>
	<td><input type="text" value="<%=vo.getUser_id() %>" readonly></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" value="<%=vo.getBoard_subject() %>" size="50" readonly></td> <!-- size는 길이 -->
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="content" cols="60" rows="20" readonly><%=vo.getBoard_content()%></textarea></td><!-- cols는 가로길이 rows는 세로길이 -->
</tr>
<tr>
	<th>파일첨부</th>
	<%String filename=vo.getBoard_re_file();
		if(filename!=null){
	%>
	<td><a href="view/file_down.jsp?board_re_file=<%=vo.getBoard_re_file()%>"><%=vo.getBoard_re_file() %></a></td>
	<%}else{ %>
	<td></td>
	<%} %>
</tr>
<tr>
	<th colspan="2" >
			<input type="button" value="답변" onclick="reply_submit()">
			<%if(vo.getUser_prcode()==vo2.getPrcode()){ %>
			<input type="button" value="수정" onclick="modify_list()">
			<input type="button" value="삭제" onclick="delete_submit()">
			<%} %>
			<input type="button" value="목록보기" onclick="list_submit()">
			
	</th>
</tr>
</table>
</form>
</body>
</html>