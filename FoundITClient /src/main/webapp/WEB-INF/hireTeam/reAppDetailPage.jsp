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
	<c:set var="rev" value="${rev}" />
	<c:set var="app" value="${app}" />
	<body onload="changeOrNot('${rev.getDecision()}')">
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">FoundIT APP</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav" style="float:right">
            <li><a href="/FoundITClient/pendingApp">Pending Applications</a></li>
            <li><a href="/FoundITClient/processedApp">Processed Applications</a></li>
            <li><a href="/FoundITClient/logout">Log out</a></li>
           </ul>
        </div>
      </div>
    </nav>
    <br>
   	<br>
   	<br>
    <div class="container">
		<h2 class="text-center"style="font-size:32px;">Application Detail</h2>
		<p class="text-center" style="font-size:20px;"><strong>Title: </strong>${app.getTitle()}</p>
		<p class="text-center" style="font-size:20px;"><strong>Name: </strong>${app.getName()}</p>      
		<p class="text-center" style="font-size:20px;"><strong>Dob: </strong>${app.getDob()}</p>
		<p class="text-center" style="font-size:20px;"><strong>Current Position: </strong>${app.getCurrentPosition()}</p>    
		<p class="text-center" style="font-size:20px;"><strong>Education: </strong>${app.getEducation()}</p>    
		<p class="text-center" style="font-size:20px;"><strong>Past Experience: </strong>${app.getPastExperience()}</p>    
		<p class="text-center" style="font-size:20px;"><strong>Professional Skills: </strong>${app.getProfessionalSkills()}</p>    
		<p class="text-center" style="font-size:20px;"><strong>Cover Letter: </strong>${app.getCoverLetter()}</p>    
		<div id = "reviewed">
		<p class="text-center" style="font-size:20px;"><strong>Decision: </strong>${rev.getDecision()}</p>    
		<p class="text-center" style="font-size:20px;"><strong>Comments: </strong>${rev.getComments()}</p>    
		</div>
		<Form action="confirmApp" id="notReview">
        <select class="form-control" style="width:auto; margin-left: auto; margin-right: auto;" id="decision" name="decision" required>
	 			<option disabled="disabled" value="" selected="selected">decision</option>
				<option value="recommend">Recommend</option>
				<option value="notRecommend">Not Recommend</option>
		</select>
		<label for="commentArea">Comment: </label>
		<div id = "commentArea">
			<textarea class="form-control" rows="13" style="width:800px; margin-left: auto; margin-right: auto;" id = "comment"  name = "comment"  required></textarea>
	    </div>
		<div class="text-center">
	        <div class="btn-group btn-group-lg">
	        	<input type="hidden" name="appKey" value="${app.getAppKey()}">
				<button  type="submit" name = "confirmApp"  value = "confirmApp" id="confirmApp" class="btn btn-lg btn-primary">Confirm</button>
			</div>
		</div>
		</Form>	
	</div>
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
    <script>
    function changeOrNot(dec){
	    if(dec!=null && dec!=""){
	    	document.getElementById("notReview").style.display = "none";
	    	document.getElementById("reviewed").style.display = "inline";
	    }else{
	    	
	    	document.getElementById("notReview").style.display = "inline";
	    	document.getElementById("reviewed").style.display = "none";
	    }
    } 
    </script>
    
	</body> 
</html>
