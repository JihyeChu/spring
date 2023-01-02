<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>ticketEnd.jsp 입니다.</h1>
	
	고객 아이디: ${ticketInfo.consumerId}<br/>
	티켓 구매수: ${ticketInfo.amount}<br/><br/>
	
	<a href="ticket">돌아가기</a>

</body>
</html>