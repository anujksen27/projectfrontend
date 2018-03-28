<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ApnaGhar- Aapke Sapno Ka Ghar</title>
<!-- Start WOWSlider.com HEAD section -->
<link rel="stylesheet" type="text/css" href="resources/engine1/style.css" />
<script type="text/javascript" src="resources/engine1/jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
 <style>
/* .container{margin-top: 4em;} */
.thumbnail{height:360px;
box-shadow:0 0 10px grey;
position:relative;}
/* #cart {
position:absolute;
    bottom:5px;
    display:none;
    margin-left:21%;
}
.see{visibility: hidden;} */
</style> 
</head>
<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container" style="margin-bottom:0px"></div>
 <!-- Start WOWSlider.com BODY section -->
<div id="wowslider-container1" style="margin-top: 0px;">
<div class="ws_images"><ul>
    <li><img src="resources/data1/images/banglows.jpg" alt="" title="" id="wows1_0"/></li>
    <li><img src="resources/data1/images/banglows1.jpg" alt="" title="" id="wows1_1"/></li>
    <li><img src="resources/data1/images/flats.jpg" alt="" title="" id="wows1_2"/></li>
    <li><img src="resources/data1/images/sale2.jpg" alt="" title="" id="wows1_3"/></li>
    <li><img src="resources/data1/images/slider.jpg" alt="" title="" id="wows1_4"/></li>
    <li><a href="http://wowslider.net"><img src="resources/data1/images/slider2.jpg" alt="bootstrap image slider" title="" id="wows1_5"/></a></li>
    <li><img src="resources/data1/images/slider3.jpg" alt="" title="" id="wows1_6"/></li>
  </ul></div>
  <div class="ws_bullets"><div>
    <a href="#" title=""><span><img src="resources/data1/tooltips/banglows.jpg" alt=""/>1</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/banglows1.jpg" alt=""/>2</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/flats.jpg" alt=""/>3</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/sale2.jpg" alt=""/>4</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/slider.jpg" alt=""/>5</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/slider2.jpg" alt=""/>6</span></a>
    <a href="#" title=""><span><img src="resources/data1/tooltips/slider3.jpg" alt=""/>7</span></a>
  </div></div><div class="ws_script" style="position:absolute;left:-99%"><a href="http://wowslider.net">css image slider</a> by WOWSlider.com v8.8</div>
<div class="ws_shadow"></div>
</div>  

  <br/>

<center><h1>Property List</h1></center>
<div class="col-sm-offset-3 col-sm-6" style="border:2px solid #ff5252"></div><br/>

<div class="container">
				 <div class="recommended_items"><!--recommended_items-->					
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									
									<c:forEach items="${proDetails}" var="prod"> 
									<div class="col-sm-4">
										<div class="product-image-wrapper ">
											<div class="single-products ">
												<div class="productinfo text-center  thumbnail">
													<img src="resources/images/${prod.pro_imagename }" style="width:350px;heigth:250px;" >
													<h2>${prod.pro_name}</h2>
													<h4><i class="fa fa-inr"></i>${prod.pro_price}/-</h4>
													<h4>${prod.supplier.sup_name}</h4>
													 <a href="addToCart?proid={{product.pro_id}}" class="btn btn-primary" style="background-color:#ff5252;border:none;" id="cart"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>
												</div>
												
											</div>
										</div>
									</div>									
									</c:forEach>																		
								</div>
							</div>
						</div>
					</div>
				</div><!--/recommended_items-->
 	


<%-- 
<div class="container"> 
<div class="col-sm-6 col-md-3" ng-repeat="product in products | filter:searchProduct | orderBy:myOrderBy">
    <div class="thumbnail">
      <a href="productDetails?proid={{product.pro_id}}">
        <img src='<c:url value="/resources/images/{{product.pro_id}}.jpg"/>' alt="{{product.pro_name}}" style="height:60%;"/>
        <div class="caption">
          <center><h4><b style="color:black;">{{product.pro_name}}</b></h4></center><br>
          <center><h5><b style="color:black;">{{product.pro_desc}}</b></h5></center>
          <span style="bottom:5px;color:black;position: absolute;"><h4><i class="fa fa-inr"></i>{{product.pro_price}}/-</h4>
          <h5>Cash on Delivery eligible</h5></span>
          <a href="addToCart?proid={{product.pro_id}}" class="btn btn-primary" style="background-color:#ff5252;border:none;" id="cart"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>          
        </div>
      </a>
    </div>
</div>
</div> --%>

<%-- <div id="HomeACarousel" class="carousel slide" data-ride="carousel" data-interval="2400">
    <div class="carousel-inner">
    <div class="item active">
      <c:if test="${homeProducts.size()==0}">
		<center><h1>Nothing to show..Contact to Admin</h1></center>
      </c:if>
      <c:forEach items="${homeProducts}" var="homeProduct">
      <c:if test="${homeProduct.barNumber==101}">
	<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href="productDetails?proid=${homeProduct.product.pro_id}">
        <img src='<c:url value="/resources/images/${homeProduct.product.pro_id}"/>.jpg' alt="${homeProduct.product.pro_name}" style="height:60%;"/>
        <div class="caption">
          <center><h4><b>${homeProduct.product.pro_name}</b></h4></center>
          <span style="bottom:2em;position:absolute;"><h4><i class="fa fa-inr"></i>${homeProduct.product.pro_price}</h4>
          <h5>Cash on Delivery eligible</h5></span>
        </div>
      </a>
    </div>
