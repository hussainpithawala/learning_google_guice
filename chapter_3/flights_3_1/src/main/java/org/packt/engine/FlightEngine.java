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
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class FlightEngine {
	
	@Inject
	@CSV
	private Provider<FlightSupplier> csvSupplierProvider;
	
	private FlightSupplier xmlFlightSupplier;
	
	private FlightSupplier jsonSupplier;

	@Inject
	public void setJsonSupplier(@Named("jsonSupplier")FlightSupplier jsonSupplier) {
		this.jsonSupplier = jsonSupplier;
	}

	public FlightSupplier getJsonSupplier() {
		return jsonSupplier;
	}

	public FlightSupplier getXmlFlightSupplier() {
		return xmlFlightSupplier;
	}
	
	@Inject
	public void setXmlFlightSupplier(@Named("xmlSupplier")FlightSupplier xmlFlightSupplier) {
		this.xmlFlightSupplier = xmlFlightSupplier;
	}
	
	public List<SearchResponse> processRequest(SearchRequest flightSearchRQ) {
		List<SearchResponse> responseList = new ArrayList<SearchResponse>();	

		boolean criteriaMatch = false;

		for(SearchResponse flightSearchRS : csvSupplierProvider.get().getResults()){
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
