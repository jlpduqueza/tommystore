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
    <link href="<c:url value="/resources/login.css"/>" rel="stylesheet">
  <style type="text/css">/* Chart.js */
@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>
 <jsp:include page="/WEB-INF/jsp/side-navbar-user.jsp" />
  <body>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>

          <h2>Cart </h2>
          
          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr>
                  <th>Product Name</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
		         <c:forEach var="cartItem" items="${cartItemMap}">
			         <tr>
				         <td><c:out value = "${cartItem.value.product.name}"/></td>
				         <td><c:out value = "${cartItem.value.quantity}"/></td>
				         <td><c:out value = "${cartItem.value.product.price}"/></td>
				         <td>
					         <a class="btn btn-dark" href="edit-product-view?id=${cartItem.value.product.id}" role="button">Edit</a>
					         <a class="btn btn-dark" href="delete-cartitem?id=${cartItem.value.product.id}" role="button">Delete</a>
				         </td>
			         </tr>
		         </c:forEach>
              </tbody>
            </table>
          </div>
          <h4>Payment </h4>
		<div class="form-group">
			<form:form action="checkout" modelAttribute="order">
		         <c:forEach var="creditCard" items="${creditCardList}">
					<div class="form-check">
					  <form:radiobutton path="creditCard.id" class="form-check-input" name="exampleRadios" id="exampleRadios3" value="${creditCard.id}"/>
					  <label class="form-check-label" for="exampleRadios3">
					    <c:out value = "Credit Card: ${creditCard.cardNumber}"/>
					  </label>
					</div>
		         </c:forEach>
		<div class="form-check">
		  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
		  <label class="form-check-label" for="exampleRadios1">
		    Cash On Delivery
		  </label>
		</div>
		  <br>	
          <h4>Shipping Address</h4>
		         <c:forEach var="shippingAddress" items="${shippingAddressList}">
					<div class="form-check">
					  <form:radiobutton path="shippingAddress.id" class="form-check-input" name="exampleRadios2" id="exampleRadios2" value="${shippingAddress.id}"/>
					  <label class="form-check-label" for="exampleRadios2">
					    <c:out value = "Shipping Address: ${shippingAddress.address1}"/>
					  </label>
					</div>
		         </c:forEach>
				  <br>	
      	      	<button type="submit" class="btn btn-dark">Checkout</button>
			</form:form>
        </div>
        </main>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/resources/jquery-3.2.1.slim.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="<c:url value="/resources/popper.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap.min.js"/>"></script>

    <!-- Icons -->
    <script src="<c:url value="/resources/feather.min.js"/>"></script>
    <script>
      feather.replace()
    </script>

</body></html>