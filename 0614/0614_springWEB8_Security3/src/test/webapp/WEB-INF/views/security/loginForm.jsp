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
	
	<c:url var="loginUrl" value="j_spring_security_check"/>
	<form action="${loginUrl}" method="post">
	
		<!-- 로그인 실패 메시지를 출력한다. -->
		<c:if test="${param.ng != null}">
			<p>
				Login NG<br/>
				<!-- exception이 발생되지 않았을 때 메시지를 출력한다. -->
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL}">
					maessage: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"></c:out>
				</c:if>
				
			</p>
		</c:if>	
		ID: <input type="text" name="j_username" /><br/>
		PW: <input type="text" name="j_password" /><br/>
		<input type="submit" value="로그인" />
	</form>

	
</body>
</html>
















