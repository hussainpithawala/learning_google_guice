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

import ext.guice.analyst.AnalyzeBindingVisitor;
import ext.guice.analyst.AnalyzeMultiBindingVisitor;
import ext.guice.analyst.AssistInstallModule;

public class Client {
	@Inject
	private FlightEngine flightEngine;

	@Inject
	SearchRQFactory searchRQFactory;
	
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

		AnalyzeBindingVisitor analyzeBindingVisitor = new AnalyzeBindingVisitor();
		
		for(Binding<?> binding : injector.getBindings().values()){
			System.out.println(binding.acceptTargetVisitor(analyzeBindingVisitor));
			System.out.println(binding.acceptScopingVisitor(analyzeBindingVisitor));
			binding.acceptTargetVisitor(new AnalyzeMultiBindingVisitor());
		}
		
    	Client client = injector.getInstance(Client.class);
    	client.startService();
    	client.makeRequest();
    	client.stopService();
	}
	
	public void makeRequest() {
		

		Date flightDate = null;

		try {
			flightDate = parseDate("30-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SearchRQ searchRQ = searchRQFactory.create("AMS", "MAD", flightDate);

		List<SearchRS> responseList = flightEngine.processRequest(searchRQ);

		for (SearchRS flightSearchRS : responseList) {
			System.out.println(flightSearchRS.getArrivalLocation() + " - "
					+ flightSearchRS.getDepartureLocation());
		}

	}

}
