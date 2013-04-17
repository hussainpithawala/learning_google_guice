package org.packt;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.packt.client.SearchRQ;
import org.packt.engine.FlightEngine;
import org.packt.supplier.SearchRS;

public class FlightEngineTest {
	FlightEngine flightEngine = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessRequest() {
		FlightEngine flightEngine = new FlightEngine();
		assertNotNull(flightEngine);
		
		SearchRQ searchRQ = new SearchRQ();
		
		searchRQ.setArrival_location("LHR");
		searchRQ.setDeparture_location("FRA");
		
		Date flightDate = null;
		
		try {
			flightDate = flightEngine.parseDate("20-11-2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		searchRQ.setFlightDate(flightDate);		
		
		List<SearchRS> responseList = flightEngine.processRequest(searchRQ);
		
		for(SearchRS flightSearchRS : responseList){
			System.out.println(flightSearchRS.getArrivalLocation() + " - "+flightSearchRS.getDepartureLocation());
		}
		
	}

	//@Test
	public void testProcessRequestforNoLocationMatch(){
		FlightEngine flightEngine = new FlightEngine();
		assertNotNull(flightEngine);
		
		SearchRQ flightSearchRQ = new SearchRQ();
		
		flightSearchRQ.setArrival_location("ABC");
		flightSearchRQ.setDeparture_location("DEF");
		
		Date flightDate = null;
		
		try {
			flightDate = flightEngine.parseDate("20-11-2010");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		flightSearchRQ.setFlightDate(flightDate);		
		
		List<SearchRS> responseList = flightEngine.processRequest(flightSearchRQ);
			
	}
	
	//@Test
	public void testProcessNoFlightsFound(){
		FlightEngine flightEngine = new FlightEngine();
		assertNotNull(flightEngine);
		
		SearchRQ flightSearchRQ = new SearchRQ();
		flightSearchRQ.setArrival_location("LHR");
		flightSearchRQ.setDeparture_location("FRA");
		
		Date flightDate = null;
		
		try {
			flightDate = flightEngine.parseDate("20-11-2011");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		flightSearchRQ.setFlightDate(flightDate);		
		
		List<SearchRS> responseList = flightEngine.processRequest(flightSearchRQ);
	}
}
