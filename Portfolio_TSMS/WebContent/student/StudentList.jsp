<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원 목록 페이지</title>
<style>
* {
	margin : 0;
	padding : 0;
}

.wrap {
    width : 1200px;
    margin : 0 auto;
    text-align : left;
}

h1 {
	text-align : center;
}

table {
	margin : 0 auto;
	font-size: 20px;
}
</style>
<script>
	function sendForm() {
		document.form.submit();
	}
</script>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
<div class="wrap">
	<h1>학생 리스트</h1>
	<form action="list.do" name="form">
		<table>
			<tr>
				<td><select name="searchType">
						<option value="1" selected>성별
						<option value="2">과목
						<option value="3">지역
						<option value="4">학년
				</select></td>
				<td><input type="text" name="search"> <input
					type="button" value="검색" onclick="sendForm()"></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>성별</th>
				<th>학년</th>
				<th>과목</th>
				<th>지역</th>
				<th>등록일자</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="student" items="${students}" begin="${page.startrow}"
				end="${page.endrow}" varStatus="status">
				<tr>
					<td>${page.totalrows-((page.where - 1) * page.maxrows + status.count -1)}</td>
					<td><a
						href="info.do?id=${student.id}&searchType=${searchType}&search=${search}">${student.name}</a></td>
					<td>${student.gender}</td>
					<td>${student.grade}</td>
					<td>${teacher.subject == '무관' ? '수학 , 영어' : ''}
						${teacher.subject == '수학' ? '수학' : ''} ${teacher.subject == '영어' ? '영어' : ''}</td>
					<td>${student.address}</td>
					<td><fmt:formatDate value="${student.createddate}"
							pattern="yy-MM-dd" /></td>
					<td>${student.readcount}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../forInclude/pageNumber.jsp" />
	</form>
</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>