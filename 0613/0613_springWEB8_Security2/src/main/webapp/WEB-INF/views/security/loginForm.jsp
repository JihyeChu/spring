<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>

	<h1>시큐리티 사용자 정의 로그인 폼</h1>
	
	<h2>loginForm.jsp</h2>
	
	<!-- action에 j_spring_security_check를 넣어줘야 시큐리티 인증 절차를 거칠 수 있다. -->
	<c:url var="loginUrl" value="j_spring_security_check"/>
	<form action="${loginUrl}" method="post">
		<%-- 
			시큐리티를 사용하려면 id와 password는 미리 정해진 약속된 키워드인 j_username과
			j_password를 사용해야 security-context.xml 파일의 <security:authentication-manager>에서
			사용할 수 있다.
		 --%>
		ID: <input type="text" name="j_username" /><br/>
		PW: <input type="text" name="j_password" /><br/>
		<input type="submit" value="로그인" />
	</form>
	<!-- 
		사용자 정의 로그인 폼을 만들었으면 security-context.xml 파일에 사용자 정의 로그인 폼을 등록하고
		컨트롤러에 로그인 폼을 요청하는 RequestMapping을 추가한다. 
	 -->

	
</body>
</html>
















