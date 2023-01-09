<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>welcome.jsp 입니다.</h1>

	<!-- spring-security-taglibs를 이용해서 ROLE_ADMIN 그룹으로 들어온 사용자가 있나 확인한다. -->
	<s:authorize ifAnyGranted="ROLE_ADMIN">
		<p>
			Login 메시지<br/>
			<s:authentication property="name"/>님 환영합니다.<br/>
			<a href="${pageContext.request.contextPath}/j_spring_security_logout">로그아웃</a>
		</p>
	</s:authorize>

	<!-- spring-security-taglibs를 이용해서 ROLE_ADMIN 그룹으로 들어온 사용자가 없나 확인한다. -->
	<s:authorize ifNotGranted="ROLE_ADMIN">
		<p>
			Logout 메시지
		</p>
	</s:authorize>
	
</body>
</html>

















