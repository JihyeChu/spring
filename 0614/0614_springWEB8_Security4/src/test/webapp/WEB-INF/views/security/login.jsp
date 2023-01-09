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

	<h1>login.jsp 입니다.</h1>
	
	<%-- 
	<!-- userPrincipal(사용자 권한) => 사용자가 비어있지 않다면 => 로그인 상태라면 -->
	<c:if test="${not empty pageContext.request.userPrincipal}">
		<p>
			Login 메시지<br/>
			<!-- userPrincipal.name: 로그인한 id를 얻어온다. -->
			${pageContext.request.userPrincipal.name}님 환영합니다.<br/>
			
			<!-- j_spring_security_logout: 로그아웃 처리되고 컨트롤러에 "/"를 요청한다. -->
			<a href="${pageContext.request.contextPath}/j_spring_security_logout">로그아웃</a>
		</p>
	</c:if>
	--%>
	
	<!-- spring-security-taglibs를 이용해서 ROLE_USER 그룹으로 들어온 사용자가 있나 확인한다. -->
	<s:authorize ifAnyGranted="ROLE_USER">
		<p>
			Login 메시지<br/>
			<%-- ${pageContext.request.userPrincipal.name}님 환영합니다.<br/> --%>
			<!-- spring-security-taglibs를 이용해서 id를 출력하기 -->
			<s:authentication property="name"/>님 환영합니다.<br/>
			<a href="${pageContext.request.contextPath}/j_spring_security_logout">로그아웃</a>
		</p>
	</s:authorize>
	
	<%-- 
	<!-- userPrincipal(사용자 권한) => 사용자가 비어있다면 => 로그아웃 상태라면 -->
	<c:if test="${empty pageContext.request.userPrincipal}">
		<p>
			Logout 메시지
		</p>
	</c:if>
	--%>
		
	<!-- spring-security-taglibs를 이용해서 ROLE_USER 그룹으로 들어온 사용자가 없나 확인한다. -->
	<s:authorize ifNotGranted="ROLE_USER">
		<p>
			Logout 메시지
		</p>
	</s:authorize>


</body>
</html>


















