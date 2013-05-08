package org.packt.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.packt.client.SearchRQ;
import org.packt.engine.FlightEngine;
import org.packt.exceptions.NoCriteriaMatchException;
import org.packt.exceptions.NoFlightAvailableException;
import org.packt.supplier.SearchRS;

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
	private Provider<SearchRQ> searchRQProvider;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			SearchRQ searchRQ = searchRQProvider.get();
			searchRQ.setArrival_location(request.getParameter("destination"));
			searchRQ.setDeparture_location(request.getParameter("source"));

			Date flightDate = (Date)request.getAttribute("dateObject");
			searchRQ.setFlightDate(flightDate);

			List<SearchRS> responseList = flightEngine.processRequest(searchRQ);
			
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
