package org.packt.client.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.packt.client.consumer.SearchRQ;
import org.packt.client.exceptions.NoCriteriaMatchException;
import org.packt.client.exceptions.NoFlightAvailableException;
import org.packt.client.producer.CSVSupplier;
import org.packt.client.producer.SearchRS;
import org.packt.client.utils.OutputPreference;

public class FlightEngine {
	private CSVSupplier csvSupplier;
	
	public FlightEngine(CSVSupplier csvSupplier) {
		this.csvSupplier = csvSupplier;
	}

	public List<SearchRS> processRequest(SearchRQ flightSearchRQ) {
		List<SearchRS> responseList = new ArrayList<SearchRS>();	

		boolean criteriaMatch = false;

		for(SearchRS flightSearchRS : csvSupplier.getResults()){
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
			Collections.sort(responseList, new Comparator<SearchRS>() {
				@Override
				public int compare(SearchRS o1, SearchRS o2) {					
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
