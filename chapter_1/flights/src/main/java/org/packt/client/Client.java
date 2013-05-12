package org.packt.client;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.client.consumer.SearchRQ;
import org.packt.client.engine.FlightEngine;
import org.packt.client.producer.CSVSupplier;
import org.packt.client.producer.SearchRS;

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
    	    	
    	SearchRQ searchRQ = new SearchRQ();
		
		searchRQ.setArrival_location("LHR");
		searchRQ.setDeparture_location("FRA");
		
		Date flightDate = null;
		
		try {
			flightDate = parseDate("20-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		searchRQ.setFlightDate(flightDate);		
		
		List<SearchRS> responseList = flightEngine.processRequest(searchRQ);
		
		for(SearchRS flightSearchRS : responseList){
			System.out.println(flightSearchRS.getArrivalLocation() + " - "+flightSearchRS.getDepartureLocation());
		}
    	
    }
}
