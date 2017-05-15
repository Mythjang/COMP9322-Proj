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
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">

    <script src="../js/ie-emulation-modes-warning.js"></script>

  </head>

  <body onload = "init()">

    <div class="container">
	  <div style="text-align:center">
        <h1>Create New Job</h1>
      </div>
      <form class="form-signin" >
        <label for="personalDetail" class="sr-only">Personal Detail</label>
        <input type="text" id="personalDetail" class="form-control" placeholder="Personal Detail" required autofocus>
        <label for="coverLetter" class="sr-only">Cover Letter</label>
        <input type="text" id="coverLetter" class="form-control" placeholder="Cover Letter" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Apply</button>
      </form>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
