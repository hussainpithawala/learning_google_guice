package org.packt.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.packt.utils.OutputPreference;

public class SearchRequest {
	private String departure_location;
	private String arrival_location;
	private Date flightDate;
	private Set<OutputPreference> preferences = new HashSet<OutputPreference>();
	
	public Set<OutputPreference> getPreferences() {
		return preferences;
	}

	public SearchRequest() {
		this.preferences.add(OutputPreference.FARE);
	}

	public String getDeparture_location() {
		return departure_location;
	}

	public void setDeparture_location(String departureLocation) {
		departure_location = departureLocation;
	}

	public String getArrival_location() {
		return arrival_location;
	}

	public void setArrival_location(String arrivalLocation) {
		arrival_location = arrivalLocation;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

}
