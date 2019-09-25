<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>english board list</title>
<jsp:include page="../main/Header.jsp" />
</head>
<body>
	<h1>English Board List</h1>
	<table>
		<tr>
			<td><select name="searchType">
					<option value="1" ${searchType == "1" ? 'selected' : ''}>이름
					<option value="2" ${searchType == "2" ? 'selected' : ''}>제목
					<option value="3" ${searchType == "3" ? 'selected' : ''}>내용
			</select></td>
			<td><input type="text" name="search" value="${search}">
				<input type="button" value="검색" onclick="sendForm()"></td>
		</tr>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>등록일자</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="english" items="${englishs}" begin="${page.startrow}"
			end="${page.endrow}" varStatus="status">
			<tr>
				<td>${page.totalrows-((page.where - 1) * page.maxrows + status.count -1)}</td>
				<td><a
					href="update.do?bno=${english.bno}&searchType=${searchType}&search=${search}">${english.name}</a></td>
				<td>${english.subject}</td>
				<td><fmt:formatDate value="${english.createddate}"
						pattern="yy-MM-dd" /></td>
				<%-- <td>${english.readcount}</td> --%>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><input type="button" value="새글등록" onclick="location.href='insert.do'"></td>
		</tr>
	</table>
	<jsp:include page="../forInclude/pageNumber.jsp" />
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>