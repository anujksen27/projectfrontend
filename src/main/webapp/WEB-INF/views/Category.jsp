<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Category Admin List</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="header.jsp" %>
<div class="container">
<br/>
<script type="text/javascript" src="<c:url value="/resources/js/inputform.js"/>"></script>
	<input type="hidden" value="${flag}" id="sh"/>
	<c:if test="${warning!=null}">
  <div class="modal" id="warningModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header headStyle">
          Warning!!!!!
        </div>
        <div class="modal-body text-center">
          <h2><b>${warning}</b></h2>
        </div>
		<div class="modal-footer">
          <button type="button" class="btn btn-danger btn-block myBoldFont" data-dismiss="modal">Close</button>
        </div>
      </div>
     </div>
  </div>
  </c:if>
	<div class="panel-group">
		<div class="panel panel-primary" style="border:1px solid black;">
			<div class="panel-heading" style="background-color:#ff5252;">
			<button type="button" class="btn btn-info btn-sm myBoldFont pull-right" id="myBtn">Add New Category</button>
			<span class="myBoldFontB">Category Details</span>
				
			</div>
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header headStyle">
					    	<c:if test="${!flag}">Add Category</c:if>
						    <c:if test="${flag}">Update Category</c:if>
					    </div>
						<div class="modal-body" style="padding:40px 50px;">
							<c:if test="${flag}"><form action="../updateCategory" method="post" class="form-horizontal"></c:if>
							<c:if test="${!flag}"><form action="addCategory" method="post" class="form-horizontal"></c:if>
							<div class="form-group">
								<label class="control-label col-sm-5 myBoldFont" for="homeCat">Home Category</label>
								<div class="col-sm-7">
								<select name="home_cat" id="homeCat" class="form-control" required>
								<c:if test="${!flag}"><option value="" label="-------- Select Home Category --------"/></c:if>
								<c:if test="${flag}"><option value="${category.home_cat}">${category.home_cat}</option></c:if>
								<option value="Sale">Sale</option>
								<option value="Buy">Buy</option>
								<option value="Rent">Rent</option>
								<option value="Cities">Cities</option>
								</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-5 myBoldFont" for="catName">Category Name</label>
								<div class="col-sm-7">
								<c:if test="${flag}"><input type="text" name="name" value="${category.cat_name}" id="catName" class="form-control" required maxlength="25"/></c:if>
								<c:if test="${!flag}"><input type="text" name="name" id="catName" class="form-control" required maxlength="25"/></c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-5 myBoldFont" for="catDesc">Category Description</label>
								<div class="col-sm-7">
								<c:if test="${flag}"><textarea name="desc" id="catDesc" class="form-control" rows="5" placeholder="Add Description" required maxlength="255">${category.cat_desc}</textarea></c:if>
								<c:if test="${!flag}"><textarea name="desc" id="catDesc" class="form-control" rows="5" placeholder="Add Description" required maxlength="255"></textarea></c:if>
								</div>
							</div>
							<c:if test="${flag}"><input type="hidden" name="catId" value="${category.cat_id} "/></c:if>
							<div class="col-sm-6">
							<input type="submit" value="Save" class="btn btn-info btn-block myBoldFont"/></div>
							<div class="col-sm-6">
							<c:if test="${!flag}"><button class="btn btn-danger btn-block myBoldFont" data-dismiss="modal">Cancel</button></c:if>
							<c:if test="${flag}"><a href="../Category" class="btn btn-danger btn-block myBoldFont">Cancel</a></c:if></div><br/>
							</form>
						</div>
					</div>
			    </div>
			</div>
		
			<div class="panel-body table-responsive">
				<table class="table table-hover myFont">
					<tr><th>CatId</th><th>Category Name</th><th>Category Description</th><th>Home Category</th><th>Operation</th></tr>
					<c:forEach items="${catDetails}" var="category">
						<tr>
							<td>${category.cat_id}</td><td>${category.cat_name}</td><td>${category.cat_desc}</td><td>${category.home_cat}</td>
							<td>
								<a href="<c:url value="updateCategory/${category.cat_id}"/>" id="update" class="btn btn-info myBoldFont">Update</a>
								<a href="<c:url value="deleteCategory?catId=${category.cat_id}"/>" class="btn btn-danger myBoldFont">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/Footer.jsp"%>
</body>
</html>
