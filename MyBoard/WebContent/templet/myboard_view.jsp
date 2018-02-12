<%@page import="board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>
	function modify_list() {
		
	}
	function list_submit() {
		
	}
	function delete_submit(){
		
	}
</script>
</head>
<body>
<form>
<table>  <!--  td는 기본 th는 재목 -->
<tr>  
	<th>글쓴이</th>
	<td><input type="text" value="" readonly></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" value="" size="50" readonly></td> <!-- size는 길이 -->
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="content" cols="60" rows="20" readonly></textarea></td><!-- cols는 가로길이 rows는 세로길이 -->
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
		<a href="qReplyView.do"><input type="button" value="답변"></a>
		<input type="button" value="수정" onclick="modify_list()">
		<input type="button" value="삭제" onclick="delete_submit()">
		<input type="button" value="목록보기" onclick="list_submit()">
	</th>
</tr>
</table>
</form>
</body>
</html>