package org.packt.action;

import static org.packt.utils.FlightUtils.parseDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.packt.client.RequestCounter;
import org.packt.client.SearchRQ;
import org.packt.client.SearchRQFactory;
import org.packt.engine.FlightEngine;
import org.packt.supplier.SearchRS;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ResultPath(value="/")
public class FlightSearchAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	private String source;
	private String destination;
	private String date;
	
	@Inject
	private RequestCounter requestCounter;
	
	@Inject
	private FlightEngine flightEngine;

	@Inject
	private SearchRQFactory searchRQFactory;
	
	private List<SearchRS> results;
	
	@Action(value="Search",results={@Result(name="success",location="response.jsp")})
	public String execute() {
		Date flightDate = null;

		try {
			flightDate = parseDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SearchRQ flightSearchRQ = searchRQFactory.create(getSource(), getDestination(), flightDate);
		// Updating the request counter;
		requestCounter.setSearches(requestCounter.getSearches() + 1);
		results = flightEngine.processRequest(flightSearchRQ);
		return SUCCESS;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void validate(){
		
	}

	public List<SearchRS> getResults() {
		return results;
	}

	public void setResults(List<SearchRS> results) {
		this.results = results;
	}

	public RequestCounter getRequestCounter() {
		return requestCounter;
	}
}
