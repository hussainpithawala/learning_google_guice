package org.packt.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.packt.client.SearchRequest;
import org.packt.exceptions.NoCriteriaMatchException;
import org.packt.exceptions.NoFlightAvailableException;
import org.packt.exceptions.NoExcelAvailableException;
import org.packt.scope.InScope;
import org.packt.supplier.CSV;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.SearchResponse;
import org.packt.supplier.provider.ExcelCheckedProvider;
import org.packt.utils.OutputPreference;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class FlightEngine {
	
	@Inject
	@CSV
	@InScope
	private Provider<FlightSupplier> csvSupplierProvider;

	@Inject
	private ExcelCheckedProvider<FlightSupplier> excelCheckedProvider;
		
	private Set<FlightSupplier> extraSuppliers;

	@Inject
	public void setExtraSuppliers(Set<FlightSupplier> extraSuppliers) {
		this.extraSuppliers = extraSuppliers;
	}

	public Set<FlightSupplier> getExtraSuppliers() {
		return extraSuppliers;
	}

	
	private Set<String> messages;

	public Set<String> getMessages() {
		return messages;
	}

	@Inject
	public void setMessages(Set<String> messages) {
		this.messages = messages;
	}
	
	public List<SearchResponse> processRequest(SearchRequest flightSearchRQ) {
		List<SearchResponse> responseList = new ArrayList<SearchResponse>();	

		boolean criteriaMatch = false;
		
		try {
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
			
			excelCheckedProvider.get().getResults();
//			flightSupplier.getResults();
			
		} catch (NoExcelAvailableException e) {
			e.printStackTrace();
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
