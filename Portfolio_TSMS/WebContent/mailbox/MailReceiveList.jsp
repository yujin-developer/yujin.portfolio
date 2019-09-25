<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../main/Header.jsp" />
<%-- <jsp:include page="../forInclude/menubarforMailboxjsp.jsp" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은쪽지함 리스트</title>

<style>
* {
	margin: 0;
	padding: 0;
}

.wrap {
	width: 1200px;
	margin: 0 auto;
	text-align: left;
}

h1, td {
	text-align: center;
}

table {
	margin: 0 auto;
	font-size: 20px;
	width: 800px;
}

.size {
	overflow: hidden;
	text-overflow: ellipsis; /* (...) */
	white-space: nowrap;
	width: 150px;
	height: 20px;
}

.sideMenu {
	width: 160px;
	position: fixed;
	overflow-x: hidden;
	padding-top: 20px;
	padding-left: 20px;
	
}

.sideMenu a {
	padding: 6px 8px 6px 16px;
	text-decoration: none;
	font-size: 22px;
	display: block;
}
</style>

<script>
var dialog;
var toggle;
var sendList = "${sendList}";
var starList = "${starList}";

$(document).ready(function () {
	//휴지통 체크박스 전체선택 및 해제
	var del = $("input[name='delete']");
	$("#allDelete").click(function () {	
		if($("#allDelete").prop("checked")) {
			del.prop("checked", true);
		}else {
			del.prop("checked", false);
		}
	});
	
	//전체 선택 클릭후 한개 선택 해제 했을때 전체 선택 버튼 false로 바꾸기
	$("#delete").click(function () {
		for(var i=0; i<del.length; i++) {
			if(del[i].checked == false && $("#allDelete").prop("checked")) {
				$("#allDelete").prop("checked", false);
			}
		}
	});
	
	//내용 클릭후 상세보기 및 쪽지보내기 dialog
	if(sendList != "yes") { //받은쪽지함에서 내용 클릭
		dialog = $("#dialog-form").dialog({
			autoOpen: false,
		    height: 500,
		    width: 350,
		    modal: true,
	    	buttons: {
		    	"send": sendMail,
		        Cancel: function() {
		        	dialog.dialog("close");
		        }
		    },
		    close: function() {
		    	$("#dialogForm")[0].reset();
		    }
		 });	
	}else { //보낸쪽지함에서 내용 클릭
		dialog = $("#dialog-form").dialog({
			autoOpen: false,
		    height: 350,
		    width: 350,
		    modal: true,
		    close: function() {
		    	$("#dialogForm")[0].reset();
		    }
		 });
	}
		
	function sendMail() {
		if($("#content").val().length <= 0) {
			alert("쪽지 내용을 입력하세요.");
			return false;
		}else {
			$("#dialogForm").submit();
			alert("쪽지를 정상적으로 보냈습니다.")
		}
	}
	
	//sender 클릭후 상세보기 dialog
	$("#dialog-info").dialog({
		autoOpen : false, 
		resizable: true, //크기 조절 
		width: 600, 
		height: 600,
	});
	
	//별 toggle
	$(".toggleStar").click(function () {
		$(this).find('img').toggle();
	});
});

