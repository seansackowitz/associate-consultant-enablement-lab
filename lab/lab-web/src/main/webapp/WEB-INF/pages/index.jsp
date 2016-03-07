<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<spring:url value="/resources/css/theme.css" var="themeCSS" />
<spring:url
	value="/resources/bootstrap-3.3.4-dist/css/bootstrap.min.css"
	var="bootstrapCoreCSS" />
<spring:url
	value="/resources/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css"
	var="bootstrapThemeCSS" />
<spring:url value="/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"
	var="bootstrapJS" />
<spring:url value="/resources/images/favicon.ico" var="favicon" />



<title>${message}</title>
<!-- Favicon -->
<link rel="icon" href="${favicon}">
<!-- Bootstrap core CSS -->
<link href="${bootstrapCoreCSS}" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${bootstrapThemeCSS}" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${themeCSS}" rel="stylesheet">

</head>

<body role="document">

	<%@include file="common/navbar.jsp"%>

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>${message}</h1>
			<p>This is our website where performers can request bookings at
				Venues and Venues can add themselves for performances.</p>
		</div>

		<ul class="nav nav-tabs nav-justified">
			<li><a href="#venues" data-toggle="tab">Venues</a></li>
			<li><a href="#performances" data-toggle="tab">Upcoming
					Performances</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane active" id="venues">
				<div class="list-group">
					<div class="row top5">
						<a href="/venue" class="btn btn-lg btn-success pull-right">Add
							Venue</a>
					</div>
					<div class="row top5">
						<c:forEach items="${venues}" var="venue">
							<a href="/bookingRequest?venue=${venue.name}" class="list-group-item">
								<h4 class="list-group-item-heading">${venue.name}</h4>
								<p class="list-group-item-text">${venue.city}</p>
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="performances">
				<div class="list-group">
					<div class="row top5">
						<a href="/bookingRequest" class="btn btn-lg btn-success pull-right">Request
							Booking</a>
					</div>
					<div class="row top5">
						<c:forEach items="${bookings}" var="booking">
							<a href="/booking/${booking.id}" class="list-group-item">
								<h4 class="list-group-item-heading">${booking.performer.name}</h4>
								<p class="list-group-item-text">${booking.venueName}&nbsp;-&nbsp;${booking.open}</p>
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${bootstrapJS}"></script>
</body>
</html>
