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
<title>휴지통</title>
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

	$(document).ready(function() {
		//휴지통 체크박스 전체선택 및 해제
		var del = $("input[name='check']");
		$("#all").click(function() {
			if ($("#all").prop("checked")) {
				del.prop("checked", true);
			} else {
				del.prop("checked", false);
			}
		});

		//전체 선택 클릭후 한개 선택 해제 했을때 전체 선택 버튼 false로 바꾸기
		$("#check").click(function() {
			for (var i = 0; i < del.length; i++) {
				if (del[i].checked == false && $("#all").prop("checked")) {
					$("#all").prop("checked", false);
				}
			}
		});

		//sender 클릭후 상세보기 dialog
		$("#dialog-info").dialog({
			autoOpen : false,
			resizable : true, //크기 조절 
			width : 600,
			height : 600,
		});

		//내용 클릭후 상세보기 dialog
		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 350,
			width : 350,
			modal : true,
			close : function() {
				$("#dialog-Form")[0].reset();
			}
		});
	});

	//완전삭제
	function trash() {
		if (!$("#check").prop("checked")) {
			alert("삭제할 쪽지를 선택하세요.");
			return false;
		}

		var cf = confirm("휴지통의 쪽지를 지우면 지워진 쪽지들을 복구할 수 없습니다. 쪽지를 삭제 하시겠습니까?");
		if (cf == true) {
			var str = "";
			$("input:checkbox:checked").each(function() {
				str += $(this).val() + ",";
			});

			$.ajax({
				url : "../mailbox/trashDelete.do",
				data : "idList=" + str,
				type : "GET",
				success : function() {
					var ids = str.split(",");
					for (i = 0; i < ids.length; i++) {
						var s = ids[i];
						$("input[value=" + s + "]").closest("tr").remove();
					}
				},
				error : function(xhr, status, er) {
					if (error) {
						error(er);
					}
				}
			});
		} else {
			return false;
		}
	}

	//휴지통에서 쪽지함으로 복구
	function move() {
		if (!$("#check").prop("checked")) {
			alert("복구할 쪽지를 선택하세요.");
			return false;
		}

		var str = "";
		$("input:checkbox:checked").each(function() {
			str += $(this).val() + ",";
		});

		$.ajax({
			url : "../trash/move.do",
			data : "idList=" + str,
			type : "GET",
			success : function() {
				var ids = str.split(",");
				for (i = 0; i < ids.length; i++) {
					var s = ids[i];
					$("[value=" + s + "]").closest("tr").remove();
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
			url : "../../teacher/info.do?id=" + id + "&readDialog=readDialog",
			dataType : 'text',
			type : "GET",
			success : function(response) {
				$("#dialog-info").html(response);
				$("#dialog-info").dialog("open");
			}
		});
	}

	//내용 클릭하면 다이얼로그 오픈
	function sendForm(f) {
		var sender = f.msender.value;
		var receiver = f.mreceiver.value;
		var content = f.mcontent.value;

		$("#sender").val(sender);
		$("#receiver").val(receiver);
		$("#receive-send-content").val(content);

		dialog.dialog("open");
	}
</script>
</head>
<body>
	<!-- 다이얼로그 열어서 내용 보여주기 -->
	<div id="dialog-form" title="Send Message">
		<form id="dialog-Form">
			From <input type="text" name="sender" id="sender" readonly><br>
			To <input type="text" name="receiver" id="receiver" readonly><br>
			쪽지 내용<br>
			<textarea style="width: 100%; height: 35%;" id="receive-send-content"
				name="receive-send-content" readonly></textarea>
			**쪽지함으로 이동시키면 쪽지보내기를 정상적으로 이용할 수 있습니다.
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
		
		<table>
			<tr>
				<th><input type="checkbox" id="all" value="all"></th>
				<th>From</th>
				<th>To</th>
				<th>내용</th>
				<th>수신날짜</th>
			</tr>
			<c:forEach var="m" items="${trashmails}" begin="${page.startrow}"
				end="${page.endrow}">
				<tr>
					<td><input type="checkbox" name="check" id="check"
						value="${m.id}"></td>

					<c:if test="${sessionScope.student.id eq m.sender}">
						<td>${m.sender}</td>
					</c:if>
					<c:if test="${sessionScope.student.id ne m.sender}">
						<td onclick="showInfo('${m.sender}');" style="cursor: pointer;">${m.sender}</td>
					</c:if>
					<c:if test="${sessionScope.student.id eq m.receiver}">
						<td>${m.receiver}</td>
					</c:if>
					<c:if test="${sessionScope.student.id ne m.receiver}">
						<td onclick="showInfo('${m.receiver}');" style="cursor: pointer;">${m.receiver}</td>
					</c:if>

					<td>
						<form id="send-form">
							<input name="mcontent" value="${m.content}" class="size"
								style="cursor: pointer;" onclick="sendForm(this.form)">
							<input type="hidden" name="msender" value="${m.sender}">
							<input type="hidden" name="mreceiver" value="${m.receiver}">
						</form>
					</td>
					<td><fmt:formatDate value="${m.sendtime}"
							pattern="yy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td><img
					src="../../image/iconmonstr-trash-can-2.svg" onclick="trash()"
					style="cursor: pointer;">      
					<img src="../../image/iconmonstr-paper-plane-1.svg" onclick="move()"
					style="cursor: pointer;"></td>
			</tr>
		</table>

		<jsp:include page="../forInclude/pageNumber.jsp" />
	</div>
	<jsp:include page="../main/Footer.jsp" />
</body>
</html>