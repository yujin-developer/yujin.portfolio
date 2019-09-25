<!-- <!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>toggle demo</title>
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
 
<button>Toggle</button>

<img id="img1" src="../image/iconmonstr-star-3.svg">
<img id="img2" src="../image/iconmonstr-star-5.svg" style="display: none">
 
<script>
$( "button" ).click(function() {
  $( "img" ).toggle();
  var img1 = $("#img1").css('display');
  var img2 = $("#img2").css('display');
  
  console.log("img1>>>>>>>>"+img1); //none
  console.log("img2>>>>>>>>>>"+img2); //inline
});


</script>
</body>
</html> -->

<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Dialog - Modal form</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <style>
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    var dialog, form,
 
      receiver = $( "#receiver" ),
      content = $( "#content" ),
      tips = $( ".validateTips" );
 
   /*  function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    } */
 
   /*  function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    } */
 
    /* function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        updateTips( n );
        return false;
      } else {
        return true;
      }
    } */
 
     function addUser() {
      var valid = true;
 
      valid = valid && checkLength( name, "username", 3, 16 );
      valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( password, "password", 5, 16 );
 
      valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
      valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
 
      if ( valid ) {
        $( "#users tbody" ).append( "<tr>" +
          "<td>" + name.val() + "</td>" +
          "<td>" + email.val() + "</td>" +
          "<td>" + password.val() + "</td>" +
        "</tr>" );
        dialog.dialog( "close" );
      }
      return valid;
    } 
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
      buttons: {
        "send": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form.reset();
      }
    });
 
   /*  form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    }); */
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
  } );
  </script>
</head>
<body>
 
<div id="dialog-form" title="Send Message">
  <form>
    <fieldset>
      Receiver
      <input type="text" name="receiver" id="receiver" value="">
      Content
      <input type="text" name="content" id="content" value="">
 
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>
 
<button id="create-user">Send</button>
 
</body>
</html>