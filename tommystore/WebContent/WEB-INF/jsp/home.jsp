<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0053)https://getbootstrap.com/docs/4.0/examples/dashboard/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/dashboard.css"/>" rel="stylesheet">
  <style type="text/css">/* Chart.js */
@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>

 <jsp:include page="/WEB-INF/jsp/side-navbar-user.jsp" />
  <body>
  	
<%-- 	<h2><c:out value = "Welcome ${user.email}!"/></h2> --%>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
		<!-- Button trigger modal -->
			<!-- Modal -->
			<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLongTitle">Login as User</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <a class="btn btn-primary" href="login">Login</a>
			      </div>
			    </div>
			  </div>
			</div>
	  		<div class="container">
				<h2><c:out value = "Welcome ${user.email}"/></h2>
	  		<ul class="list-inline">
				<li class="list-inline-item">
       			   <h2>Popular products</h2>
       			   
				   <c:choose>
						<c:when test="${message!=null}">  
							<div class="alert alert-danger" role="alert">
						  		<c:out value = "${message}"/>	
							</div>
						</c:when>
					</c:choose>       		
				</li>
				<li class="list-inline-item float-right">

					<form:form action="search-product" method="POST" modelAttribute="searchBean">
						<div class="input-group form-group">
							  <form:input path="keyword" type="text" class="form-control" placeholder="keyword" aria-label="Search" aria-describedby="basic-addon2"/>
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-info" type="submit">Search</button>
						  	</div>
						</div>
					</form:form>
				</li>
			</ul>
			  <div class="table-responsive">
			  
       	<c:choose>
	       <c:when test="${user==null}">  
 				<table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>Product name</th>
	                  <th>Price</th>
	                  <th>Category</th>
					  <th>Action</th>
	                </tr>
	              </thead>
	              <tbody>
			         <c:forEach var="product" items="${productList}">
				         <tr>
					         <td><c:out value = "${product.name}"/></td>
					         <td><c:out value = "${product.price}"/></td>
					         <td><c:out value = "${product.category.name}"/></td>
					    	 <td>
						    	 <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModalCenter">
								 	 Add To Cart
								 </button>
							 </td>
				         </tr>
			         </c:forEach>
	              </tbody>
	            </table>
		     </c:when>
		     <c:when test="${user!=null}">  
				<table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>Product name</th>
	                  <th>Price</th>
	                  <th>Category</th>
					  <th>Action</th>
	                </tr>
	              </thead>
	              <tbody>
			         <c:forEach var="product" items="${productList}">
				         <tr>
					         <td><c:out value = "${product.name}"/></td>
					         <td><c:out value = "${product.price}"/></td>
					         <td><c:out value = "${product.category.name}"/></td>
					    	 <td><a class="btn btn-dark" href="user/add-to-cart-view?id=${product.id}" role="button">Add To Cart</a></td>
				         </tr>
			         </c:forEach>
	              </tbody>
	            </table>
		     </c:when>
			</c:choose>
	           
	          </div>
			</div>
          </main>
  
  
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="<c:url value="/resources/popper.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap.min.js"/>"></script>

    <!-- Icons -->
    <script src="<c:url value="/resources/feather.min.js"/>"></script>

    <script src="/resources/jquery-3.2.1.slim.min.js"></script>
</body></html>
