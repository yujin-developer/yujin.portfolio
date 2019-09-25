<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.pageNum {
	text-align: center;
	/* font-family: "font1"; */
	font-size: 30px;
}
</style>
<br>
<br>
<br>
<div class="pageNum">
	<c:if test="${page.endpage >= 0}">
		<c:choose>
			<c:when test="${page.wheregroup > 1}">
				<a href="list.do?go=1&searchType=${searchType}&search=${search}">《</a>
				<a
					href="list.do?gogroup=${page.priorgroup}&searchType=${searchType}&search=${search}">〈</a>
			</c:when>
			<c:otherwise>
		《
		〈
	</c:otherwise>
		</c:choose>
		<c:forEach var="p" begin="${page.startpage}" end="${page.endpage}">
			<c:choose>
				<c:when test="${p == page.where}">
			${p}
		</c:when>
				<c:otherwise>
					<a href="list.do?go=${p}&searchType=${searchType}&search=${search}">${p}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${page.wheregroup < page.totalgroup}">
				<a
					href="list.do?gogroup=${page.nextgroup}&searchType=${searchType}&search=${search}">〉</a>
				<a
					href="list.do?gogroup=${page.totalgroup}&searchType=${searchType}&search=${search}">》</a>
			</c:when>
			<c:otherwise>
		〉
		》
	</c:otherwise>
		</c:choose>
	</c:if>
</div>