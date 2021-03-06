<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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

    <title>Manager Page</title>

    <!-- Bootstrap core CSS -->
    <link href="/FoundITClient/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="/FoundITClient/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/FoundITClient/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/FoundITClient/resources/css/theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/FoundITClient/resources/js/ie-emulation-modes-warning.js"></script>

  </head>

  <body>

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


    <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Hello Dear Manager</h1>
        <p>Welcome to use our system.</p>
      </div>


      <div class="page-header">
        <h1>Current Jobs</h1>
      </div>
      <div style="text-align:center">
         <a style="margin:0 auto" href="createJ"><button type="button" class="btn btn-sm btn-blue">Add New Job</button></a>
      </div>
      <br/>
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Company Name</th>
                <th>Salary Rate</th>
                <th>Position Type</th>
                <th>Location</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
             <c:forEach items="${jobs.getJobs()}" var="job">
              <tr>
                <td>${job.getCompanyName()}</td>
                <td>${job.getSalaryRate()}</td>
                <td>${job.getPositionType()}</td>
                <td>${job.getLocation()}</td>
                <td>${job.getStatus()}</td>
                <td>
                	<form action="/FoundITClient/jobDetail">
                	<input type = "hidden" value = "${job.getKey()}" name="jobDetail">
  					<button class="btn btn-s btn-basic" type="submit">Job detail</button>
  					</form>
  				</td>
                <td>
  				<td><form action="/FoundITClient/detail">
  					<input type = "hidden" value = "${job.getKey()}" name="detail">
  					<button class="btn btn-s btn-basic" type = "submit">Recruit Management</button>
  				</form></td>
                <td><form action="deleteJob">
                	<input type = "hidden" value = "${job.getKey()}" name="delete">
                	<button  type="submit" style="display:${job.getStatus()=='open' or job.getStatus()=='close'  ? 'inline':'none'}" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </form></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>




    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../../assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

