<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>
<style type="text/css">
	a {
		color: black;
		text-decoration: none;
	}
	a:hover {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>

	<table width="1000" align="center" border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th colspan="5">게시판 보기</th>
		</tr>
		<tr>
			<td colspan="5" align="right">
				${mvcboardList.totalCount}(${mvcboardList.currentPage} / ${mvcboardList.totalPage})
			</td>
		</tr>
		<tr>
			<th width="70">글번호</th>
			<th width="620">제목</th>
			<th width="120">이름</th>
			<th width="120">작성일</th>
			<th width="70">조회수</th>
		</tr>
		
		<c:set var="list" value="${mvcboardList.list}"/>
		<c:if test="${list.size() == 0}">
		<tr>
			<td colspan="5" align="right">
				<marquee>테이블에 저장된 글이 없습니다.</marquee>
			</td>
		</tr>
		</c:if>
		
		<c:if test="${list.size() != 0}">
		<c:forEach var="vo" items="${list}">
		<tr>
			<td align="center">${vo.idx}</td>
			<td>
				<!-- 글 레벨에 따른 들어쓰기 -->
				<c:if test="${vo.lev > 0}">
					<c:forEach var="i" begin="1" end="${vo.lev}" step="1">
						&nbsp;&nbsp;&nbsp;
					</c:forEach>
					Re.
				</c:if>
				
				<c:set var="subject" value="${fn:replace(vo.subject, '<', '&lt;')}"/>
				<c:set var="subject" value="${fn:replace(subject, '>', '&gt;')}"/>
				<a href="increment?idx=${vo.idx}&currentPage=${mvcboardList.currentPage}">
					${subject}
				</a>
			</td>
			<td align="center">
				<c:set var="name" value="${fn:replace(vo.name, '<', '&lt;')}"/>
				<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
				${name}
			</td>
			<td align="center">
				<fmt:formatDate value="${vo.writeDate}" pattern="yyyy.MM.dd(E)"/>
			</td>
			<td align="center">${vo.hit}</td>
		</tr>
		</c:forEach>
		</c:if>
		
		<!-- 페이지 이동 버튼 -->
		<tr>
			<td colspan="5" align="center">
			
				<!-- 처음으로 -->
				<c:if test="${mvcboardList.currentPage > 1}">
					<button class="button button1" type="button" title="첫 페이지로 이동합니다." 
							onclick="location.href='?currentPage=1'">처음</button>
				</c:if>
				
				<c:if test="${mvcboardList.currentPage <= 1}">
					<button class="button button2" type="button" title="이미 첫 페이지 입니다." 
							disabled="disabled">처음</button>
				</c:if>
				
				<!-- 10페이지 앞으로 -->
				<c:if test="${mvcboardList.startPage > 1}">
					<button class="button button1" type="button" title="이전 10 페이지로 이동합니다." 
							onclick="location.href='?currentPage=${mvcboardList.startPage - 1}'">
						이전
					</button>
				</c:if>
				
				<c:if test="${mvcboardList.startPage <= 1}">
					<button class="button button2" type="button" title="이미 첫 10 페이지 입니다." 
							disabled="disabled">이전</button>
				</c:if>

				<!-- 페이지 이동 -->
				<c:forEach var="i" begin="${mvcboardList.startPage}" end="${mvcboardList.endPage}" step="1">
				
					<c:if test="${mvcboardList.currentPage == i}">
						<button class="button button2" type="button" disabled="disabled">${i}</button>
					</c:if>
					
					<c:if test="${mvcboardList.currentPage != i}">
						<button class="button button1" type="button" onclick="location.href='?currentPage=${i}'">
							${i}
						</button>
					</c:if>
				
				</c:forEach>

				<!-- 10페이지 뒤로 -->
				<c:if test="${mvcboardList.endPage < mvcboardList.totalPage}">
					<button class="button button1" type="button" title="다음 10 페이지로 이동합니다." 
							onclick="location.href='?currentPage=${mvcboardList.currentPage + 10}'">다음</button>
				</c:if>
				
				<c:if test="${mvcboardList.endPage >= mvcboardList.totalPage}">
					<button class="button button2" type="button" title="이미 마지막 10 페이지 입니다." 
							disabled="disabled">다음</button>
				</c:if>
				
				<!-- 마지막으로 -->
				<c:if test="${mvcboardList.currentPage < mvcboardList.totalPage}">
					<button class="button button1" type="button" title="첫 페이지로 이동합니다." 
							onclick="location.href='?currentPage=${mvcboardList.totalPage}'">마지막</button>
				</c:if>
				
				<c:if test="${mvcboardList.currentPage >= mvcboardList.totalPage}">
					<button class="button button2" type="button" title="이미 마지막 페이지 입니다." disabled="disabled">
						마지막
					</button>
				</c:if>
			
			</td>
		</tr>
		
		<!-- 글쓰기 버튼 -->
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글쓰기" onclick="location.href='insert'"/>
			</td>
		</tr>
	</table>

</body>
</html>










