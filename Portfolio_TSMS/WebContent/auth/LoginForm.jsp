<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
h1 {
	font-size: 55px;
	padding-top: 50px;
}

#login-box {
	text-align: center;
}

img {
	height: 40px;
	width: 40px;
	margin-bottom: -10px;
}

input.boxsize{
	height: 40px;
	margin: 0 0 0 10px;
	padding: 0;
	/* box-shadow: 10px 10px 5px grey; */
}

input.loginBtn{
	color: white;
	margin-top: 15px;
	width: 219px;
	height: 46px;
	border: 1px solid;
	border-radius: 18px;
	cursor: pointer;
}

#backcolor1 {
	background-color: #218a91;
}

#backcolor2 {
	background-color: #3f6196;
}

#backcolor3 {
	background-color: #3f6196;
}

.btn {
	background-color: white;
	border: none;
	font-size: 20px;
	margin-bottom: 10px;
}

table {
	margin : 0 auto;
}

#t-btn, #s-btn {
	cursor: pointer;
}
</style>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script>
$(document).ready(function() {
	if(${reLogin == 'reLogin'}) {
		alert('My Profile의 아이디 또는 비밀번호가 수정되었으므로 다시 로그인 해야 합니다.');
	}
	
	if(${loginFail == 'loginFail'}) {
		alert('아이디 또는 비밀번호가 틀렸습니다.');
	}
	$("#teacherForm").show();
	$("#studentForm").hide();
	$("#t-btn").css("box-shadow", "3px 3px grey");
});

function showTeacherForm() {
	$("#s-btn").css("box-shadow", "");
	$("#t-btn").css("box-shadow", "3px 3px grey");
	$("#studentForm").hide();
	$("#teacherForm").show();
}
function showStudentForm() {
	$("#t-btn").css("box-shadow", "");
	$("#s-btn").css("box-shadow", "3px 3px grey");
	$("#studentForm").show();
	$("#teacherForm").hide();
}
</script>
</head>
<body>
	
	<div id="login-box">
		<h1>Welcome!</h1>
		<table>
			<tr><td><input id="t-btn" class="btn" type="button" value="TEACHER" onclick="showTeacherForm()"></td>
			<td><input id="s-btn" class="btn" type="button" value="STUDENT" onclick="showStudentForm()"></td></tr>
		</table>	
			<form action="login.do" method="post" id="teacherForm">
			<table>
				<tr>
					<td><img src="../image/iconmonstr-user-6.svg"></td>
					<td><input type="text" name="id" placeholder="UserName" class="boxsize"></td>
				</tr>
				<tr>
					<td><img src="../image/iconmonstr-password-10.svg"></td>
					<td><input type="password" name="password" placeholder="Password" class="boxsize"></td>
				</tr>
			</table>
				<input id="backcolor1" type="submit" value="LOGIN" class="loginBtn"> 
				<input type="hidden" name="teacherLogin" value="teacherLogin"> 
				<input type="hidden" name="noMulti" value="noMulti">
			</form>
			
			<form action="login.do" method="post" id="studentForm">
				<table class="teacher-table">
					<tr>
						<td><img src="../image/iconmonstr-user-6.svg"></td>
						<td><input type="text" name="id" placeholder="UserName" class="boxsize"></td>
					</tr>
					<tr>
						<td><img src="../image/iconmonstr-password-10.svg"></td>
						<td><input type="password" name="password" placeholder="Password" class="boxsize"></td>
					</tr>
				</table>
				<input id="backcolor1" type="submit" value="LOGIN" class="loginBtn"> 
				<input type="hidden" name="noMulti" value="noMulti">
			</form>
			
			<input id="backcolor2" type="button" value="선생님 회원가입" class="loginBtn" onclick='location.href="../teacher/insert.do"'><br>
			<input id="backcolor3" type="button" value="학생 회원가입" class="loginBtn" onclick='location.href="../student/insert.do"'>
	</div>
</body>
</html>