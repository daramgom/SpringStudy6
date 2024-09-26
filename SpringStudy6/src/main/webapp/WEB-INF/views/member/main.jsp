<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL-core 라이브러리 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/views/member/main.jsp</h1>
	
	<!-- 사용자 ID님 환영합니다 메시지 출력 -->
	<!-- 스크립틀릿, 스크립틀릿 표현식 사용하지 앞으로 사용하지 않음 -->
	<!-- el표현식 jstl 라이브러리 스프링 프레임워크가 기본 탑재 (pom.xml 의존성 주입) -->
	
	<!-- 로그인 성공한 사용자만 이용가능 -->
	<%-- <c:if test="${sessionScope.id == null }"> --%>
	<c:if test="${empty sessionScope.id }">
		<!-- 로그인 정보가 없을 때 로그인 페이지로 이동 -->
		<c:redirect url="/member/login"/>
	</c:if>
	
	<big><b>${sessionScope.id }</b></big>님 환영합니다 <br>
	<h2>${id }</h2>
	<input type="button" value="로그아웃" onclick="location.href='/member/logout';">
	
	<hr>
	
	<h2><a href="/member/info" style="text-decoration: none; color: black;">회원정보 조회 (info) </a></h2>
	<h2><a href="/member/update" style="text-decoration: none; color: black;">회원정보 수정 (update) </a></h2>
	<h2><a href="/member/delete" style="text-decoration: none; color: black;">회원탈퇴 (delete) </a></h2>
	
	<c:if test="${!empty id && id.equals('admin') }">
	<!-- 관리자 기능 (사용자 admin)일 때 사용가능 -->
	<h2><a href="/member/list" style="text-decoration: none; color: black;">회원정보 목록 (list) </a></h2>
	</c:if>
	
</body>
</html>