<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Sign Up</title>

    <!-- Bootstrap core CSS -->
    <link href="/FoundITClient/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/FoundITClient/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/FoundITClient/resources/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/FoundITClient/resources/js/ie-emulation-modes-warning.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

	<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
	<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
	
	<!-- Bootstrap Date-Picker Plugin -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<style type="text/css">
/**
 * Override feedback icon position
 * See http://formvalidation.io/examples/adjusting-feedback-icon-position/
 */
#eventForm .form-control-feedback {
    top: 0;
    right: -15px;
}
</style>
  <body onload = "init()">

    <div class="container">
      <form class="form-signin"  action = "register" name = "user">
        <h2 class="form-signin-heading">Sign Up</h2>
        
        <div class="form-group">
      		<label for="type" >Choose your role: </label>
     		<select class="form-control" id="type" name="type" required>
	 			<option disabled="disabled" value="" selected="selected">Please select an role</option>
				<option value="manager">Manager</option>
				<option value="candidate">Candidate</option>
				<option value="hiringTeam">Hiring Team</option>
			</select>
			</div>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email Address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"  required>
        <label for="inputPassword" class="sr-only">Confirm Password</label>
        <input type="password" name="comfirmPassword" id="comfirmPassword" onkeyup='check();' class="form-control" placeholder="Comfirm Password" required>
       	 <label id='message'></label>
        <label for="name" class="sr-only">Name</label>
        <input type="text" id="name"  name="name" class="form-control" placeholder="Name" required>
        <label for="sex" class="sr-only">title</label>
        <select class="form-control" id="title" name="title" required>
	 			<option disabled="disabled" value="" selected="selected">Please select your title</option>
				<option value="Mr">Mr</option>
				<option value="Miss">Miss</option>
				<option value="Mrs">Mrs</option>
				<option value="Ms">Ms</option>
		</select>
        <label for="dob" class="sr-only">Date of Birth</label>
        <input type="text" id="dob" name="dob" class="form-control" placeholder="DOB" required>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
      </form>
    </div>
      

<script>
$(document).ready(function(){
  var date_input=$('input[name="dob"]'); //our date input has the name "date"
  var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
  var options={
    format: 'mm/dd/yyyy',
    container: container,
    todayHighlight: true,
    autoclose: true,
  };
  date_input.datepicker(options);
})
</script>
      	  <script>
      		var inputCompany = document.getElementById('company');
      		var selectType = document.getElementById('type');
      		function init(){
      		  inputCompany.style.display = "none";
      		}
      		selectType.onchange = function(){
       	    if(this.value!="manager"){
        			  inputCompany.style.display = "none";
       	    }else{
          		  inputCompany.style.display = "inline-block";
       	              }
           }
       </script>
<script>
var check = function() {
  if (document.getElementById('inputPassword').value ==
    document.getElementById('comfirmPassword').value && document.getElementById('inputPassword').value !='') {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'matching';
  } else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'not matching';
  }
}
</script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
