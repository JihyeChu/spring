<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변형 게시판 글 입력</title>
</head>
<body>

	<form action="insertOK" method="post">
		<table width="600" align="center" border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th colspan="2">답변형 게시판 입력하기</th>
			</tr>
			<tr>
				<th width="100">이름</th>
				<td width="500">
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="subject" style="width: 98%"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" name="content" style="resize: none; width: 99%"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2" align="center">
					<input type="submit" value="저장하기"/>
					<input type="reset" value="다시쓰기"/>
					<input type="button" value="돌아가기" onclick="history.back()"/>
				</th>
			</tr>
		</table>
	</form>

</body>
</html>









