<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 프로필 수정 페이지</title>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script src="../forInclude/functionForStudentInsert.js"></script>
<script>
	$(document).ready(function() {
		//이메일 DB에서 넘어온거 분리
		var email1 = "${fn:split(student.email, '@')[0]}";
		var email2 = "${fn:split(student.email, '@')[1]}";
		var email = "${student.email}";
		$("#email1").val(email1);
		$("#email2").val(email2);
		$('#hiddenEmail').val(email);

		//주소DB에서 넘어온거 분리
		var si_do1 = "${fn:split(student.address, ' ')[0]}";
		var si_gun_gu1 = "${fn:split(student.address, ' ')[1]}";
		var address = "${student.address}";
		$("#si_do1").val(si_do1);
		$("#si_gun_gu1").val(si_gun_gu1);
		changeSido1(si_do1, si_gun_gu1);
		$("#hiddenAddress1").val(address);

		//DB에서 넘어온 grade가 'all'일때 전체 체크 및 비활성화
		if(${student.grade == 'all'}) {
			$("input[name='grade']").prop("checked", true);
			$(".setCheckedGrade").prop("disabled", true);
		}
		
		//DB에서 넘어온 day가 'all'일때 전체 체크 및 비활성화
		if(${student.day == 'all'}) {
			$("input[name='day']").prop("checked", true);
			$(".setCheckedDay").prop("disabled", true);
		}
		
		//DB에서 넘어온 time 분리해서 value에 넣기
		var startTime = "${fn:split(student.time,'~')[0]}";
		var endTime = "${fn:split(student.time,'~')[1]}";
		$("#startTime").val(startTime);
		$("#endTime").val(endTime);
		
		//탈퇴 클릭후 비번 한번더 체크
		$("#checkDelete").click(function (){
			var checkPwd = prompt('회원 탈퇴를 위해 비밀번호를 입력해 주세요.','');
			if(checkPwd == ${sessionScope.student.password}) {
				alert('회원 탈퇴가 완료 되었습니다. 그동안 TSMS 서비스를 이용해 주셔서 감사합니다.')
				location.href='delete.do?student_id='+${student.student_id};
			}else {
				alert('비밀번호가 일치하지 않습니다.');
				return false;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
	<h1>My Profile 수정하기</h1>
	<form action="update.do" method="post" name="form">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" placeholder="4~15 자리 영문/숫자 조합"
					onchange="checkId(this.value)" value="${student.id}"></td>
				<td><div id="checkId"></div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="password1" name="password"
					placeholder="4~15 자리 영문/숫자 조합" value="${student.password}"></td>
			</tr>
			<tr>
				<th>비밀번호 재확인</th>
				<td><input type="password" id="password2"
					placeholder="4~15 자리 영문/숫자 조합" onchange="checkPwd(this.value)"
					value="${student.password}"></td>
				<td><div id="checkPwd"></div></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="${student.name}"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><select name="gender">
						<option value="여" ${student.gender == '여' ? 'selected' : '' }>여</option>
						<option value="남" ${student.gender == '남' ? 'selected' : '' }>남</option>
				</select></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age" placeholder="숫자만 입력하세요."
					value="${student.age}"></td>
			</tr>

			<tr>
				<th>이메일</th>
				<td><input type="text" id="email1">@<input type="text"
					id="email2"> <select id="chooseEmail"
					onchange="selectEmail(this.value)">
						<option value="1">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
				</select> <input type="hidden" id="hiddenEmail" name="email"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><select onchange="changeSido1(this.value)" id="si_do1">
						<option value="1">시/도</option>
						<c:forEach var="addr" items="${address}">
							<option value="${addr.si_do}">${addr.si_do}</option>
						</c:forEach>
				</select> <select id="si_gun_gu1" onclick="realAddr1(this.value)">
						<option value="1">시/군/구</option>
				</select> <input type="hidden" id="hiddenAddress1" name="address"></td>
			</tr>
			<tr>
				<th>학습과목</th>
				<td><select name="subject">
						<option value="무관" ${student.subject == '무관' ? 'selected' : ''}>무관</option>
						<option value="수학" ${student.subject == '수학' ? 'selected' : ''}>수학</option>
						<option value="영어" ${student.subject == '영어' ? 'selected' : ''}>영어</option>
				</select></td>
			</tr>
			<tr>
				<th>학습학년</th>
				<td><label><input type="checkbox" id="allGrade"
						name="grade" value="all">무관</label> <label><input
						type="checkbox" id="e" name="grade" value="e"
						class="setCheckedGrade"
						${fn:contains(student.grade,'e') ? 'checked' : ''}>초등학생</label><br>
					<label><input type="checkbox" id="m1" name="grade"
						value="m1" class="setCheckedGrade"
						${fn:contains(student.grade,'m1') ? 'checked' : ''}>중학생(1)</label>
					<label><input type="checkbox" id="m2" name="grade"
						value="m2" class="setCheckedGrade"
						${fn:contains(student.grade,'m2') ? 'checked' : ''}>중학생(2)</label>
					<label><input type="checkbox" id="m3" name="grade"
						value="m3" class="setCheckedGrade"
						${fn:contains(student.grade,'m3') ? 'checked' : ''}>중학생(3)</label><br>
					<label><input type="checkbox" id="h1" name="grade"
						value="h1" class="setCheckedGrade"
						${fn:contains(student.grade,'h1') ? 'checked' : ''}>고등학생(1)</label>
					<label><input type="checkbox" id="h2" name="grade"
						value="h2" class="setCheckedGrade"
						${fn:contains(student.grade,'h2') ? 'checked' : ''}>고등학생(2)</label>
					<label><input type="checkbox" id="h3" name="grade"
						value="h3" class="setCheckedGrade"
						${fn:contains(student.grade,'h3') ? 'checked' : ''}>고등학생(3)</label></td>
			</tr>
			<tr>
				<th>학습가능요일</th>
				<td><label><input type="checkbox" id="allDay"
						name="day" value="all">무관</label> <label><input
						type="checkbox" id="mon" name="day" value="월"
						class="setCheckedDay"
						${fn:contains(student.day,'월') ? 'checked' : ''}>월</label> <label><input
						type="checkbox" id="tue" name="day" value="화"
						class="setCheckedDay"
						${fn:contains(student.day,'화') ? 'checked' : ''}>화</label> <label><input
						type="checkbox" id="wed" name="day" value="수"
						class="setCheckedDay"
						${fn:contains(student.day,'수') ? 'checked' : ''}>수</label> <label><input
						type="checkbox" id="thr" name="day" value="목"
						class="setCheckedDay"
						${fn:contains(student.day,'목') ? 'checked' : ''}>목</label> <label><input
						type="checkbox" id="fri" name="day" value="금"
						class="setCheckedDay"
						${fn:contains(student.day,'금') ? 'checked' : ''}>금</label> <label><input
						type="checkbox" id="sat" name="day" value="토"
						class="setCheckedDay"
						${fn:contains(student.day,'토') ? 'checked' : ''}>토</label> <label><input
						type="checkbox" id="sun" name="day" value="일"
						class="setCheckedDay"
						${fn:contains(student.day,'일') ? 'checked' : ''}>일</label></td>
			</tr>
			<tr>
				<th>학습가능시간</th>
				<td><input type="time" name="time" value="00:00" id="startTime">
					<input type="time" name="time" value="00:00" id="endTime">
					<input type="hidden" name="time" id="hiddenTime"></td>
			</tr>
			<tr>
				<th>자기소개(선택)</th>
				<td><textarea name="introduce" cols="20" rows="5">${student.introduce}</textarea></td>
			</tr>
			<tr>
				<td><input type="button" value="수정" onclick="check()">
					<input type="button" value="회원탈퇴" id="checkDelete"> <input
					type="button" value="새로작성" onclick="reset(this)"> <input
					type="button" value="메인페이지 바로가기"> <input type="hidden"
					name="student_id" value="${student.student_id}"></td>
			</tr>
		</table>
	</form>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>