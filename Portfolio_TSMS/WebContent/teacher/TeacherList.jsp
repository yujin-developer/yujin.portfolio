<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선생님 회원 목록 페이지</title>
<jsp:include page="../main/Header.jsp" />
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
</style>

<script>
	function view(temp) {
		filename = "http://localhost:8088/Portfolio_TSMS/upload/" + temp;
		i = window.open(filename, "win", "height=350, width=450");
	} 

	function sendForm() {
		/* if(!document.form.search.value.trim()){
			alert('검색어를 입력하세요.');
			search.focus();
			return false; 
		} */
		document.form.submit();
	}

$(function () {
		//dialog 오픈	
		/* var target = $("div[id='dialog']");
		target.empty(); */
		
		$("#dialog").dialog({
			autoOpen : false, 
			//position:[100,200], //x,y  값
			//"center", "left", "right", "top", "bottom"
			//modal : true, 
			resizable: true, //크기 조절 
			width: 600, 
			height: 600,
			buttons : {
				"매칭시도" : function() {
					var result = confirm('매칭을 시도하시겠습니까?');
					if(result == true) {
						$.ajax({
							url : "../teacher/sendMail.do",
							data : "id=" + $("#id").val(),
							dataType:'text',
							type : "GET",
							success : function() {
									alert("매칭 이메일 보내기 성공!");
							}
						});
					} 
				},
				"취소" : function() {
					$(this).dialog("close");
				}
			}
		});
	
	//선생님 추천 클릭 후 정보 가져와서 dialog로 열기	
	$("#btn").on("click", function () {
		$.ajax({
			url : "../teacher/recommend.do",
			dataType:'text',
			type : "GET",
			success : function(response) {
				 //$(function() {
					// target.append(response);
					$("#dialog").html(response);
					$("#dialog").dialog("open");	
				 //});
			}
			/* error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			} */
		});
	});
	
	//선생님으로 로그인 돼있다면 추천 버튼 지우기
	if(${!empty sessionScope.teacher}) {
		$("#btn").hide();
	}
});	
</script>
</head>
<body>
<div class="wrap">
	<h1>선생님 리스트</h1><br>

	<div class="center">
		<button id="btn">선생님 추천받기</button>
	</div>
	<div id="content">
		<div id="dialog" title="선생님 추천"></div>
	</div>

	<form action="list.do" name="form">
		<table>
			<tr>
				<td><select name="searchType">
						<option value="1" ${searchType == "1" ? 'selected' : ''}>성별
						<option value="2" ${searchType == "2" ? 'selected' : ''}>학교
						<option value="3" ${searchType == "3" ? 'selected' : ''}>과목
						<option value="4" ${searchType == "4" ? 'selected' : ''}>지역
				</select></td>
				<td><input type="text" name="search" value="${search}">
					<input type="button" value="검색" onclick="sendForm()"></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>성별</th>
				<th>학교</th>
				<th>과목</th>
				<th>지역</th>
				<th>등록일자</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="teacher" items="${teachers}" begin="${page.startrow}"
				end="${page.endrow}" varStatus="status">
				<tr>
					<td>${page.totalrows-((page.where - 1) * page.maxrows + status.count -1)}</td>
					<td><a
						href="info.do?id=${teacher.id}&searchType=${searchType}&search=${search}">${teacher.name}</a></td>
					<td>${teacher.gender}</td>
					<td>${teacher.university}</td>
					<td>${teacher.subject == '무관' ? '수학 , 영어' : ''}
						${teacher.subject == '수학' ? '수학' : ''} ${teacher.subject == '영어' ? '영어' : ''}</td>
					<td>${teacher.address1}</td>
					<td><fmt:formatDate value="${teacher.createddate}"
							pattern="yy-MM-dd" /></td>
					<td>${teacher.readcount}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../forInclude/pageNumber.jsp" />
	</form>
</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>