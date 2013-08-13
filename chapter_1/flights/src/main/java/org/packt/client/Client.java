package org.packt.client;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.client.consumer.SearchRequest;
import org.packt.client.engine.FlightEngine;
import org.packt.client.producer.CSVSupplier;
import org.packt.client.producer.SearchResponse;

import static org.packt.client.utils.FlightUtils.parseDate;

public class Client 
{
    public static void main( String[] args )
    {
    	CSVSupplier csvSupplier = new CSVSupplier("./flightCSV/");
    	
    	FlightEngine flightEngine = new FlightEngine(csvSupplier);
    	
    	Client client = new Client(flightEngine);
    	
    	client.makeRequest();
    }
    
    public Client(FlightEngine flightEngine){
    	this.flightEngine = flightEngine;
    }
    
    private FlightEngine flightEngine;
    
    public void makeRequest(){
    	    	
    	SearchRequest searchRequest = new SearchRequest();
		
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
		
		for(SearchResponse flightSearchRS : responseList){
			System.out.println(flightSearchRS.getArrivalLocation() + " - "+flightSearchRS.getDepartureLocation());
		}
    	
    }
}
