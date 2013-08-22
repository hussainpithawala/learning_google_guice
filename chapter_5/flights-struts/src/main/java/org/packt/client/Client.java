package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.supplier.SearchResponse;

import com.google.inject.Inject;

public class Client {
	@Inject
	private FlightEngine flightEngine;

	@Inject
	private SearchRequest searchRequest;

	public void makeRequest() {

		searchRequest.setArrival_location("LHR");
		searchRequest.setDeparture_location("FRA");

		Date flightDate = null;

		try {
			flightDate = parseDate("20-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		searchRequest.setFlightDate(flightDate);

		List<SearchResponse> responseList = flightEngine.processRequest(searchRequest);

		for (SearchResponse flightSearchRS : responseList) {
			System.out.println(flightSearchRS.getArrivalLocation() + " - "
					+ flightSearchRS.getDepartureLocation());
		}

	}

}
