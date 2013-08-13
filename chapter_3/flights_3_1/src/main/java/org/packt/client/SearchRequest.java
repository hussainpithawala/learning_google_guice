package org.packt.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.packt.utils.OutputPreference;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class SearchRequest {
	private String departureLocation;
	private String arrivalLocation;
	private Date flightDate;
	private Set<OutputPreference> preferences = new HashSet<OutputPreference>();
	
	@AssistedInject
	public SearchRequest(@Assisted("depLoc") String departureLocation,@Assisted("arrivLoc") String arrivalLocation,@Assisted Date flightDate){
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.flightDate = flightDate;
	}
	
	public Set<OutputPreference> getPreferences() {
		return preferences;
	}

	public SearchRequest() {
		this.preferences.add(OutputPreference.FARE);
	}

	public String getDeparture_location() {
		return departureLocation;
	}

	public void setDeparture_location(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrival_location() {
		return arrivalLocation;
	}

	public void setArrival_location(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

}

