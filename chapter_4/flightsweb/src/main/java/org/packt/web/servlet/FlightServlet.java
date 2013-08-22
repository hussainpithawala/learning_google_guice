package org.packt.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.packt.client.SearchRequest;
import org.packt.engine.FlightEngine;
import org.packt.exceptions.NoCriteriaMatchException;
import org.packt.exceptions.NoFlightAvailableException;
import org.packt.supplier.SearchResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class FlightServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FlightEngine flightEngine;
	
	@Inject
	private Provider<SearchRequest> searchRequestProvider;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			SearchRequest searchRequest = searchRequestProvider.get();
			searchRequest.setArrival_location(request.getParameter("destination"));
			searchRequest.setDeparture_location(request.getParameter("source"));

			Date flightDate = (Date)request.getAttribute("dateObject");
			searchRequest.setFlightDate(flightDate);

			List<SearchResponse> responseList = flightEngine.processRequest(searchRequest);
			
			request.getSession().setAttribute("responseList", responseList);
			request.getSession().setAttribute("isException", false);
			
		} catch(NoFlightAvailableException e){
			request.getSession().setAttribute("isException", true);
			request.getSession().setAttribute("exceptionMessage", e.getMessage());
		} catch(NoCriteriaMatchException e){
			request.getSession().setAttribute("isException", true);
			request.getSession().setAttribute("exceptionMessage", e.getMessage());
		}finally{
			try {
				request.getRequestDispatcher("response.jsp").forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}
}