//내용 클릭하면 다이얼로그 오픈
function sendForm(f) {
	var sender = f.msender.value;
	var receiver = f.mreceiver.value;
	var content = f.mcontent.value;
	var sessionSender = "${sessionScope.student.id}";
	
	if(sendList != "yes") {
		$("#sender").val(sender);
		$("#receiveContent").val(content);
	}else {
		$("#receiver").val(receiver);
		$("#sendContent").val(content);
	}
	dialog.dialog("open");
	
	//내용 클릭후 Read로 DB업데이트
	if((f.mreadcheck.value == "unread") && (sender != sessionSender)) { //받은쪽지함에서 읽었을때만 업데이트
		var readcheck = "read";
		$.ajax({
			url : "../readUpdate.do",
			data: "readcheck="+readcheck+"&id="+f.mid.value,
			type : "GET",
			success : function() {
				var a = f.mid.value;
				$("#"+a+".changeImgRead").find(".img3").css('display','none');
				$("#"+a+".changeImgRead").find(".img4").css('display','inline');
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
} 

//별표 DB Update
function starUpdate(id) {
	var img1 = $("#"+id+".toggleStar .img1").css('display');
	var img2 = $("#"+id+".toggleStar .img2").css('display');
	var starcheck = "";
	if(img2 == "none") { 
		starcheck = "no";
	}else if(img1 == "none") { 
		starcheck = "yes";
	}

	//별 toggle 한후 DB에 업데이트
	$.ajax({
		url : "../starUpdate.do",
		data: "starcheck="+starcheck+"&id="+id,
		type : "GET",
		success : function() {
			if(starList == "yes"){
				$("#"+id+".toggleStar").closest("tr").remove();
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});  
}

//휴지통으로 보내기 
function trash () {
	var str = "";  
	$("input:checkbox:checked").each(function () {  
	    str += $(this).val() + ","; 
	});  
	
	$.ajax({
		url : "../trash.do",
		data : "idList=" + str,
		type : "GET",
		success : function() {
			var ids = str.split(",");
			for(i=0; i<ids.length; i++) {
				var s = ids[i];
				$("input[value="+s+"]").closest("tr").remove();
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	}); 
}

//ajax로 상세보기 dialog 띄우기
function showInfo(id) {
	$.ajax({
		url : "../teacher/info.do?id="+id+"&readDialog=readDialog",
		dataType:'text',
		type : "GET",
		success : function(response) {
			$("#dialog-info").html(response);
			$("#dialog-info").dialog("open");	
		}
	});
}
	
</script>
</head>
<body>
	<!-- 다이얼로그 열어서 내용 보여주고 쪽지 보내기 -->
	<div id="dialog-form" title="Send Message">
		<form id="dialogForm" name="dialogForm" action="insert.do"
			method="post">
			<c:if test="${sendList != 'yes'}">
				<input type="hidden" name="noMulti" value="noMulti"> From<input
					type="text" name="sender" id="sender" readonly>
				<br> 받은
			쪽지 내용 <br>
				<textarea style="width: 100%; height: 35%;" id="receiveContent"
					name="receiveContent" readonly></textarea>
				<br> 쪽지 쓰기 <br>
				<textarea style="width: 100%; height: 35%;" name="content"
					id="content"></textarea>
			</c:if>
			<c:if test="${sendList == 'yes'}">
				<input type="hidden" name="noMulti" value="noMulti"> To<input
					type="text" name="receiver" id="receiver" readonly>
				<br> 보낸 쪽지 내용 <br>
				<textarea style="width: 100%; height: 35%;" id="sendContent"
					name="sendContent" readonly></textarea>
			</c:if>
		</form>
	</div>

	<!-- From 클릭하면 상세보기 dialog -->
	<div id="dialog-info" title="보낸 사람 정보"></div>

	<div class="wrap">
		<div class="sideMenu">
			<a href="../receive/list.do">받은쪽지함</a> <a
				href="../receive/list.do?sendList=yes">보낸쪽지함</a> <a
				href="../receive/list.do?starList=yes">별표쪽지함</a> <a
				href="../trash/list.do">휴지통</a>
		</div>

		<input type="hidden" name="noMulti" value="noMulti">
		<table>
			<tr>
				<th><input type="checkbox" id="allDelete" value="allDelete"></th>
				<th>별표체크</th>
				<th>Read</th>
				<c:if test="${sendList != 'yes' or starList == 'yes'}">
					<th>From</th>
				</c:if>
				<c:if test="${sendList == 'yes' or starList == 'yes'}">
					<th>To</th>
				</c:if>
				<th>내용</th>
				<th>수신날짜</th>
			</tr>
			<c:if test="${page.endrow >= 0}">
				<c:forEach var="m" items="${mails}" begin="${page.startrow}"
					end="${page.endrow}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="delete" id="delete"
							value="${m.id}"></td>
						<td>
							<div class="toggleStar" onclick="starUpdate(${m.id});"
								id="${m.id}">
								<c:if test="${m.starcheck == 'yes'}">
									<img class="img1" src="../../image/iconmonstr-star-3.svg"
										style="cursor: pointer;">
									<img class="img2" src="../../image/iconmonstr-star-5.svg"
										style="cursor: pointer; display: none;">
								</c:if>
								<c:if test="${m.starcheck == 'no'}">
									<img class="img1" src="../../image/iconmonstr-star-3.svg"
										style="cursor: pointer; display: none;">
									<img class="img2" src="../../image/iconmonstr-star-5.svg"
										style="cursor: pointer;">
								</c:if>
							</div>
						</td>
						<td><div id="${m.id}" class="changeImgRead">
								<c:if test="${m.readcheck == 'read'}">
									<img class="img3" src="../../image/iconmonstr-email-2.svg"
										style="display: none;">
									<img class="img4" src="../../image/iconmonstr-email-8.svg">
								</c:if>
								<c:if test="${m.readcheck == 'unread'}">
									<img class="img3" src="../../image/iconmonstr-email-2.svg">
									<img class="img4" src="../../image/iconmonstr-email-8.svg"
										style="display: none;">
								</c:if>
							</div></td>

						<c:if test="${sendList != 'yes' or starList == 'yes'}">
							<c:if test="${sessionScope.student.id eq m.sender}">
								<td>${m.sender}</td>
							</c:if>
							<c:if test="${sessionScope.student.id ne m.sender}">
								<td onclick="showInfo('${m.sender}');" style="cursor: pointer;">${m.sender}</td>
							</c:if>
						</c:if>
						<c:if test="${sendList == 'yes' or starList == 'yes'}">
							<c:if test="${sessionScope.student.id eq m.receiver}">
								<td>${m.receiver}</td>
							</c:if>
							<c:if test="${sessionScope.student.id ne m.receiver}">
								<td onclick="showInfo('${m.receiver}');"
									style="cursor: pointer;">${m.receiver}</td>
							</c:if>
						</c:if>

						<td>
							<form id="send-form">
								<input name="mcontent" value="${m.content}" class="size"
									style="cursor: pointer;" onclick="sendForm(this.form)">
								<input type="hidden" name="msender" value="${m.sender}">
								<input type="hidden" name="mreadcheck" value="${m.readcheck}">
								<input type="hidden" name="mid" value="${m.id}"> <input
									type="hidden" name="mreceiver" value="${m.receiver}">
							</form>
						</td>
						<td><fmt:formatDate value="${m.sendtime}"
								pattern="yy-MM-dd HH:mm" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td><img
					src="../../image/iconmonstr-trash-can-2.svg" onclick="trash()"
					style="cursor: pointer;"></td>
			</tr>
		</table>

		<jsp:include page="../forInclude/pageNumber.jsp" />
	</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>