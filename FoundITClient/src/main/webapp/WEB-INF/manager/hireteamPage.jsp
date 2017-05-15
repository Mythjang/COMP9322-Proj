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

<body onload="addOrNot()">
 
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
            <li><a href="/FoundITClient/jobList">Job Postings</a></li>
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
  
<div id= "pageTwo">
    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>Hire Team</h1>
      </div>  
      <form action = "addHireTeam">
        <div class="col-md-8" style="margin:0 auto">
         <button  type="button" class="btn btn-sm btn-blue" id="add" onclick = "addCell()" >Add Reviewer</button>
         <button type="submit" class="btn btn-sm btn-blue" id= "confirm" style="display:none">Confirm</button>
          <table id="hireTeamTable" class="table table-striped" style="margin:auto">
            <thead>
              <tr>
                <th>Account</th>
                <th>Password</th>
              </tr>
            </thead>
            <tbody>
			 <c:forEach items="${hireTeams}" var="hireTeam">
              <tr>
                <td>${hireTeam.getEmail()}</td>
                <td>${hireTeam.getPassword()}</td>
                <%-- <td>
                	<input type = "hidden" value = "${hireTeam.getEmail()}" name="delete">
                	<button  type="submit" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </td> --%>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
     </form>
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
	document.getElementById("add").style.display = 'none';
    var table = document.getElementById("hireTeamTable");
    index =  table.rows.length;
  //  var index =  table.rows[table.rows.length-1].cells[0].children[0].getAttribute('name');
    var row = table.insertRow(index);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    cell1.innerHTML = '<input type="email" name="email" id="userName" placeholder="Email" required>';
    cell2.innerHTML = '<input type="text" name= "password" id="pass" placeholder="password" required>';
    cell3.innerHTML = '<button type="button" id="del'+ index +'" class="close" aria-label="Close" onclick="del('+index+')"><span aria-hidden="true">&times;</span></button>';
    console.log(index);
    
}
function del(id){
    var table = document.getElementById("hireTeamTable").deleteRow(id);
	document.getElementById("confirm").style.display = 'none';
	document.getElementById("add").style.display = 'inline';
}

function addOrNot(){
    var table = document.getElementById("hireTeamTable");
    index =  table.rows.length;
    if(index > 5) document.getElementById("add").style.display = 'none';
	
}
</script>
 </body>
  
  
</html>

