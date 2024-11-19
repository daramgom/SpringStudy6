<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/uploadResult.jsp</title>
</head>
<body>

	<h1>/views/file/uploadResult.jsp</h1>
	${map }
	<hr>
	
	아이디 : ${map.userid } <br>
	이름 : ${map.username } <br>
	<!-- 파일 : ${file } <br> -->
	<%-- 파일 : <a href="C:\\upload\\${file }">${file }</a> <br> --%>
	<c:forEach items="${map.fileList }" var="file">
		파일 : <a href="/file/download?fileName=${file }">${file }</a> <br>
		<img src="/file/download?fileName=${file }"> <br>
		썸네일 : <img src="/file/thDownload?fileName=${file }">
	</c:forEach>
	
	<a href="/file/form"> 다시 업로드 화면으로 ... </a>
	

</body>
</html>