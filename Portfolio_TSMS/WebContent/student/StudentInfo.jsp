<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세정보 페이지</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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

h1,th {
	text-align : center;
}

table {
	margin : 0 auto;
	font-size: 20px;
}
</style>
<script>
$(function () {
	//학생으로 로그인 돼있다면 매칭버튼 지우기
	if(${!empty sessionScope.student}) {
		$("input[type=button]").hide();
	}
});

function sendMail(param1) {
	var result = confirm('매칭을 시도하시겠습니까?');
	if(result == true) {
		$.ajax({
			url : "../student/sendMail.do",
			data : "id=" + param1,
			dataType:'text',
			type : "GET",
			success : function() {
					alert("매칭 이메일 보내기 성공!");
			}
		});
	}
}
</script>

</head>
<body>
	<jsp:include page="../main/Header.jsp" />
<div class="wrap">
	<h1>학생 상세정보</h1><br>
	<table>
		<tr>
			<th>이름</th>
			<td>${student.name}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${student.age}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${student.gender}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${student.address}</td>
		</tr>
		<tr>
			<th>학습과목</th>
			<td>${student.subject == '무관' ? '수학 , 영어' : ''}
				${student.subject == '수학' ? '수학' : ''} ${student.subject == '영어' ? '영어' : ''}
			</td>
		</tr>
		<tr>
			<th>학습학년</th>
			<td>${student.grade == 'e' ? '초' : ''} ${student.grade == 'm1' ? '중1' : ''}
				${student.grade == 'm2' ? '중2' : ''} ${student.grade == 'm3' ? '중3' : ''}
				${student.grade == 'h1' ? '고1' : ''} ${student.grade == 'h2' ? '고2' : ''}
				${student.grade == 'h3' ? '고3' : ''}</td>
		</tr>
		<tr>
			<th>학습가능요일</th>
			<td>${student.day == 'all' ? '월 화 수 목 금 토 일' : ''}
				${fn:contains(student.day, '월') ? '월' : ''}
				${fn:contains(student.day, '화') ? '화' : ''}
				${fn:contains(student.day, '수') ? '수' : ''}
				${fn:contains(student.day, '목') ? '목' : ''}
				${fn:contains(student.day, '금') ? '금' : ''}
				${fn:contains(student.day, '토') ? '토' : ''}
				${fn:contains(student.day, '일') ? '일' : ''}</td>
		</tr>
		<tr>
			<th>학습가능시간</th>
			<td>${student.time}</td>
		</tr>
		<tr>
			<th>자기소개</th>
			<td>${student.introduce}</td>
		</tr>
		<tr>
			<td><input type="button" value="매칭시도하기" onclick="sendMail('${student.id}');"></td>
		</tr>
	</table>
</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>