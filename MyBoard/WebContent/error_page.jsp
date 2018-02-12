<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code=request.getParameter("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=code %></title>
</head>
<body>
	<script type>
		<%switch(code){
			case"로그인오류":%>
				alert('아이디,혹은 비밀번호를 확인해주세요');
			<%break;
			case"회원가입실패":	%>		
				alert('알수없는 오류 : 회원가입 실패');
			<%break;
			case"":
			
			break;
			}%>
		
		history.back();
	</script>

</body>
</html>