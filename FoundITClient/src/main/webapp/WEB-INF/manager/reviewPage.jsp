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

    <title>Application Page</title>

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
            <li><a href="manager.jsp">Job Postings</a></li>
            <li><a href="#home">Log out</a></li>
            </ul>
        </div>
      </div>
    </nav>
    
 <div class="btn-group btn-group-justified">
    <a href="/FoundITClient/application" class="btn btn-primary">Application</a>
    <a href="/FoundITClient/hireTeam" class="btn btn-primary">Hire Team</a>
    <a href="/FoundITClient/review" class="btn btn-primary">Poll & Vote</a>
  </div>
  
	<div id= "pageThree">    
     <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>Review</h1>
      </div>
      <div class="col-md-8" style="margin:0 auto">
          <table class="table table-striped" >
            <thead>
              <tr>
                <th>appid</th>
                <th>Reviewer Details</th>
                <th>Comments</th>
                <th>Decision</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Very good</td>
                <td>to be continue</td>
                <td>recommended</td>
              </tr>
              <tr>
                <td>2</td>
                <td>no good</td>
                <td>get the f off</td>
                <td>No recommended</td>
              </tr>
            </tbody>
          </table>
        </div>
       </div>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="/FoundITClient/resources/js/bootstrap.min.js"></script>
    <script src="/FoundITClient/resources/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/FoundITClient/resources/js/ie10-viewport-bug-workaround.js"></script>
   
 </body>
  
  
</html>

