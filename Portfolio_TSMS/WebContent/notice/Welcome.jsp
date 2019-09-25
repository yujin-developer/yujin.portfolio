<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 환영 페이지</title>
<style>
h1, input {
	text-align: center;
}
</style>
</head>
<body>
	<h1>환영합니다. Teacher Student Matching Site의 회원가입이 정상적으로 완료되었습니다.
		가입하신 아이디는 "${showId}" 입니다.</h1>

	<input type="button" onclick='location.href="../auth/login.do"'
		value="로그인">
	<input type="button" onclick='location.href="../main/Main.jsp"'
		value="메인페이지 바로가기">
</body>
</html>