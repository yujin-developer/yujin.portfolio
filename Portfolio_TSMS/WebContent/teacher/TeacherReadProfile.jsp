<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선생님 프로필 페이지</title>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
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
$(document).ready(function() {
	$('#goModify').on('click', function(e) {
		$("#form").submit();
	});

	if(${successUpdate == 'successUpdate'}) {
		alert('My Profile의 수정이 정상적으로 완료되었습니다.');
	}
});
</script>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
<div class="wrap">
	<h1>My Profile</h1><br>
	<table>
		<tr>
			<th>아이디</th>
			<td>${teacher.id}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>*********</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${teacher.name}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${teacher.gender}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${teacher.age}</td>
		</tr>

		<tr>
			<th>이메일</th>
			<td>${teacher.email}</td>
		</tr>

		<tr>
			<th>대학교</th>
			<td>${teacher.university}</td>
		</tr>

		<tr>
			<th>희망지역1</th>
			<td>${teacher.address1}</td>
		</tr>

		<tr>
			<th>희망지역2</th>
			<td>${teacher.address2}</td>
		</tr>
		<tr>
			<th>과외가능과목</th>
			<td>${teacher.subject == '무관' ? '수학 , 영어' : ''}
				${teacher.subject == '수학' ? '수학' : ''} ${teacher.subject == '영어' ? '영어' : ''}
			</td>
		</tr>
		<tr>
			<th>과외가능학생</th>
			<td>${teacher.grade == 'all' ? '전 학년' : ''}
				${fn:contains(teacher.grade, 'e') ? '초' : ''}
				${fn:contains(teacher.grade, 'm1') ? '중1' : ''}
				${fn:contains(teacher.grade, 'm2') ? '중2' : ''}
				${fn:contains(teacher.grade, 'm3') ? '중3' : ''}
				${fn:contains(teacher.grade, 'h1') ? '고1' : ''}
				${fn:contains(teacher.grade, 'h2') ? '고2' : ''}
				${fn:contains(teacher.grade, 'h3') ? '고3' : ''}</td>
		</tr>
		<tr>
			<th>과외가능요일</th>
			<td>${teacher.day == 'all' ? '월 화 수 목 금 토 일' : ''}
				${fn:contains(teacher.day, '월') ? '월' : ''}
				${fn:contains(teacher.day, '화') ? '화' : ''}
				${fn:contains(teacher.day, '수') ? '수' : ''}
				${fn:contains(teacher.day, '목') ? '목' : ''}
				${fn:contains(teacher.day, '금') ? '금' : ''}
				${fn:contains(teacher.day, '토') ? '토' : ''}
				${fn:contains(teacher.day, '일') ? '일' : ''}</td>
		</tr>
		<tr>
			<th>과외가능시간</th>
			<td>${teacher.time}</td>
		</tr>
		<tr>
			<th>경력 및 자기소개</th>
			<td>${teacher.introduce}</td>
		</tr>
		<tr>
			<th>사진</th>
			<td>${teacher.photo}</td>
		</tr>
		<tr>
			<td><input type="button" value="수정" id="goModify"> <input
				type="button" value="메인페이지 바로가기"></td>
		</tr>
	</table>
	<form action="update.do" id="form">
		<input type="hidden" name="goModify" value="goModify"> <input
			type="hidden" name="id" value="${teacher.id}">
	</form>
</div>
	<jsp:include page="../main/Footer.jsp" />
</body>

</html>