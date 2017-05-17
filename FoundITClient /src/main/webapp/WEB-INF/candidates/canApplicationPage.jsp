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

    <title>My Applications</title>

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

    <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Hello Dear Manager</h1>
        <p>Welcome to use our system.</p>
      </div>


      <div class="page-header">
        <h1>Applications</h1>
      </div>
      <br/>
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Application Key</th>
                <th>Job Id</th>
              </tr>
            </thead>
            <tbody>
			 <c:forEach items="${apps.getApps()}" var="app">
              <tr>
                <td>${app.getAppKey()}</td>
                <td>${app.getJobId()}</td>
                <td><form action="/FoundITClient/canAppDetail">
  					<input type = "hidden" value = "${app.getAppKey()}" name="appKey">
  					<input type = "hidden" value = "${app.getJobId()}" name="jobId">
  					<button class="btn btn-s btn-basic" type = "submit">detail</button>
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

