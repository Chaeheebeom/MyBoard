<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>
<form action="login.login" method="post">
<table>
	<tr>
		<td colspan="2"><input type="text" name="id" placeholder="아이디" required></td> <%--placeholder: 투명글자 required:비어있으면 다음칸으로 못넘어감. --%>
		<td rowspan="2">
		<input type="submit" value="login">
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="text" name="passwd" placeholder="비밀번호" required></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="view/member_join.jsp">회원가입</a>
		</td>
		<td></td>
	</tr>  
</table>
</form>
</body>
</html>