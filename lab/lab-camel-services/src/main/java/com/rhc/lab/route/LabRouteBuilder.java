package com.rhc.lab.route;

import javax.annotation.Resource;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;

import com.rhc.lab.domain.BookingRequest;
import com.rhc.lab.domain.Login;
import com.rhc.lab.domain.Venue;
import com.rhc.lab.kie.service.impl.LocalStatelessDecisionService;
import com.rhc.lab.service.BookingRequestService;
import com.rhc.lab.service.LoginService;

public class LabRouteBuilder extends RouteBuilder {

	@Autowired
	BookingRequestService requestService;
	LoginService loginRequestService;

	@Resource(name = "localDecisionService")
	LocalStatelessDecisionService localDecisionService;

	@Override
	public void configure() throws Exception {

		restConfiguration().component("restlet").host("localhost").port("8081")
				.enableCORS(true).bindingMode(RestBindingMode.json);

		rest("/login")
				.verb("options")
				.route()
				.setHeader("Access-Control-Allow-Origin", constant("*"))
				.setHeader(
						"Access-Control-Allow-Methods",
						constant("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH"))
				.setHeader(
						"Access-Control-Allow-Headers",
						constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
				.setHeader("Allow", constant("GET, OPTIONS, POST, PATCH"));

		rest("/login").post().consumes("application/json").type(Login.class)
				.produces("application/json").to("direct:login");
		// get().param().type(RestParamType.query).name("email")
		// 	.endParam().param().type(RestParamType.query).name("password")
		// 	.consumes("application/json").type(Login.class)
		// 	.produces("application/json").to("direct:newLogin");
		//
		rest("/bookings")
				.verb("options")
				.route()
				.setHeader("Access-Control-Allow-Origin", constant("*"))
				.setHeader(
						"Access-Control-Allow-Methods",
						constant("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH"))
				.setHeader(
						"Access-Control-Allow-Headers",
						constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
				.setHeader("Allow", constant("GET, OPTIONS, POST, PATCH"));

		rest("/bookings").get().param().type(RestParamType.query)
				.name("startDate").endParam().param().type(RestParamType.query)
				.name("endDate").endParam().param().type(RestParamType.query)
				.name("venue").endParam().param().type(RestParamType.query)
				.name("performer").endParam().to("direct:getBookings").post()
				.consumes("application/json").type(BookingRequest.class)
				.produces("application/json").to("direct:newBooking")
				.put("/{id}").consumes("application/json")
				.type(BookingRequest.class).produces("application/json")
				.to("direct:updateBooking").delete("/{id}")
				.to("direct:deleteBooking").get("/{id}")
				.to("direct:getBookingById");

		rest("/venues")
				.verb("options")
				.route()
				.setHeader("Access-Control-Allow-Origin", constant("*"))
				.setHeader(
						"Access-Control-Allow-Methods",
						constant("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH"))
				.setHeader(
						"Access-Control-Allow-Headers",
						constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
				.setHeader("Allow", constant("GET, OPTIONS, POST, PATCH"));

		rest("/venues").get().param().type(RestParamType.query).name("city")
				.endParam().param().type(RestParamType.query).name("capacity")
				.endParam().param().type(RestParamType.query).name("type")
				.endParam().to("direct:getVenues").post()
				.consumes("application/json").type(Venue.class)
				.produces("application/json").to("direct:newVenue")
				.get("/{id}").to("direct:getVenueById").delete("/{id}")
				.to("direct:deleteVenue").put("/{id}")
				.consumes("application/json").type(Venue.class)
				.to("direct:updateVenue");

		rest("/performancetypes").get().to("direct:getPerformanceTypes");

		// booking routes
		from("direct:getBookings").log(LoggingLevel.INFO, "Getting Bookings")
				.bean(requestService, "getBookings")
				.log(LoggingLevel.INFO, "Body : ${body}").to("mock:end");

		from("direct:newBooking")
				.log(LoggingLevel.INFO, "New Booking Request")
				.bean(requestService, "buildSession")
				.setHeader("responseClazz")
				.constant("com.rhc.lab.domain.BookingResponse")
				.bean(localDecisionService,
						"executeForClass(${body}, bookingProcess, ${header.responseClazz})")
				.bean(requestService, "saveBooking").to("mock:end");

		// TODO actually update the Booking
		from("direct:updateBooking").transform().constant("update Booking");

		from("direct:deleteBooking")
				.log(LoggingLevel.INFO, "Delete Booking ${header.id}")
				.bean(requestService, "deleteBooking").to("mock:end");

		from("direct:getBookingById")
				.log(LoggingLevel.INFO, "Get Booking by Id id : ${header.id}")
				.bean(requestService, "getBookingById").to("mock:end");

		// login route
		from("direct:login")
				.log(LoggingLevel.INFO, "Post Login by email")
				.bean(loginService, "getLoginByEmail").to("mock:end");

		// venue routes
		from("direct:getVenues").log(LoggingLevel.INFO, "Getting Venues")
				.bean(requestService, "getVenues").to("mock:end");
		from("direct:newVenue").log(LoggingLevel.INFO, "New Venue")
				.bean(requestService, "saveVenue").to("mock:end");

		// TODO: actually update a Venue
		from("direct:updateVenue").transform().constant("update venue");

		from("direct:deleteVenue")
				.log(LoggingLevel.INFO, "Delete Venue ${header.id}")
				.bean(requestService, "deleteVenue").to("mock:end");

		from("direct:getVenueById").log(LoggingLevel.INFO, "Id ${header.id}")
				.bean(requestService, "getVenueById").to("mock:end");

		from("direct:builder").log("Hello World").transform()
				.constant("hello world").to("mock:end");

		from("direct:getPerformanceTypes")
				.log(LoggingLevel.INFO, "get performance Types")
				.bean(requestService, "getPerformanceTypes").to("mock:end");
	}

}
