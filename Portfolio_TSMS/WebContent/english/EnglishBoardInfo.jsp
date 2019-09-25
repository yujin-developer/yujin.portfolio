<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English Board Info</title>
<script>
function view(temp) {
	filename = "http://localhost:8088/Portfolio_TSMS/upload/" + temp;
	i = window.open(filename, "win", "height=350, width=450");
}

function addReply(f) {
	var content = f.content.value;
	var bno = "${english.bno}";
	
	$.ajax({
		url : "../english/reply/insert.do",
		data: "content="+content+"&bno="+bno,
		type : "GET",
		success : function(response) {
			$("#addReplyer").val("${sessionScope.student.id}");
			$("#addContent").val(content);
			$("#addReplydate").val(response);
			$("#content").val("");
			
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}

function updateWrite() {
	$("#subject, #content").attr("readonly", false);
	$("#reply-list, #reply-form").hide();
	$("#update-form").submit();
}

function deleteWrite() {
	
}
</script>
</head>
<body>
	<jsp:include page="../main/Header.jsp" />
	<h1>English Board Info</h1>
	<form id="update-form" action="update.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>이름</th>
				<td><input type="text" id="name" name="name" value="${english.name}" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" id="subject" name="subject" value="${english.subject}" readonly></td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td><fmt:formatDate value="${english.createddate}"
							pattern="yy-MM-dd" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><c:if test="${!empty english.photo}">
						<c:set var="url"
							value="http://localhost:8088/Portfolio_TSMS/upload/" />
						<a onClick="view('${english.photo}');"><img width=100
							height=100 src="${pageScope.url}${english.photo}"><br></a>
					</c:if><input type="text" id="content" name="content" value="${english.content}" readonly></td>
			</tr>
		</table>
		<input type="button" value="수정" onclick="updateWrite()">
		<input type="button" value="삭제" onclick="deleteWrite()">
	</form>
	
	
	
	<div id="reply-list">
		<c:forEach var="r" items="${replys}">
			<table>
				<tr><td>${r.replyer}</td></tr>
				<tr><td>${r.content}</td></tr>
				<tr><td><fmt:formatDate value="${r.replydate}" pattern="yy-MM-dd HH:mm" /></td></tr>
			</table>
		</c:forEach>
		<table>
			<tr><td><input type="text" id="addReplyer"></td></tr>
			<tr><td><input type="text" id="addContent"></td></tr>
			<tr><td><input type="text" id="addReplydate"></td></tr>
		</table>
	</div>
	
	<form id="reply-form">
		<table>
			<tr>
				<th><textarea id="content" name="content"></textarea><img src=""></th>
				<th>
					<input type="text" value="등록" onclick="addReply(this.form)">
				</th>
			</tr>
		</table>
	</form>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>