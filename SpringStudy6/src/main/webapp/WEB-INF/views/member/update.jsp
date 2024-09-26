<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/views/member/update.jsp</h1>
	
	<fieldset>
		<legend> 회원정보수정 </legend>
		<%-- ${memberVO } --%>
		
		<form action="" method="post">
			아이디 : <input type="text" name="userid" value="${memberVO.userid }" readonly="readonly"> <br>
			비밀번호 : <input type="password" name="userpw"> <br>
			이름 : <input type="text" name="username" autocomplete="none" value="${memberVO.username }"> <br>
			이메일 : <input type="text" name="useremail" value="${memberVO.useremail }"> <br>
			
			<input type="submit" value="회원정보수정">
		</form>
	
	</fieldset>
	<h3><a href="/member/main" style="text-decoration: none; color: black;">메인페이지 이동</a></h3>
</body>
</html>