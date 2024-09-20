<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>/views/itwill.jsp</h2>
	<hr>
	
	<!-- jsp 내장객체를 사용 -->
	<h3>파라메터 데이터 (paramater)</h3>
	<h3> msg (paramater) : <%=request.getParameter("msg") %> </h3>
	<h3> param.msg (el 표현식1) : ${param.msg } </h3>
	<hr>
	
	<h3>영역객체 데이터(attribute)</h3>
	<h3> msg (el 표현식2) : ${msg } </h3>
	<h3> requestScope.msg (el 표현식3) : ${requestScope.msg } </h3>
	<hr>
	
	@ModelAttribute("msg") String msg <br>
	--> request.getParamater("msg") + request.setAttribute("msg",값)
	<hr>
	
	<h3> doB () 실행시 전달된 VO객체 정보 출력 </h3>
	<h3> MemberVO : <%-- requestScope. 생략 --%>${memberVO } </h3>
	<h3> vo1 : <%-- requestScope. 생략 --%>${vo1 } </h3>
	<hr>
	
	
</body>
</html>