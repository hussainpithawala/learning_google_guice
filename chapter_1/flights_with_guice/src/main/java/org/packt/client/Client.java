package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.packt.engine.FlightEngine;
import org.packt.supplier.CSVSupplier;
import org.packt.supplier.SearchRS;
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
    			bindConstant().annotatedWith(Names.named("csvPath")).to("./flightCSV/");
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
