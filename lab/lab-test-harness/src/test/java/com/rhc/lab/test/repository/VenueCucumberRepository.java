package com.rhc.lab.test.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rhc.lab.dao.VenueRepository;
import com.rhc.lab.domain.Venue;

public class VenueCucumberRepository implements VenueRepository {

	private Map<String, Venue> venueRepo = new HashMap<String, Venue>();

	@Override
	public Iterable<Venue> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Venue> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Venue arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Venue> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Venue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Venue> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venue findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Venue> S save(S venue) {
		venueRepo.put(venue.getId(), venue);
		return null;
	}

	@Override
	public <S extends Venue> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venue> findByName(String name) {
		List<Venue> venues = new ArrayList<Venue>();
		for (Venue venue : venueRepo.values()) {
			if (venue.getName().equals(name)) {
				venues.add(venue);
			}
		}
		return venues;
	}

	public void clear() {
		venueRepo = new HashMap<String, Venue>();
	}
}
