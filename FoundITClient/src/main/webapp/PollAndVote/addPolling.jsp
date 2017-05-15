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

    <title>Polling and Voting</title>

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
  </head>

  <body onload = "init()">

    <div class="container">
      <form class="form-signin"  action = "../addPoll" name = "user">
        <h2 class="form-signin-heading">Add Polling</h2>
		<label for="inputEmail" class="sr-only">Email</label>
        <input type="email" id="inputEmail" class="form-control"  name = "email" value="" placeholder="Email" >
        <label for="inputPollTitle" class="sr-only">Poll Title</label>
        <input type="text" name="title" id="inputPollTitle" class="form-control" placeholder="Poll Title" required autofocus>
        <label for="inputDescription" class="sr-only">Description</label>
        <input type="text" name="description" id="inputDescription" class="form-control" placeholder="Description"  required>
        <label for="inputpollOptionType" class="sr-only">Options Type</label>
        <input type="text" id="inputpollOptionType"  name="pollOptionType" class="form-control" placeholder="Options Type" required>
        <label for="inputOptions" class="sr-only">Options</label>
        <input type="text" id="inputOptions" name="options" class="form-control" placeholder="Options" required>
        <label for="inputComments" class="sr-only">Comments</label>
        <input type="text" id="inputComments" name="comments" class="form-control" placeholder="Comments" required>
        <label for="inputFinalChoice" class="sr-only">Final Choice</label>
        <input type="text" id="inputFinalChoice" name="finalChoice" class="form-control" placeholder="Introduction" required>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Add New Poll</button>
      </form>
      
      
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
      

    <!-- Form code begins -->
    </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
