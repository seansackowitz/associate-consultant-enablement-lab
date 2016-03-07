package com.rhc.lab.controller;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhc.lab.dao.BookingRepository;
import com.rhc.lab.domain.Booking;

@RestController
public class LabRestController {

	@Resource(name = "bookingDao")
	private BookingRepository bookingDao;

	private ObjectMapper om;

	public LabRestController() {
		om = new ObjectMapper();
	}

	/**
	 * GET method to get all bookings as JSON
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bookings", method = RequestMethod.GET)
	public String newbookingRequest(ModelMap model) {

		List<Booking> bookings = (List<Booking>) bookingDao.findAll();
		if (bookings != null) {
			try {
				return om.writeValueAsString(bookings);
			} catch (Exception e) {
				return e.getMessage();
			}
		} else {
			return "{\"message\": \"No bookings found.\"}";
		}
	}

}
