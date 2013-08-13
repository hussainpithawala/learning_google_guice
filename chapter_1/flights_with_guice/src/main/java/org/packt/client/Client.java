package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.supplier.CSVSupplier;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.SearchResponse;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Names;

public class Client 
{
    public static void main( String[] args )
    {
    	/**
    	 * Guice Bootstrapping code
    	 */
        class CSVSupplierModule extends AbstractModule{
    		@Override
    		public void configure() {
    			bind(String.class).annotatedWith(Names.named("csvPath")).toInstance("./flightCSV");
    			bind(FlightSupplier.class).to(CSVSupplier.class);
    		}
        }
        
    	Injector injector = Guice.createInjector(new CSVSupplierModule());
    	Client client = injector.getInstance(Client.class);
    	
    	client.makeRequest();
    }
    
    public Client(){
    }
    
    @Inject
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
