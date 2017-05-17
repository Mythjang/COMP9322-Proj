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

    <title>Search</title>

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
  <body>
	    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">FoundIT APP</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav" style="float:right">
            <li><a href="/FoundITClient/searchPage">Search</a></li>
            <li><a href="/FoundITClient/availableJob">Job List</a></li>
            <li><a href="/FoundITClient/logout">Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <br>
    <div class="btn-group btn-group-justified">
	    <a href="/FoundITClient/myApplications" class="btn btn-primary">My Applications</a>
	    <a href="/FoundITClient/showDetail" class="btn btn-primary">My detail</a>
    </div>

    <div class="container">
      <form class="form-signin"  action = "/FoundITClient/searchJob">
        <h2 class="form-signin-heading">search</h2>
        

        <label for="companyName" class="sr-only">Company Name</label>
        <input type="text" name="companyName" id="companyName" class="form-control" placeholder="Company Name" >
       
       	<label for="positionType" class="sr-only">Position Type</label>
        <input type="text" name="positionType" id="positionType" class="form-control" placeholder="Position Type">
       	
       	<label for="salaryRate" class="sr-only">Minimum Salary</label>
        <input type="number" name="salaryRate" id="salaryRate" class="form-control" placeholder="Minimum Salary">
       	
       	<label for="location" class="sr-only">Location</label>
        <input type="text" name="location" id="location" class="form-control" placeholder="Location">
    
        <label for="status" class="sr-only">Status</label>
        <select class="form-control" id="status" name="status">
	 			<option disabled="disabled" value="" selected="selected">Please select a Job Status</option>
				<option value="open">Open</option>
				<option value="appReceived">Application received</option>
				<option value="inReview">In review</option>
				<option value="close">close</option>
		</select>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Search</button>
      </form>
    </div>
      

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
