<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/basic-jquery-slider.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="./scripts/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="./scripts/basic-jquery-slider.js"></script>


<title>메인 페이지</title>
<style>
* {
    margin: 0;
    padding: 0
}

#wrap {
    width: 100%;
    margin: 0 auto;
    text-align: left;
}

</style>
</head>
<body>
	
	<script>
		$(document).ready(function() {
			$('#slide').bjqs({
				'width' : '100%',
				'height' : 510,
				'showMarkers' : true,
				'showControls' : false,
				'centerMarkers' : false
			});
		});

		/* Dropdown script */
		var timeout = 500;
		var closetimer = 0;
		var ddmenuitem = 0;

		function dropdown_open() {
			dropdown_canceltimer();
			dropdown_close();
			ddmenuitem = $(this).find('ul').css('visibility', 'visible');
		}

		function dropdown_close() {
			if (ddmenuitem)
				ddmenuitem.css('visibility', 'hidden');
		}

		function dropdown_timer() {
			closetimer = window.setTimeout(dropdown_close, timeout);
		}

		function dropdown_canceltimer() {
			if (closetimer) {
				window.clearTimeout(closetimer);
				closetimer = null;
			}
		}

		$(document).ready(function() {
			$('#dropdown > li').bind('mouseover', dropdown_open)
			$('#dropdown > li').bind('mouseout', dropdown_timer)
		});

		document.onclick = dropdown_close;
	</script>
</head>
<body>
	<div id="wrap">
        <article id="front">
            <div id="slide">
                <ul class="bjqs">
                    <li><img src="../image/" width="100%" height="496"></li>
                    <li><img src="../image/" width="100%" height="496"></li>
                    <li><img src="../image/" width="100%" height="496"></li>
                </ul>
            </div>
            
            <div class="slider_text_panel">
				<div class="slider_text">
                	<h1>AAAAAA</h1>
                	<p>AAAAAAAAAAAAAAAAAAAAAA</p>
            	</div>
            </div>
        </article>
    </div>
	<jsp:include page="Footer.jsp" />
</body>
</html>