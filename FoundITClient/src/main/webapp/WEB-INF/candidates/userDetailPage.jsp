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
	<c:set var="user" value="${user}" />
	<body onload="disableChange()">
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
           <li class="active"><a href="/FoundITClient/availableJob">Job List</a></li>
            <li><a href="#home">Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <br>
    <br>
    <br>
    

    <div class="container">
    <form  action="/FoundITClient/updateDetail" name="user">
        <p  class="text-center" style="font-size:32px;">${user.getName()}</p>
   	<div class="form-signin">
   	    	<label style="font-size:23px;" ><strong>DOB</strong></label>
    		<p  style="font-size:23px;">${user.getDob()}</p>
    		<label style="font-size:23px;" ><strong>Email </strong></label>
    		<p  style="font-size:23px;">${user.getEmail()}</p>
	  		<label for="companyName" style="font-size:23px;"><strong>Current Position</strong></label>
	        <input type="text"  style="font-size: 23px;"  onkeyup="showChange()" id="currentPosition" class="form-control" name = "currentPosition" value="${not empty user.getCurrentPosition()?  user.getCurrentPosition() : ''}" >
	        <label for="salaryRate" style="font-size:23px;"><strong>Past Experience</strong></label>
	        <input type="text" style="font-size: 23px;" onkeyup="showChange()" id="pastExperience" class="form-control" name = "pastExperience" value="${not empty user.getPastExperience()?  user.getPastExperience(): ''}" >
	        <label for="positionType" style="font-size:23px;"><strong>Professional Skills</strong></label>
	        <input type="text" style="font-size: 23px;"  onkeyup="showChange()" id="professionalSkills" class="form-control" name = "professionalSkills" value="${not empty user.getProfessionalSkills()?  user.getProfessionalSkills():''}" >
	        <label for="location" style="font-size:23px;"><strong>Education</strong></label>
	        <input type="text" style="font-size: 23px;" onkeyup="showChange()" id="education" class="form-control" name = "education" value="${not empty user.getEducation()?  user.getEducation():''}" >
		</div>
		<br>
		<div class="text-center">
	        <div class="btn-group btn-group-lg">
				<button  type="submit" name = "action"  value = "updateDetail" id="updateDetail" class="btn btn-lg btn-primary">Update</button>
			</div>
		</div>
 		</form> 	
      </div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
    <script>
    function disableChange(){
		document.getElementById("updateDetail").style.display = "none";
    	
    }
    function showChange(){
    	document.getElementById("updateDetail").style.display = "inline";
    	
    }
    </script>
	</body> 
</html>
