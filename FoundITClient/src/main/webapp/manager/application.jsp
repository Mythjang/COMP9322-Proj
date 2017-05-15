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

<body onload="showStuff('pageOne', 'pageTwo','pageThree')">
 
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
<button class="btn" onclick="showStuff('pageOne','pageTwo','pageThree')"> Application </button>
<button class="btn" onclick="showStuff('pageTwo','pageOne','pageThree')"> Hire Team </button>
<button class="btn" onclick="showStuff('pageThree','pageOne','pageTwo')"> Poll & Vote </button>
<div id="pageOne">
    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>Applications</h1>
      </div>
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Cover Letter</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Hei</td>
                <td>Mark</td>
                <td>CSE</td>
                <td>OPEN</td>
                <td ><button type="button" class="btn btn-xs btn-default">Send Notification</button></td>
              </tr>
              <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>Tom</td>
                <td>CSE</td>
                <td>OPEN</td>
                <td ><button type="button" class="btn btn-xs btn-default">Send Notification</button></td>
              </tr>
              <tr>
                <td>3</td>
                <td>Herry</td>
                <td>Mondy</td>
                <td>CSE</td>
                <td>OPEN</td>
                <td ><button type="button" class="btn btn-xs btn-default">Send Notification</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
</div>
<div id= "pageTwo">
    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>Hire Team</h1>
      </div>  
      <form action = "addHireTeam">
        <div class="col-md-8" style="margin:0 auto">
         <button  type="button" class="btn btn-sm btn-blue" onclick = "addCell()" >Add Reviewer</button>
         <button type="submit" class="btn btn-sm btn-blue" id= "confirm">Confirm</button>
          <table id="hireTeamTable" class="table table-striped" style="margin:auto">
            <thead>
              <tr>
                <th>Account</th>
                <th>Password</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Jang</td>
                <td>cse305</td>
                <td><button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button></td>
              </tr>
            </tbody>
          </table>
        </div>
     </form>
      </div>
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
   
<script type="text/javascript">
	function showStuff(id1, id2, id3) {
	    document.getElementById(id1).style.display = 'inline';
	    document.getElementById(id2).style.display  = 'none';
	    document.getElementById(id3).style.display  = 'none';
	    document.getElementById("confirm").style.display = 'none';
	}
</script>

<script type="text/javascript">
function addCell() {
	document.getElementById("confirm").style.display = 'inline';
    var table = document.getElementById("hireTeamTable");
    var index = table.getElementsByTagName("tr").length;
    var row = table.insertRow(index);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<input type="text" name= "userName'+ index +'" id="userName" placeholder="userName'+ index +'" required>';
    cell2.innerHTML = '<input type="text" name= "pass'+ index +'" id="pass" placeholder="pass'+ index +'" required>';
}
</script>
 </body>
  
  
</html>

