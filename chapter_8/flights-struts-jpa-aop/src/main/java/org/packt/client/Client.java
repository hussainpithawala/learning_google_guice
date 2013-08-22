package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.intercerptor.WrapIt;
import org.packt.modules.MainModule;
import org.packt.supplier.SearchResponse;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class Client {
	@Inject
	private FlightEngine flightEngine;

	@Inject
	private SearchRequest searchRequest;

	@Inject
	private PersistService persistService;
	
	private void startService(){
		if(persistService != null){
			persistService.start();
		}
	}
	
	private void stopService(){
		if(persistService != null){
			persistService.stop();
		}
	}
	
	public static void main(String args[]){
		Injector injector = Guice.createInjector(new JpaPersistModule("flight"), new MainModule());
    	Client client = injector.getInstance(Client.class);
    	client.startService();
    	client.makeRequest();
    	client.stopService();
	}
	
	@WrapIt
	public void makeRequest() {
		
		searchRequest.setArrival_location("MAD");
		searchRequest.setDeparture_location("AMS");

		Date flightDate = null;

		try {
			flightDate = parseDate("30-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		searchRequest.setFlightDate(flightDate);

		List<SearchResponse> responseList = flightEngine.processRequest(searchRequest);

		for (SearchResponse flightSearchRS : responseList) {
			System.out.println(flightSearchRS.getArrivalLocation() + " - "
					+ flightSearchRS.getDepartureLocation());
		}

	}

}
