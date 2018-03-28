<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">

</head>
<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container-fluid">

				<c:if test="${userExist!=null}"><br/><br/>
				<span style="border:1px solid white;padding:10px;font-size: 25px;">${userExist}</span>
				</c:if>
				<div class="container" style="margin-top: 0px; height:800px;" >
		<div id="login-box"  style="margin-top: 0px;" >
				<div class="logo" >
					<!-- <img src="http://lorempixel.com/output/people-q-c-100-100-1.jpg" class="img img-responsive img-circle center-block"/> -->
					<h1 class="logo-caption"><span class="tweak">S</span>ign<span class="tweak">U</span>p</h1>
				</div><!-- /.logo -->
		
		<div class="controls">
			<form action="addUserDetails" method="POST">
			<input type="text" name="full_name" placeholder="Enter Name" class="form-control" required="" maxlength="25" pattern="[A-Za-z]+[ ]*[A-Za-z]*" title="Enter a valid name"/><br>
			<input type="email" name="email" placeholder="Enter Email" class="form-control" required="" title="Enter a valid email address"/><br>
			<input type="phone" name="mobile" placeholder="Mobile Number" class="form-control" required="" pattern="[7-9]{1}[0-9]{9}" title="Enter a correct mobile number" maxlength="10"/><br>
			<input type="text" name="username" placeholder="Create Username" class="form-control" required="" /><br>
			<textarea type="text" name="address" placeholder="Address" class="form-control" required="" rows="3" maxlength="255"></textarea><br>
			<input type="password" name="password" placeholder="Create Password" class="form-control" required="" pattern="[A-za-z0-9]{8,}" title="Atleast have 8 characters, special characters or spaces are not allowed"/><br>
			
			<button type="submit" class="btn btn-default btn-block btn-custom">SignUp</button>
			<!-- <div class="from-style">
			<span style="color: #fff;">Already have an account?</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login">Sign In</a>
			</div> -->
			</form>
			<br><br>
			<span style="color: #fff;">Already have an account?</span>&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login">Sign In</a>
			
		</div><!-- /.controls -->
	</div><!-- /#login-box -->
</div><!-- /.container -->
</div>							


<%@include file="/WEB-INF/views/Footer.jsp"%>
</body>
</html>