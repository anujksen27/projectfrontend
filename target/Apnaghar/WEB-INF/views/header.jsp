<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link  rel="stylesheet" href='<c:url value="/resources/css/style.css"/>'/>
<link rel="icon" href="resources/images/icon.png">
</head>
<body>
<div class="container-fluid">
		<div class="col-sm-6 logo-img ">
    		<a href="#"><img src="resources/images/Apna.png" title="ApnaGhar- Aapke Aapno Ka Ghar"></a><br>    
  		</div>
   <div class="col-sm-6 new_social">
    <ul>
    <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
    <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
    <li><a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
    <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
    </ul>
</div> 
</div>

<nav class="navbar navbar-inverse" style="margin-bottom:0px;border-radius:0px;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="home"><span class="glyphicon glyphicon-home"></span> Home</a>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="about_us">About Us</a></li> 
        <li class="dropdown"><a>Sale
        <span class="caret"></span></a>
        <ul class="dropdown-menu"><c:forEach items="${catDetails}" var="homeCat">
        	<c:if test="${homeCat.home_cat=='Sale'}">
        	<li><a href="viewProducts?id=${homeCat.cat_id}">${homeCat.cat_name}</a></li>
        	</c:if></c:forEach>
        </ul></li>
        <li class="dropdown"><a>Buy
        <span class="caret"></span></a>
        <ul class="dropdown-menu"><c:forEach items="${catDetails}" var="homeCat">
        <c:if test="${homeCat.home_cat=='Buy'}">
        	<li><a href="viewProducts?id=${homeCat.cat_id}">${homeCat.cat_name}</a></li>
        	</c:if>
        	</c:forEach>
        </ul></li>
        <li class="dropdown"><a>Rent
        <span class="caret"></span></a>
        <ul class="dropdown-menu"><c:forEach items="${catDetails}" var="homeCat">
        <c:if test="${homeCat.home_cat=='Rent'}">
        	<li><a href="viewProducts?id=${homeCat.cat_id}">${homeCat.cat_name}</a></li>
        	</c:if>
        	</c:forEach>
        </ul></li> 
        <li class="dropdown"><a>Cities
        <span class="caret"></span></a>
        <ul class="dropdown-menu"><c:forEach items="${catDetails}" var="homeCat">
        <c:if test="${homeCat.home_cat=='Cities'}">
        	<li><a href="viewProducts?id=${homeCat.cat_id}">${homeCat.cat_name}</a></li>
        	</c:if>
        	</c:forEach>
        </ul></li> 
        <li><a href="contact_us">Contact Us</a></li> 
      </ul>
		
      <ul class="nav navbar-nav navbar-right">
      <c:if test='<%=(Boolean)session.getAttribute("loggedIn")!=null&&(Boolean)session.getAttribute("loggedIn")==true%>'>
      <li><a href="<c:url value="/showCart"/>">
      <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Cart</a>
      </li>
      <c:if test='<%=(String)session.getAttribute("user")==null%>'>
      <li class="dropdown">
      <a>Admin Dashboard<span class="caret"></span>
      <ul class="dropdown-menu">
      <li><a href="<c:url value="/Category"/>">Category Admin List</a></li>
      <li><a href="<c:url value="/Product"/>">Products Admin List</a></li>
      <li><a href="<c:url value="/Supplier"/>">Supplier Admin List</a></li>
      <li><a href="<c:url value="/homeProducts"/>">HomeProducts Admin List</a></li>
      </ul></a>
      </li></c:if>
      <li class="dropdown">
      <a>
      <span class="img-circle"><%=session.getAttribute("usertitle")%></span> <%=session.getAttribute("username")%>
       <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a href="<c:url value="/myAccount"/>">My Account</a></li>
         <li><a href="<c:url value="/myOrders"/>">My Orders</a></li>
         <li><a href="<c:url value="/perform_logout"/>">
         <span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
       </li>
       </c:if>
       <c:if test='<%=(Boolean)session.getAttribute("loggedIn")==null%>'>
         <li><a href="register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </c:if>
      
      </ul>
      <c:if test="${search!=null}">
      <div class="navbar-form navbar-right form-group" >
        	<input type="text" ng-model="searchProduct" class="form-control" placeholder="Search" style="width:100%">
      	</div>
      </c:if>
    </div>
    
      
  </div>
</nav>