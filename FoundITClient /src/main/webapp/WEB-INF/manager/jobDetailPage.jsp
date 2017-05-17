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

    <title>Signin Template for Bootstrap</title>

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
	<body onload = "disableChange();changeOrNot();">
	 <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">FoundIT APP</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav" style="float:right">
            <li><a href="/FoundITClient/jobList">Job Postings</a></li>
            <li><a href="/FoundITClient/logout">Log out</a></li>
            </ul>
        </div>
      </div>
    </nav>


    <br>
    <div class="container">
    <input type="hidden" id="trueStatus" value="${job.getStatus()}">
    <form  action="/FoundITClient/deleteOrChangeJob" name="job">
    	<div class="form-signin">
	  		<label for="companyName">Company Name</label>
	        <input type="text" style='width:auto' onkeyup="showChange()" id="companyName" class="form-control" name = "companyName" value="${job.getCompanyName()}"  required>
	        <label for="salaryRate">Salary Rate</label>
	        <input type="number" style='width:auto' onkeyup="showChange()" id="salaryRate" class="form-control" name = "salaryRate" value="${job.getSalaryRate()}"  required>
	        <label for="positionType">Position Type</label>
	        <input type="text" style='width:auto' onkeyup="showChange()" id="positionType" class="form-control" name = "positionType" value="${job.getPositionType()}"  required>
	        <label for="location">Location</label>
	        <input type="text" style='width:auto'onkeyup="showChange()" id="location" class="form-control" name = "location" value="${job.getLocation()}"  required>
	        <label for="status" >Status</label>
			<select class="selectpicker" id="status" name="status" onchange="showChange()">
			  <option value="open" ${job.getStatus() == "open" ? 'selected="selected"' : ''}>open</option>
			  <option value="appReceived" ${job.getStatus() == "appReceived" ? 'selected="selected"' : ''}>appReceived</option>
			  <option value = "inReview"  ${job.getStatus() == "inReview" ? 'selected="selected"' : ''}>inReview</option>
			  <option value = "close"  ${job.getStatus() == "close" ? 'selected="selected"' : ''}>close</option>
			</select>
		</div>
		<br>
		<div class="text-center">
      		<h2>Job Description</h2>
       		<textarea class="form-control" rows="15" style="width:1000px; margin-left: auto; margin-right: auto;" id="detail" name = "detail"onkeyup="showChange()" required>${job.getDetail()}</textarea>
	        <div class="btn-group btn-group-lg">
	        	 <input type="hidden"  id="key" class="form-control" name = "key" value="${job.getKey()}">
				<button  type="submit" name = "action"  value = "change" id="changeJob" class="btn btn-warning">Change</button>
				<button  type="submit" name= "action" id="deleteJob" value="delete" class="btn  btn-danger">Delete</button>
			</div>
		</div>
 		</form> 	
      </div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
    <script>
    function disableChange(){
		document.getElementById("changeJob").style.display = "none";
    	
    }
    function showChange(){
    	document.getElementById("changeJob").style.display = "inline";
    	
    }
    function changeOrNot(){
    	status = document.getElementById("trueStatus").value;
    	if(status!="open" && status!="close")
    		document.getElementById("deleteJob").style.display = "none";
/*     	if(status!="open" && status!="appReceived") 
    		document.getElementById("changeJob").style.display = "none"; */
    	var op = document.getElementById("status").getElementsByTagName("option");
    	var index;
    	for (index = 0; i < op.length; i++) {
    		  if (op[i].text == status) {
    			  break;
    		  }
    	}
    	for (var i = 0; i < index; i++) {
    	 op[i].disabled = true;
	  	}
    }
    </script>
	</body> 
</html>
