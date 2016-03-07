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

	<div class="container" role="main">

		<a href="/" class="btn btn-lg btn-success pull-right">Back</a>
		
		<div class="page-header">
			<h1>${booking.performer.name}</h1>
			<h2>${venue.name}</h2>
			<h3>${venue.city}</h3>
			<h4>${booking.open}</h4>
		</div>
		<div>
			<p>${booking.performer.description}</p>
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
