package org.packt.client;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.supplier.LocalSupplier;
import org.packt.supplier.SearchRS;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;

import static org.packt.utils.FlightUtils.parseDate;

public class Client 
{
    public static void main( String[] args )
    {
    	Client client = new Client();
    	client.makeRequest();
    }
    
    public Client(){
    }
    
    private FlightEngine flightEngine;
    
    public void makeRequest(){
    	/**
    	 * Guice Bootstrapping code
    	 */
    	
    	Injector injector = Guice.createInjector(new LocalSupplierModule(),new FlightEngineModule());
    	flightEngine = injector.getInstance(FlightEngine.class);
    	
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
    
    class LocalSupplierModule implements Module{
		@Override
		public void configure(Binder binder) {
			binder.bindConstant().annotatedWith(Names.named("csvPath")).to("./flightCSV/");
		}
    }
    
    class FlightEngineModule implements Module{
    	@Override
		public void configure(Binder binder) {
			binder.bind(LocalSupplier.class).toInstance(new LocalSupplier());
		}
    }
}
