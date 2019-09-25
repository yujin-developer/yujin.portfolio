//중복 아이디 체크 및 영문/숫자 조합 체크
function checkId(param) {
	var target = $("div[id='checkId']");
	target.empty();

	$.ajax({
		url : "../teacher/insert.do",
		data : "checkId=" + encodeURI(param, "UTF-8"),
		type : "GET",
		success : function(response) {
			var idType = /^[a-z0-9+]{4,15}$/;
			if (!idType.test(param)) {
				target.append('숫자/영문자(소문자) 조합으로 4~15자리 아이디를 생성해야 합니다.');
				return false;
			}

			var chk_num = param.search(/[0-9]/g);
			var chk_eng = param.search(/[a-z]/ig);
			if (chk_num < 0 || chk_eng < 0) {
				target.append('숫자/영문자(소문자) 조합 이어야 합니다.');
				return false;
			}

			if (response == "") {
				/*target.append('사용가능한 아이디 입니다.');*/
			} else if (response != "") {
				target.append('이미 사용중인 아이디 입니다.');
				return false;
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}

// 비밀번호 재확인 일치 체크 및 영문/숫자 조합 체크
function checkPwd1(param) {
	var target = $("div[id='checkPwd1']");
	target.empty();
	var idType = /^[a-z0-9+]{4,15}$/;

	if (!idType.test(param)) {
		target.append('숫자/영문자(소문자) 조합으로 4~15자리 비밀번호를 생성해야 합니다.');
		return false;
	}

	var chk_num = param.search(/[0-9]/g);
	var chk_eng = param.search(/[a-z]/ig);
	if (chk_num < 0 || chk_eng < 0) {
		target.append('숫자/영문자(소문자) 조합 이어야 합니다.');
		return false;
	}
	
}
function checkPwd2(param) {
	var target = $("div[id='checkPwd2']");
	var password1 = $("#password1").val();
	target.empty();
	var idType = /^[a-z0-9+]{4,15}$/;

	if (param != password1) {
		target.append('비밀번호가 일치하지 않습니다.');
		return false;
	} else {
		/*target.append('비밀번호가 일치합니다.');*/
	}
}

// 이메일 입력방식 선택해서 hidden으로 넘기기
function selectEmail(param) {
	var email1 = $('#email1').val();
	var email = email1.concat("@", param);
	$('#email2').val(param);

	if (param == '1') {
		email = email1.concat("@", $('#email2').val())
	}
	$('#hiddenEmail').val(email);
}

// 시도, 시군구 바뀌게 하는 코드
function changeSido1(param) {
	var target = $("select[id='si_gun_gu1']");
	target.empty();

	if (param == "") {
		target.append('<option value="">시/군/구</option>');
	}

	$.ajax({
		url : "../teacher/insert.do",
		data : "sido=" + encodeURI(param, "UTF-8"),
		type : "GET",
		// traditional: true,
		// dataType: "JSON",
		success : function(response) {
			/*
			 * var si_gun_gu = "${si_gun_gu}"; if(si_gun_gu.length == 0) {
			 * target.append('<option value="">시/군/구</option>'); }else {
			 * target.append("<c:forEach var='addr' items='${si_gun_gu}'><option
			 * value='${addr.si_gun_gu}'>${addr.si_gun_gu}</option></c:forEach>"); }
			 */

			// $.each(response, function(index, value) {
			// target.append('<option
			// value='+value.si_gun_gu+'>'+value.si_gun_gu+'</option>');
			// console.log(value.si_gun_gu);
			// });
			/*
			 * var str = ""; for(var i=0, len = response.length || 0; i<len;
			 * i++) { str += '<option>'+response[i].si_gun_gu+'</option>'; }
			 * target.append(str);
			 */
			target.append('<option value="">시/군/구</option>');
			var si_gun_gu = response.split(",");
			for (var i = 0; i < si_gun_gu.length; i++) {
				if (si_gun_gu[i] != "") {
					target.append('<option value=' + si_gun_gu[i] + '>'
							+ si_gun_gu[i] + '</option>');
				}
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}
function changeSido2(param) {
	var target = $("select[id='si_gun_gu2']");
	target.empty();

	if (param == "") {
		target.append('<option value="">시/군/구</option>');
	}

	$.ajax({
		url : "../teacher/insert.do",
		data : "sido=" + encodeURI(param, "UTF-8"),
		type : "GET",
		success : function(response) {
			target.append('<option value="">시/군/구</option>');
			var si_gun_gu = response.split(",");
			for (var i = 0; i < si_gun_gu.length; i++) {
				if (si_gun_gu[i] != "") {
					target.append('<option value=' + si_gun_gu[i] + '>'
							+ si_gun_gu[i] + '</option>');
				}
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}

// 선생님 프로필 읽을때 selected 지정
function changeSido1(param, param2) {
	var target = $("select[id='si_gun_gu1']");
	target.empty();

	$.ajax({
		url : "../teacher/insert.do",
		data : "sido=" + encodeURI(param, "UTF-8"),
		type : "GET",
		success : function(response) {
			target.append('<option value="">시/군/구</option>');
			var si_gun_gu = response.split(",");
			for (var i = 0; i < si_gun_gu.length; i++) {
				if (si_gun_gu[i] != "") {
					if (param2 == si_gun_gu[i]) {
						target.append('<option value=' + si_gun_gu[i]
								+ ' selected>' + si_gun_gu[i] + '</option');
					} else {
						target.append('<option value=' + si_gun_gu[i] + '>'
								+ si_gun_gu[i] + '</option');
					}
				}
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}
function changeSido2(param, param2) {
	var target = $("select[id='si_gun_gu2']");
	target.empty();

	$.ajax({
		url : "../teacher/insert.do",
		data : "sido=" + encodeURI(param, "UTF-8"),
		type : "GET",
		success : function(response) {
			target.append('<option value="">시/군/구</option>');
			var si_gun_gu = response.split(",");
			for (var i = 0; i < si_gun_gu.length; i++) {
				if (si_gun_gu[i] != "") {
					if (param2 == si_gun_gu[i]) {
						target.append('<option value=' + si_gun_gu[i]
								+ ' selected>' + si_gun_gu[i] + '</option');
					} else {
						target.append('<option value=' + si_gun_gu[i] + '>'
								+ si_gun_gu[i] + '</option');
					}
				}
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	});
}

// 주소값 합쳐서 hidden으로 넘기기
function realAddr1(param) {
	var si_do1 = $('#si_do1').val();
	var address1 = si_do1.concat(" ", param);
	$('#hiddenAddress1').val(address1);
}
function realAddr2(param) {
	var si_do2 = $('#si_do2').val();
	var address2 = si_do2.concat(" ", param);
	$('#hiddenAddress2').val(address2);
}

$(document).ready(function() {
	// 체크박스(학년) 전체선택 및 전체해제
	$("#allGrade").click(function() {
		if ($("#allGrade").prop("checked")) {
			$("input[type='checkbox'][name='grade']").prop("checked", true);
			$(".setCheckedGrade").prop("disabled", true);
		} else {
			$("input[type='checkbox'][name='grade']").prop("checked", false);
			$(".setCheckedGrade").prop("disabled", false);
		}
	});

	// 체크박스(요일) 전체선택 및 전체해제
	$("#allDay").click(function() {
		if ($("#allDay").prop("checked")) {
			$("input[type='checkbox'][name='day']").prop("checked", true);
			$(".setCheckedDay").prop("disabled", true);
		} else {
			$("input[type='checkbox'][name='day']").prop("checked", false);
			$(".setCheckedDay").prop("disabled", false);
		}
	});
	
	$("select").attr("style", "height:40px");
});

// 공백 체크
function trim(str) {
	return str.replace(/^\s*|\s*$/g, "");
}

function check() {
	with (document.form) {
		if (!trim(id.value)) {
			alert("아이디를 입력해 주세요.");
			id.focus();
			return false;
		}
		if (!trim(password.value)) {
			alert("비밀번호를 입력해 주세요.");
			password.focus();
			return false;
		}
		if (!trim(name.value)) {
			alert("이름을 입력해 주세요.");
			name.focus();
			return false;
		}
		var ageType = /[^0-9]/g;
		if (!trim(age.value) || ageType.test(age.value)) {
			alert("나이(숫자만)를 입력해 주세요.");
			age.focus();
			return false;
		}
		if (!trim(email.value)) {
			alert("이메일을 입력해 주세요.");
			email.focus();
			return false;
		}
		if (!trim(address1.value)) {
			alert("희망지역을 선택해 주세요.");
			address1.focus();
			return false;
		}

		var cntGrade = 0;
		for (var i = 0; i < grade.length; i++) {
			if (grade[i].checked == true) {
				cntGrade++;
			}
		}
		if (cntGrade == 0) {
			alert("과외가 가능한 학생을 선택해 주세요.");
			return false;
		}

		var cntDay = 0;
		for (var i = 0; i < day.length; i++) {
			if (day[i].checked == true) {
				cntDay++;
			}
		}
		if (cntDay == 0) {
			alert("과외가 가능한 요일을 선택해 주세요.");
			return false;
		}
	}

	gradeDB();
	dayDB();
	timeDB();
	document.form.submit();
}

// 체크된 학년 합쳐서 DB에 넘기기
function gradeDB() {
	var grade = "";
	$('input:checkbox[name=grade]').each(function() {
		if ($("#all").prop("checked")) {
			grade = $("#all").val();
		} else if ($(this).is(':checked') && !($("#all").prop("checked"))) {
			grade += "," + $(this).val();
		}
	});
	$("input[name=grade]").val(grade);
}

// 체크된 요일 합쳐서 DB에 넣기
function dayDB() {
	var day = "";
	$('input:checkbox[name=day]').each(function() {
		if ($("#allDay").prop("checked")) {
			day = $("#allDay").val();
		} else if ($(this).is(':checked') && !($("#allDay").prop("checked"))) {
			day += "," + $(this).val();
		}
	});
	$("input[name=day]").val(day);
}

// 과외 시간 합쳐서 DB에 넣기
function timeDB() {
	var time = "";
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	time = startTime.concat("~", endTime);
	$("#hiddenTime").val(time);
}