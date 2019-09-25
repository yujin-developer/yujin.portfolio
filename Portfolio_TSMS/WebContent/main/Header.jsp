<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="tsms.vo.TeacherVO"%>
<%@ page import="tsms.vo.StudentVO"%> --%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header-style.css">
<style>
</style>
<script>
$(document).ready(function (){
	if(${!empty sessionScope.student or !empty sessionScope.teacher}) {
		$("#beforelogin").hide();
	}
	
	$("ul li a").click(function (e) {
		if(${empty sessionScope.student and empty sessionScope.teacher}) {
			e.preventDefault(); //a 태그에 걸린 이벤트 삭제
			alert("로그인 후 이용가능합니다.")
			location.href="<%=request.getContextPath()%>/auth/login.do";
		}
	});
});
</script>
<div class="wrap">
<Header>
	<div id="logo">
		<a id="title1" href="<%=request.getContextPath()%>/main.do">TSMS<br></a>
		<a id="title2" href="<%=request.getContextPath()%>/main.do">Teacher Student Matching Site</a>
	</div>
	
	<div id="beforelogin">
		<c:if test="${empty sessionScope.teacher or empty sessionScope.teacher.teacher_id or empty sessionScope.student or empty sessionScope.student.student_id}">
			<a href="<%=request.getContextPath()%>/auth/login.do">LOGIN</a>
		</c:if>
	</div>
	
	<div id="afterlogin">
		<c:if test="${!empty sessionScope.teacher or !empty sessionScope.teacher.teacher_id or !empty sessionScope.student or !empty sessionScope.student.student_id}">
			<c:if test="${!empty sessionScope.teacher.id}">
				${sessionScope.teacher.id}님 
				<a href="<%=request.getContextPath()%>/teacher/update.do">Profile</a>
			</c:if>
			<c:if test="${!empty sessionScope.student.id}">
				${sessionScope.student.id}님
				<a href="<%=request.getContextPath()%>/student/update.do">Profile</a>
			</c:if>
			<a href="<%=request.getContextPath()%>/mailbox/receive/list.do">Mailbox</a>
			<a href="<%=request.getContextPath()%>/auth/logout.do">LogOut</a>
		</c:if>
	</div>
</Header>
<div class="clear"></div>
<nav>
	<ul>
		<li><a href="<%=request.getContextPath()%>/teacher/list.do">FIND
				TEACHER</a></li>
		<li><a href="<%=request.getContextPath()%>/student/list.do">FIND
				STUDENT</a></li>
		<li><a href="<%=request.getContextPath()%>/english/list.do">BOARD</a></li>
		<li><a id="a4" href="">ABOUT</a></li>
	</ul>
</nav>
<div class="clear"></div>
</div>
<div class="line"></div>

