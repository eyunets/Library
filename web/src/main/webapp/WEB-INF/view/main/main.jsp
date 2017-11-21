<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/assests/img/book1d.jpg"
							alt="First slide">
					</div>
					<div class="item">
						<img  src="${pageContext.request.contextPath}/assests/img/book2d.jpg"
							alt="Second slide">
					</div>
					<div class="item">
						<img  src="${pageContext.request.contextPath}/assests/img/book3d.jpg"
							alt="Third slide">
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-generic"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span></a><a
					class="right carousel-control" href="#carousel-example-generic"
					data-slide="next"><span
					class="glyphicon glyphicon-chevron-right"> </span></a>
			</div>
			<div class="main-text hidden-xs">
				<div class="col-md-12 text-center">
					<h1><spring:message code="menu.name"/></h1>
					<h3><spring:message code="menu.welcome"/></h3>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="push"></div>