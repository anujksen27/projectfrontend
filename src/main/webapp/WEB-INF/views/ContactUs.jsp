<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Contact Us</title>
<link rel="icon" href="resources/images/icon.png">
<link >
</head>
<body >
<jsp:include page="header.jsp"></jsp:include>
		
		<div class="jumbotron jumbotron-sm" style="margin-top: -20px;">
		    <div class="container">
		        <div class="row">
		            <div class="col-sm-12 col-lg-12">
		                <h1 class="h1" style="text-align:center;color:#ff5252;">
		                    C<small>ontact</small>  U<small>s</small> </h1>
		            </div>
		        </div>
		    </div>
		</div>


	<div class="container">
    	<div class="row">
	    	<div class="col-md-4 pm-columnPadding30" style="color:#ff5252;">
	            <form>
	            <h5 class="title-divider"><span class="glyphicon glyphicon-globe" style="color:#ff5252;"></span>
	            &nbsp<b style="color:#ff5252;">Our office</b></h5>	            
	            <address>
	                <strong>ApnaGhar.com</strong><br>
	                <p style="color:black;">Near District Centre<br>
	                Janakpuri, New Delhi-110059<br>
	                <abbr title="Phone">
	                    Mob:</abbr>
	                (123) 456-7890</p>
	            </address>
	            <address>
	                <strong>Email:</strong>
	                <a href="mailto:#" style="color:black;">services@apnaghar.com</a>
	            </address>
	            </form>
	        </div>
	        <div class="col-md-8 pm-columnPadding30">
	            <div class="row">
	                <div class="col-lg-12 pm-containerPadding-bottom-20">
	                     <h5 class="title-divider">Contact us by Form</h5>
                    <!-- <div class="pm-column-title-divider">
	                </div> -->
	            </div>
	        </div>
	        <br>
	        
	        <div class="row">
	                    <form action="send_mail" method="POST">
	                    <div class="col-lg-6 col-md-6 col-sm-12">
	                        <input name="fname" type="text" id="fname" class="pm-form-textfield" placeholder="First Name" />
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-12">
	                        <input name="lname" type="text" id="lname" class="pm-form-textfield" placeholder="Last Name" />
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-12">
	                        <input name="email" type="text" id="email" class="pm-form-textfield" placeholder="Email Address" />
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-12">
	                        <input name="mobile" type="phone" id="mobile" class="pm-form-textfield" placeholder="Phone Number" />
	                    </div>
	                    <div class="col-lg-12">
	                        <textarea name="message" rows="10" cols="50" id="message" class="pm-form-textarea" placeholder="Message">
							</textarea>
	                    </div>
	                    <div class="col-lg-6 ">
	                        <input type="submit" name="submit" value="Send Message" id="submit" class="pm-form-submit-btn" />	
	                    </div>
	                    </form>
	         </div>
	    </div>
	</div>
</div>

<br>
<jsp:include page="Footer.jsp"></jsp:include>