</div></c:if>
  </c:forEach>
</div>
<div class="item">
      <c:if test="${homeProducts.size()==0}">
		<center><h1>Nothing to show..Contact to Admin</h1></center>
      </c:if>
      <c:forEach items="${homeProducts}" var="homeProduct">
      <c:if test="${homeProduct.barNumber==102}">
	<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href="productDetails?proid=${homeProduct.product.pro_id}">
        <img src='<c:url value="/resources/images/${homeProduct.product.pro_id}"/>.jpg' alt="${homeProduct.product.pro_name}" style="height:60%;"/>
        <div class="caption">
          <center><h4><b>${homeProduct.product.pro_name}</b></h4></center>
          <span style="bottom:2em;position:absolute;"><h4><i class="fa fa-inr"></i>${homeProduct.product.pro_price}</h4>
          <h5>Cash on Delivery eligible</h5></span>
        </div>
      </a>
    </div>
</div></c:if>
  </c:forEach>
</div>    
    </div>
 </div>
 --%>
    <br/><br/><%--<br/>
  <div class="container">
  <div class="col-sm-4">
  <img src="<c:url value="/resources/images/flats.jpg"/>" style="width:100%"/>
  </div>
  <div class="col-sm-4">
  <img src="<c:url value="/resources/images/flats2.jpg"/>" style="width:100%"/>
  </div>
  <div class="col-sm-4">
  <img src="<c:url value="/resources/images/flats1.jpg"/>" style="width:100%"/>
  </div>
  </div> --%>
  
 <%--  
  <center><h1>New Launched Products</h1></center>
<div class="col-sm-offset-3 col-sm-6" style="border:2px solid red"></div><br/>
<div id="HomeBCarousel" class="carousel slide" data-ride="carousel" data-interval="2400">
    <div class="carousel-inner">
    <div class="item active">
      <c:if test="${homeProducts.size()==0}">
      	<div class="col-sm-12">
  			<img src="<c:url value="/resources/images/flats2.jpg"/>" style="width:100%;height:200px;"/>
 		 </div>
		<!-- <center><h1>Nothing to show..Contact to Admin</h1></center> -->
      </c:if>
      <c:forEach items="${homeProducts}" var="homeProduct">
      <c:if test="${homeProduct.barNumber==201}">
	<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href="productDetails?proid=${homeProduct.product.pro_id}">
        <img src='<c:url value="/resources/images/${homeProduct.product.pro_id}"/>.jpg' alt="${homeProduct.product.pro_name}" style="height:60%;"/>
        <div class="caption">
          <center><h4><b>${homeProduct.product.pro_name}</b></h4></center>
          <span style="bottom:2em;position:absolute;"><h4><i class="fa fa-inr"></i>${homeProduct.product.pro_price}</h4>
          <h5>Cash on Delivery eligible</h5></span>
        </div>
      </a>
    </div>
</div></c:if>
  </c:forEach>
</div>
 --%><%-- <div class="item">
      <c:if test="${homeProducts.size()==0}">
		<center><h1>Nothing to show...Contact to Admin</h1></center>
      </c:if>
      <c:forEach items="${homeProducts}" var="homeProduct">
      <c:if test="${homeProduct.barNumber==202}">
	<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href="productDetails?proid=${homeProduct.product.pro_id}">
        <img src='<c:url value="/resources/images/${homeProduct.product.pro_id}"/>.jpg' alt="${homeProduct.product.pro_name}" style="height:60%;"/>
        <div class="caption">
          <center><h4><b>${homeProduct.product.pro_name}</b></h4></center>
          <span style="bottom:2em;position:absolute;"><h4><i class="fa fa-inr"></i>${homeProduct.product.pro_price}</h4>
          <h5>Cash on Delivery eligible</h5></span>
        </div>
      </a>
    </div>
</div></c:if>
  </c:forEach>
</div>    
    </div>
  </div>
<br/>
 --%>
 <div class="container">
				<div class="recommended_items"><!--recommended_items-->					
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									
									
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/flats.jpg" alt="" />
													<h2><i class="fa fa-inr"></i>5,60,000</h2>
													<p>1 BHK Flats</p>
													<p>@ Vasundhra Apartments</p>
													<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												
											</div>
										</div>
									</div>
									
									
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/flats1.jpg" alt="" />
													<h2><i class="fa fa-inr"></i>6,00,000/ <i class="fa fa-inr"></i>10,00,000</h2>
													<p>1 BHK/2 BHK Flats</p>
													<p>@ Radhika Apartments</p>
													<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/flats2.jpg" alt="" />
													<h2><i class="fa fa-inr"></i>6,00,000/ <i class="fa fa-inr"></i>10,00,000</h2>
													<p>1 BHK/2 BHK Flats</p>
													<p>@ Jurs Country</p>
													<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												
											</div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div><!--/recommended_items-->

	
	
<script type="text/javascript" src="resources/engine1/wowslider.js"></script>
<script type="text/javascript" src="resources/engine1/script.js"></script>
<!-- End WOWSlider.com BODY section -->
<script type="text/javascript" src="/resources/js/main.js"></script>
<%@include file="/WEB-INF/views/Footer.jsp"%>
<script>
var app = angular.module('productApp', []);
app.controller('myCtrl', function($scope) {
    $scope.products=${proDetails}
    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }
});
$(document).ready(function(){

    $(".thumbnail").hover(function(){
    	$(this).find("#cart").slideToggle(100)
    	$(this).find("h5").toggleClass("see");
    });
});
</script>

</body>
</html>