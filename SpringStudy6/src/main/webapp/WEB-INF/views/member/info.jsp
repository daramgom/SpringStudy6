<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/views/member/info.jsp</h1>
	
	${memberVo } 
	<hr>
	
	<h2> 아이디: ${memberVO.userid }</h2>
	<h2> 비밀번호: ${memberVO.userpw }</h2>
	<h2> 이름: ${memberVO.username }</h2>
	<h2> 이메일: ${memberVO.useremail }</h2>
	<h2> 회원가입일자: ${memberVO.regdate }</h2>
	
	<h3><a href="/member/main" style="text-decoration: none; color: black;">메인페이지 이동</a></h3>
	

	
	
	
</body>
</html>