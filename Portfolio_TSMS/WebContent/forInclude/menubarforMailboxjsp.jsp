<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.sideMenu {
	/* height: 100%; */
	width: 160px;
	position: fixed;
	left: 0;
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

/* .wrap {
	margin-left: 160px;
	font-size: 20px;
	padding: 0px 10px;
} */
</style>
<div class="sideMenu">
	<a href="../receive/list.do">받은쪽지함</a> <a
		href="../receive/list.do?sendList=yes">보낸쪽지함</a> <a
		href="../receive/list.do?starList=yes">별표쪽지함</a> <a
		href="../trash/list.do">휴지통</a>
</div>