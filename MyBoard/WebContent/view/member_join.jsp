<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

</head>
<body>
<form action="../insert.login" method="post">
	<table>
		<tr>
			<td colspan="2" align="center"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" placeholder="ID" name="id"required></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" placeholder="비밀번호" name="passwd"required></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" placeholder="이름" name="name"required></td>
		</tr>
		<tr>
			<td>핸드폰번호</td>
			<td>
			<input type="text" placeholder="핸드폰번호" name="phonnum"required>
			<br>
			-를 뺴고 적어주세요.
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="전송">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
</body>
</html>