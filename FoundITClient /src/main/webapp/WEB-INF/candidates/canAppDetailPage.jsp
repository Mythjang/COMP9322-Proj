<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <title>Application Details</title>

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
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/css/bootstrap-select.min.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/js/bootstrap-select.min.js"></script>
	</head>
	<c:set var="job" value="${job}" />
	<c:set var="app" value="${app}" />
	<c:set var="decision" value="${decision}" />
	<body onload = "updateOrNot();">
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

    <br>
   	<br>
   	<br>
    <div class="container">
		<h2 class="text-center" style="font-size:32px;">Application Detail</h2>
		<br/>
		<dl class="dl-horizontal" style="padding-left: 450px">
  			<dt style="font-size:20px;">Company Name:</dt>
  				<dd style="font-size:20px;">${job.getCompanyName()}</dd>
  			<dt  style="font-size:20px;">Salary Rate:</dt>
  				<dd style="font-size:20px;">${job.getSalaryRate()}</dd>
  			<dt  style="font-size:20px;">Position Type:</dt>
  				<dd  style="font-size:20px;">${job.getPositionType()}</dd>
 			<dt  style="font-size:20px;" >Location: </dt>
  				<dd style="font-size:20px;">${job.getLocation()}</dd>
  			<dt  style="font-size:20px;">decision: </dt>
  				<dd  style="font-size:20px;">${decision}</dd>
		</dl>
	</div>
	<Form class="text-center" action="/FoundITClient/updateApp">
     	<h2>cover letter</h2>
      	<textarea class="form-control" rows="10" onkeyup="showUpdate()" style="width:800px; margin-left: auto; margin-right: auto;" id="coverLetter" name = "coverLetter" required>${app.getCoverLetter()}</textarea>
        <br>
       	<input type="hidden"  id="jobId" class="form-control" name = "jobId" value="${job.getKey()}">
       	<input type="hidden"  id="appkey" class="form-control" name = "appKey" value="${app.getAppKey()}">
		<button  type="submit" name = "updateApp"  value = "updateApp" id="updateApp" class="btn btn-warning">Update</button>
	</Form>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
    <script>

    function updateOrNot(){
    	document.getElementById("updateApp").style.display = "none";
    	if(document.getElementById("decision").innerHTML != "")
    		document.getElementById("coverLetter").style.display = "inline";
    	else
    		document.getElementById("coverLetter").style.display = "none";
    }
    function showUpdate(){
    	if(document.getElementById("decision").innerHTML == "" || document.getElementById("decision").innerHTML == null)
    	document.getElementById("updateApp").style.display = "inline";	
    }
    </script>
	</body> 
</html>
