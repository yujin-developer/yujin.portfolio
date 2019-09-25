<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../main/Header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>english board Form</title>
<script>
function trim(str) {
	return str.replace(/^\s*|\s*$/g, "");
}

function check() {
	with (document.form) {
		if (!trim(subject.value)) {
			alert("제목을 입력해 주세요!!!!");
			subject.focus();
			return false;
		}
		if (!trim(content.value)) {
			alert("내용을 입력해 주세요!!!!");
			content.focus();
			return false;
		}
	}
	var cf = confirm("새 글을 등록 하시겠습니까?");
	if(cf == true) {
		document.form.submit();
	}else {
		return false;
	}
}
</script>
</head>
<body>
	<h1>English Board Form</h1>
	<div align="center">
		<form name="form" method="post" action="insert.do"
			enctype="multipart/form-data">
			<table width="600" border="1">
				<tr>
					<th colspan="2" height="40">글 쓰기</th>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td><input type="text" name="name"
						value="${sessionScope.student.name}" readonly></td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td><input type="text" name="subject"></td>
				</tr>
				<tr>
					<td align="center">내용</td>
					<td><textarea rows="10" cols="65" name="content"
							maxlength="1000"></textarea></td>
				</tr>
				<tr>
					<th>사진등록(선택)</th>
					<td><input type="file" name="photo"></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><a onClick="check();">글등록하기</a></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>