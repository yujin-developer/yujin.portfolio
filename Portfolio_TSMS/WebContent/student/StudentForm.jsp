<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원가입 페이지</title>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script src="../forInclude/functionForStudentInsert.js"></script>
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

table input.box {
	height: 35px;
	width: 200px;
}

table tr td,th {
	padding-bottom: 13px;
}

#checkId,#checkPwd1,#checkPwd2 {
	color: red;
	font-size: 13px;
}

.btns {
	width: 100px;
	height: 40px;
	margin: 10px;
	background-color: #8fa9bd;
	border: none;
	cursor: pointer;
	font-size: 20px;
}

#center {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
	<div class="wrap">
	<h1>회원가입 (학생)</h1><br>
	<form action="insert.do" method="post" name="form">
		<input type="hidden" name="noMulti" value="noMulti">
		<table>
			<tr>
				<th>아이디</th>
				<td><input class="box" type="text" name="id" placeholder="4~15 자리 영문/숫자 조합"
					onchange="checkId(this.value)"><br><div id="checkId"></div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input class="box" type="password" id="password1" name="password"
					placeholder="4~15 자리 영문/숫자 조합" onchange="checkPwd1(this.value)"><br><div id="checkPwd1"></div></td>
			</tr>
			<tr>
				<th>비밀번호 재확인</th>
				<td><input class="box" type="password" id="password2" name="password"
					placeholder="4~15 자리 영문/숫자 조합" onchange="checkPwd2(this.value)"><br><div id="checkPwd2"></div></td>
				
			</tr>
			<tr>
				<th>이름</th>
				<td><input class="box" type="text" name="name"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><select name="gender">
						<option value="여">여</option>
						<option value="남">남</option>
				</select></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input class="box" type="text" name="age" placeholder="숫자만 입력하세요."></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input class="box" type="text" id="email1">@<input class="box" type="text"
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
						<option value="무관">무관</option>
						<option value="수학">수학</option>
						<option value="영어">영어</option>
				</select></td>
			</tr>
			<tr>
				<th>학습학년</th>
				<td><label><input type="checkbox" id="e" name="grade"
						value="e" class="setCheckedGrade">초등학생</label><br> <label><input
						type="checkbox" id="m1" name="grade" value="m1"
						class="setCheckedGrade">중학생(1)</label> <label><input
						type="checkbox" id="m2" name="grade" value="m2"
						class="setCheckedGrade">중학생(2)</label> <label><input
						type="checkbox" id="m3" name="grade" value="m3"
						class="setCheckedGrade">중학생(3)</label><br> <label><input
						type="checkbox" id="h1" name="grade" value="h1"
						class="setCheckedGrade">고등학생(1)</label> <label><input
						type="checkbox" id="h2" name="grade" value="h2"
						class="setCheckedGrade">고등학생(2)</label> <label><input
						type="checkbox" id="h3" name="grade" value="h3"
						class="setCheckedGrade">고등학생(3)</label></td>
			</tr>
			<tr>
				<th>학습가능요일</th>
				<td><label><input type="checkbox" id="allDay"
						name="day" value="all">무관</label> <label><input
						type="checkbox" id="mon" name="day" value="월"
						class="setCheckedDay">월</label> <label><input
						type="checkbox" id="tue" name="day" value="화"
						class="setCheckedDay">화</label> <label><input
						type="checkbox" id="wed" name="day" value="수"
						class="setCheckedDay">수</label> <label><input
						type="checkbox" id="thr" name="day" value="목"
						class="setCheckedDay">목</label> <label><input
						type="checkbox" id="fri" name="day" value="금"
						class="setCheckedDay">금</label> <label><input
						type="checkbox" id="sat" name="day" value="토"
						class="setCheckedDay">토</label> <label><input
						type="checkbox" id="sun" name="day" value="일"
						class="setCheckedDay">일</label></td>
			</tr>
			<tr>
				<th>학습가능시간</th>
				<td><input type="time" name="time" value="00:00" id="startTime">
					<input type="time" name="time" value="00:00" id="endTime">
					<input type="hidden" name="time" id="hiddenTime"></td>
			</tr>
			<tr>
				<th>자기소개(선택)</th>
				<td><textarea name="introduce" cols="80" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" id="center"><input type="button" value="등록" onclick="check()" class="btns">
					<input type="reset" value="취소" class="btns"></td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>