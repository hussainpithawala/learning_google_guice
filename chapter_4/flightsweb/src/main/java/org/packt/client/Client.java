package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.modules.MainModule;
import org.packt.supplier.SearchRS;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Client 
{
    public static void main( String[] args )
    {
        
    	Injector injector = Guice.createInjector(new MainModule());
    	Client client = injector.getInstance(Client.class);
    	client.makeRequest();
    }
    
    public Client(){
    }
    
    @Inject
    private FlightEngine flightEngine;
    
    @Inject
    private SearchRQ searchRQ;
    
    public void makeRequest(){
		
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
