<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<style>
.backImg{
background-image: url('<c:url value="/resources/images/about.jpg"/>');
height:28vw;
background-size: 100%;
padding-top:4vw;
}
</style>
<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container-fluid backImg text-center">
<span style="color:#fff;font-family: Forte;font-size: 8vw;">403 - Access is denied</span>
<br/><span style="color:#fff;font-family: Forte;font-size: 4vw;">You do not have permission to access this page!</span>
</div>
<%@include file="/WEB-INF/views/Footer.jsp"%>			 
</body></html>
