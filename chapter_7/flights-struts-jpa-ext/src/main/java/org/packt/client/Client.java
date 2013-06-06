package org.packt.client;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.packt.engine.FlightEngine;
import org.packt.supplier.SearchRS;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import ext.guice.analyst.AnalyzeBindingVisitorImpl;
import ext.guice.analyst.AnalyzeMultiBindingVisitorImpl;
import ext.guice.analyst.AssistInstallModule;

public class Client {
	@Inject
	private FlightEngine flightEngine;

	@Inject
	private SearchRQ searchRQ;

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
		Injector injector = Guice.createInjector(new JpaPersistModule("flight"), new AssistInstallModule("org.packt.modules"));

		AnalyzeBindingVisitorImpl analyzeBindingVisitorImpl = new AnalyzeBindingVisitorImpl();
		
		for(Binding<?> binding : injector.getBindings().values()){
			System.out.println(binding.acceptTargetVisitor(analyzeBindingVisitorImpl));
			System.out.println(binding.acceptScopingVisitor(analyzeBindingVisitorImpl));
			System.out.println(binding.acceptTargetVisitor(new AnalyzeMultiBindingVisitorImpl()));
		}
		
    	Client client = injector.getInstance(Client.class);
    	client.startService();
    	client.makeRequest();
    	client.stopService();
	}
	
	public void makeRequest() {
		
		searchRQ.setArrival_location("MAD");
		searchRQ.setDeparture_location("AMS");

		Date flightDate = null;

		try {
			flightDate = parseDate("30-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		searchRQ.setFlightDate(flightDate);

		List<SearchRS> responseList = flightEngine.processRequest(searchRQ);

		for (SearchRS flightSearchRS : responseList) {
			System.out.println(flightSearchRS.getArrivalLocation() + " - "
					+ flightSearchRS.getDepartureLocation());
		}

	}

}
