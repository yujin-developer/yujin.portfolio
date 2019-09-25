<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="./forInclude/jquery.innerfade.js"></script>
<title>메인 페이지</title>
<style>
</style>
</head>
<body>
	
	<script>
		$(document).ready(
				function() {
					function moveSlider(index) {
						var willMoveLeft = -(index * 600);
						$('.slider_panel').animate({
							left : willMoveLeft
						}, 'slow');

						$('.control_button[data-index=' + index + ']')
								.addClass('active');
						$('.control_button[data-index!=' + index + ']')
								.removeClass('active');

						$('.slider_text[data-index=' + index + ']').show()
								.animate({
									left : 650
								}, 'slow');
						$('.slider_text[data-index!=' + index + ']').hide(
								'slow', function() {
									$(this).css('left', -700);
								});
					}

					$('.slider_text').css('left', -700).each(function(index) {
						$(this).attr('data-index', index);
					});

					$('.control_button').each(function(index) {
						$(this).attr('data-index', index);
					}).click(function() {
						var index = $(this).attr('data-index');
						moveSlider(index);
					});

					var randomNumber = Math.round(Math.random() * 2);

					setInterval(function() {
						moveSlider(randomNumber);
						randomNumber += 1;
						if (randomNumber > 2) {
							randomNumber = 0;
						}
					}, 3000);

				});
	</script>
</head>
<body>
	<div class="animation_canvas">
		<div class="slider_panel">
			<!-- <img class="slider_image" src=""> <img class="slider_image"
				src=""> <img class="slider_image" src=""> -->
		</div>
		<div class="slider_text_panel">
			<div class="slider_text">
				<h2>선생님 등록 현황 : ${teachersSize}</h2>
				<br>
				<table>
					<tr>
						<th>이름</th>
						<th>성별</th>
						<th>학교</th>
						<th>과목</th>
						<th>지역</th>
						<th>등록일자</th>
					</tr>
					<c:forEach var="teacher" items="${teachers}" begin="0" end="9">
						<tr>
							<td><a href="./teacher/info.do?id=${teacher.id}">${teacher.name}</a></td>
							<td>${teacher.gender}</td>
							<td>${teacher.university}</td>
							<td>${teacher.subject == '무관' ? '수학 , 영어' : ''}
								${teacher.subject == '수학' ? '수학' : ''} ${teacher.subject == '영어' ? '영어' : ''}</td>
							<td>${teacher.address1}</td>
							<td><fmt:formatDate value="${teacher.createddate}"
									pattern="yy-MM-dd" /></td>
						</tr>
					</c:forEach>
					<tr><td colspan="6" style="text-align:center;">.<br>.<br>.</td></tr>
				</table>
			</div>
			<div class="slider_text">
				<h2>학생 등록 현황 : ${studentsSize}</h2>
				<table>
					<tr>
						<th>이름</th>
						<th>성별</th>
						<th>학년</th>
						<th>과목</th>
						<th>지역</th>
						<th>등록일자</th>
					</tr>
					<c:forEach var="student" items="${students}" begin="0" end="9">
						<tr>
							<td><a
								href="./student/info.do?student_id=${student.student_id}">${student.name}</a></td>
							<td>${student.gender}</td>
							<td>${student.grade}</td>
							<td>${teacher.subject == '무관' ? '수학 , 영어' : ''}
								${teacher.subject == '수학' ? '수학' : ''} ${teacher.subject == '영어' ? '영어' : ''}</td>
							<td>${student.address}</td>
							<td><fmt:formatDate value="${student.createddate}"
									pattern="yy-MM-dd" /></td>
						</tr>
					</c:forEach>
					<tr><td colspan="6" style="text-align:center;">.<br>.<br>.</td></tr>
				</table>
			</div>
			<div class="slider_text">
				<h2>질문 게시판 등록 현황 : ${englishsSize}</h2>
				<table>
					<tr>
						<th>이름</th>
						<th>제목</th>
						<th>등록일자</th>
					</tr>
					<c:forEach var="english" items="${englishs}" begin="0" end="9">
						<tr>
							<td><a href="./english/update.do?bno=${english.bno}">${english.name}</a></td>
							<td>${english.subject}</td>
							<td><fmt:formatDate value="${english.createddate}"
									pattern="yy-MM-dd" /></td>
						</tr>
					</c:forEach>
					<tr><td colspan="3" style="text-align:center;">.<br>.<br>.</td></tr>
				</table>
			</div>
		</div>
		<div class="control_panel">
			<div class="control_button"></div>
			<div class="control_button"></div>
			<div class="control_button"></div>
		</div>
	</div>

	<jsp:include page="Footer.jsp" />
</body>
</html>