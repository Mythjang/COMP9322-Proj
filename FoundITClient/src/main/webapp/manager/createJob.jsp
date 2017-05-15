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

    <title>Create New Job</title>

    <!-- Bootstrap core CSS -->
    <link href="/FoundITClient/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/FoundITClient/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="/FoundITClient/resources/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/FoundITClient/resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body onload = "init()">

    <div class="container">
	  <div style="text-align:center">
        <h1>Create New Job</h1>
      </div>
      <form class="form-signin" action = "addJob" name = "job" >
        <label for="companyName" class="sr-only">CompanyName</label>
        <input type="text" id="companyName" name="companyName" class="form-control" placeholder="Company Name" required autofocus>
        <label for="salaryRate" class="sr-only">Salary Rate</label>
        <input type="text" id="salaryRate" name="salaryRate" class="form-control" placeholder="Salary Rate" required>
        <label for="positionType" class="sr-only">Position Type</label>
        <input type="text" name="positionType" id="positionType" class="form-control" placeholder="Position Type" required>
        <label for="location" class="sr-only">Location</label>
        <input type="text" name= "location" id="location" class="form-control" placeholder="Location" required>
        <label for="details" class="sr-only">Details</label>
        <textarea id="details" name="details" class="form-control" rows="3" placeholder="Details" required></textarea>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Add Job</button>
         
      </form>
      <div class ="form-signin">
      <a href="/FoundITClient/jobList"><button class="btn btn-lg btn-primary btn-block" >back</button></a>
      </div>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
