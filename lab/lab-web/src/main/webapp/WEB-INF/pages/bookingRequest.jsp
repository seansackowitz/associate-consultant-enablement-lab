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
<spring:url
	value="/resources/bootstrap-3.3.4-dist/js/bootstrap-datetimepicker.min.js"
	var="dateTimePickerJS" />
<spring:url
	value="/resources/bootstrap-3.3.4-dist/js/bootstrap-datetimepicker.pt-BR.js"
	var="dateTimePickerLocaleJS" />
<spring:url
	value="/resources/bootstrap-3.3.4-dist/css/bootstrap-datetimepicker.min.css"
	var="dateTimePickerCSS" />
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

		<form:form method="POST" commandName="bookingRequest">
			<div class="form-group">
				<label for="venues">Venue</label>
				<div class="dropdown">
					<form:select path="venueName">
						<form:options items="${venues}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="openTime">Opening Time</label>
				<div class="input-append date form_datetime">
					<form:input path="open" />
					<span class="add-on"><i class="icon-th"></i></span>
				</div>
			</div>
			<div class="form-group">
				<label for="closeTime">Closing Time</label>
				<div class="input-append date form_datetime">
					<form:input path="close" />
					<span class="add-on"><i class="icon-th"></i></span>
				</div>
			</div>
			<div class="form-group">
				<label class="clear" for="name">Performer Name</label>
				<form:input path="performer.name" placeholder="Performer Name" />
			</div>
			<div class="form-group blocks">
				<label class="clear">Performance Type (Select One)</label>
				<form:radiobuttons path="performer.type" items="${performanceTypes}" itemLabel="label"  />
			</div>
			<div class="form-group">
				<label for="description">Description</label>
				<form:textarea class="form-control" rows="5" id="description"
					path="performer.description" placeholder="Performer Description..." />
			</div>
			<button class="btn btn-lg btn-success pull-right" type="submit">Request</button>
		</form:form>
	</div>


	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${bootstrapJS}"></script>
	<script src="${dateTimePickerJS}"></script>
	<script src="${dateTimePickerLocaleJS}"></script>
	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			format : "dd-MM-yyyy hh:ii"
		});
	</script>
</body>
</html>
