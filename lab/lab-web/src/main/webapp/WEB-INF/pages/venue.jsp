<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<!-- Bootstrap DateTime Picker -->
<link href="${dateTimePickerCSS}" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${themeCSS}" rel="stylesheet">

</head>

<body role="document">

	<%@include file="common/navbar.jsp"%>


	<div class="container" role="main">


		<div class="page-header">
			<h1>Request a Booking</h1>
		</div>

		<form:form method="POST" commandName="venue">
			<div class="form-group">
				<label class="clear" for="name">Venue Name</label>
				<form:input path="name" />
			</div>
			<div class="form-group">
				<label class="clear" for="city">City</label>
				<form:input path="city" />
			</div>
			<div class="form-group">
				<label class="clear" for="capacity">Capacity</label>
				<form:input path="Capacity" />
			</div>
			<div class="form-group blocks">
				<label class="clear" for="accomodations">Performance Accomodations</label>
					<form:checkboxes items="${performanceTypes}" itemLabel="label"
						path="accomodations" />
			</div>

			<button class="btn btn-lg btn-success pull-right" type="submit">Submit</button>
		</form:form>
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
