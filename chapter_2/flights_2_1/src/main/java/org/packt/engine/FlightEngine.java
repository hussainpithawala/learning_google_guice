package org.packt.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.packt.client.SearchRequest;
import org.packt.exceptions.NoCriteriaMatchException;
import org.packt.exceptions.NoFlightAvailableException;
import org.packt.supplier.CSV;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.SearchResponse;
import org.packt.utils.OutputPreference;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class FlightEngine {
	private int maxResults;
	
	private FlightSupplier csvFlightSupplier;
	
	private FlightSupplier xmlFlightSupplier;
	
	public int getMaxResults() {
		return maxResults;
	}

	@Inject
	public void setMaxResults(@Named("maxResults")int maxResults) {
		this.maxResults = maxResults;
	}

	public FlightSupplier getXmlFlightSupplier() {
		return xmlFlightSupplier;
	}

	@Inject
	public void setXmlFlightSupplier(@Named("xmlSupplier")FlightSupplier xmlFlightSupplier) {
		this.xmlFlightSupplier = xmlFlightSupplier;
	}

	@Inject
	public FlightEngine(@CSV FlightSupplier flightSupplier) {
		this.csvFlightSupplier = flightSupplier;
	}
	
	public FlightSupplier getFlightSupplier() {
		return csvFlightSupplier;
	}

	public void setFlightSupplier(FlightSupplier flightSupplier) {
		this.csvFlightSupplier = flightSupplier;
	}

	public List<SearchResponse> processRequest(SearchRequest flightSearchRQ) {
		List<SearchResponse> responseList = new ArrayList<SearchResponse>();	

		boolean criteriaMatch = false;

		for(SearchResponse flightSearchRS : csvFlightSupplier.getResults()){
			if(flightSearchRS.getArrivalLocation().equals(
					flightSearchRQ.getArrival_location())
					||
				flightSearchRS.getDepartureLocation().equals(flightSearchRQ.getDeparture_location()))
				criteriaMatch = true;
			
			if (flightSearchRS.getArrivalLocation().equals(
					flightSearchRQ.getArrival_location())
					&&
				flightSearchRS.getDepartureLocation().equals(flightSearchRQ.getDeparture_location())
					&&
				(flightSearchRS.getValidDate().compareTo(flightSearchRQ.getFlightDate()) == 0)
			) {
				responseList.add(flightSearchRS);
			}
		}
		
		if(!criteriaMatch)
			throw new NoCriteriaMatchException("Depart/Arrival Codes don't match our records");
		if(responseList.size() == 0)
			throw new NoFlightAvailableException("No flights found for given specified date");
		
		if(flightSearchRQ.getPreferences().contains(OutputPreference.DURATION)){
			Collections.sort(responseList, new Comparator<SearchResponse>() {
				@Override
				public int compare(SearchResponse o1, SearchResponse o2) {					
					int result = 0;
					
					if(o1.getFlightDuration() > o2.getFlightDuration())
						result = 1;
					else if(o1.getFlightDuration() < o2.getFlightDuration())
						result = -1;	
					
					return result;
				}
			});
		}
		
		return responseList;
	}	
}
