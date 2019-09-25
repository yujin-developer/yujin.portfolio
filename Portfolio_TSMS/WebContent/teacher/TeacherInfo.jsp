<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선생님 상세정보 페이지</title>
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

img {
	margin: 10px;
}

</style>
<script>
$(function () {
	//선생님으로 로그인 돼있다면 매칭버튼 지우기
	if(${!empty sessionScope.teacher}) {
		$("input[type=button]").hide();
	}
	
	$("#dialog").dialog({
		autoOpen : false, 
		//position:[100,200], //x,y  값
		//"center", "left", "right", "top", "bottom"
		//modal : true, 
		resizable: true, //크기 조절 
		width: 600, 
		height: 600,
		buttons : {
			"확인" : function() {
				$(this).dialog("close");
			},
			"취소" : function() {
				$(this).dialog("close");
			}
		}
	});
});

	function view(temp) {
		filename = "http://localhost:8088/Portfolio_TSMS/upload/" + temp;
		i = window.open(filename, "win", "height=350, width=450");
	}
	
	function sendMail(param1) {
		var result = confirm('매칭을 시도하시겠습니까?');
		if(result == true) {
			$.ajax({
				url : "../teacher/sendMail.do",
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
	<h1>선생님 상세정보</h1><br>
	<table>
	
		<tr>
			<c:set var="url" value="http://localhost:8088/Portfolio_TSMS/upload/" />
			<th><a onClick="view('${teacher.photo}');"><img width=150
					height=150 src="${pageScope.url}${teacher.photo}"></a></th>
			
		</tr>
		
		<tr>
			<th>이름</th>
			<td>${teacher.name}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${teacher.age}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${teacher.gender}</td>
		</tr>
		<tr>
			<th>대학교</th>
			<td>${teacher.university}</td>
		</tr>

		<tr>
			<th>과외가능지역1</th>
			<td>${teacher.address1}</td>
		</tr>

		<tr>
			<th>과외가능지역2</th>
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
			<th>자기소개</th>
			<td>${teacher.introduce}</td>
		</tr>
		<tr>
			<td><input type="button" value="매칭시도하기"
				onclick="sendMail('${teacher.id}');"></td>
		</tr>
	</table>
</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>