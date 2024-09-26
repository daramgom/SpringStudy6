<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/views/member/delete.jsp</h1>
	<fieldset>
	<legend> 회원탈퇴</legend>
		<form action="" method="post">
			<input type="hidden" name="userid" value="${sessionScope.id }">
			비밀번호 : <input type="password" name="userpw"> <br>
			<input type="submit" value="회원탈퇴">
		</form>
	</fieldset>
	<h3><a href="/member/main" style="text-decoration: none; color: black;">메인페이지 이동</a></h3>
</body>
</html>