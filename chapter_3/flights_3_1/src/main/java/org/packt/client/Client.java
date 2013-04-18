package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.supplier.CSV;
import org.packt.supplier.CSVSupplier;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.SearchRS;
import org.packt.supplier.XMLSupplier;
import org.packt.supplier.provider.CSVSupplierProvider;
import org.packt.utils.FlightUtils;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class Client 
{
    public static void main( String[] args )
    {
    	/**
    	 * Guice Bootstrapping code
    	 * method local classes for demonstration
    	 * only.
    	 */

        
        class FlightEngineModule extends AbstractModule{
        	@Override
    		public void configure() {
        		bind(FlightSupplier.class).
        			annotatedWith(CSV.class).
        				toProvider(CSVSupplierProvider.class);
    			bind(FlightSupplier.class).
    				annotatedWith(Names.named("xmlSupplier")).
    					toInstance(new XMLSupplier());
    		}
        }
        
        class ClientModule extends AbstractModule{
    		@Override
    		protected void configure() {
    			bind(SearchRQ.class).toInstance(new SearchRQ());
    		}
        }
        
        class FlightUtilityModule extends AbstractModule{
        	@Override
        	protected void configure(){
        		bindConstant().annotatedWith(Names.named("dateFormat")).to("dd-MM-yy");
        		requestStaticInjection(FlightUtils.class);
        	}
        }
        
    	Injector injector = Guice.createInjector(new FlightEngineModule(),
    												  new ClientModule(), 
    												    new FlightUtilityModule());
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
