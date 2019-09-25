<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>jQuery UI Dialog - Animation</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <!--  <script>
  $( function() {
    $( "#dialog" ).dialog({
      autoOpen: false
      
    });
 
    $( "#opener" ).on( "click", function() {
      $( "#dialog" ).dialog( "open" );
    });
  } );
  </script>
</head>
<body>
 
<div id="dialog" title="Basic dialog">
  <p>This is an animated dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
	<table>
		<tr>
			<td>이름</td>
			<td>전유진</td>
		</tr>
	</table>

</div>
 
<button id="opener">Open Dialog</button>
 
  -->
 
 
 
 
 
 
 
 
 
 
 <!-- <style>
#dialog-background {
    display: none;
    position: fixed;
    top: 0; left: 0;
    width: 100%; height: 100%;
    background: rgba(0,0,0,.3);
    z-index: 10;
}
#my-dialog {
    display: none;
    position: fixed;
    left: calc( 50% - 160px ); top: calc( 50% - 70px );
    width: 320px; height: 140px; 
    background: #fff;
    z-index: 11;
    padding: 10px;
}
</style>
<script>
$(function(){
	$("#btn-open-dialog,#dialog-background,#btn-close-dialog").click(function () {
		$("#my-dialog,#dialog-background").toggle();
	});
});
</script>


 </head>
 <body>
 간단한 모달창 <button id="btn-open-dialog">창 열기</button>

<div id="my-dialog">
    창 내용
</div>
<div id="dialog-background"></div>
 </body> -->
 
 
 
 
 
<!--  
<script>
$(function() {
  $('#dialog').dialog({
    autoOpen: false,
    resizable: false,
  });
  $('#button_open_dialog').click( function(){
    $('#dialog').dialog('open');
  });
});
</script>
 <body>
<button id='button_open_dialog'>대화상자 열기</button>
 
<div id="dialog" title="기본 대화상자">
  <p>이것은 기본 대화상자 입니다. 이동하거나 닫을 수 있습니다.</p>
</div>
 
 
  -->
 
 
 
 
 <!-- <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>jQuery UI Example Page</title>
        <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.11.custom.css"      rel="stylesheet" />  
        <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.11.custom.min.js">     </script>
        <script type="text/javascript">
        $(document).ready(function () {
            $(function() {
            $("#dialog-form").dialog({
                autoOpen: false,
                    maxWidth:600,
                    maxHeight: 500,
                    width: 600,
                    height: 500,
                    modal: true,
                    buttons: {
                    "Create": function() {
                    $(this).dialog("close");
                    },
                    Cancel: function() {
                    $(this).dialog("close");
                    }
                },
                    close: function() {
                }
                });
            });

            $("#create-appt")
            .button()
            .click(function() {
                $("#dialog-form").dialog("open");
            });
        });
        </script>

    </head>
        <body>
    <h1>test</h1>
    <div id="dialog-form" title="Create Appointment">   
        <p> this is my test </p>
    </div>
    <input type="button" id="create-appt" value="test"/>
    </body>
 </html>
  -->
 
 
 
 
 
 
 
 
 
</body>
</html>