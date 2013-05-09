package org.packt.web.filter;

import static org.packt.utils.FlightUtils.parseDate;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.packt.client.RequestCounter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.RequestParameters;

@FlightFilter
public class FlightSearchFilter implements Filter{
	@Inject
	@RequestParameters
	private Provider<Map<String, String[]>> reqParamMapProvider;
	
	@Inject
	private Provider<RequestCounter> requestCountProvider;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Intent of this filter is to verify the request and return
		// an exception to the response page.
		boolean valid = true;
		
		try {
			Map<String,String[]> requestMap = reqParamMapProvider.get();
			
			String destination = requestMap.get("destination")[0];
			String source = requestMap.get("source")[0];
			String date = requestMap.get("date")[0];
			
			if(destination.length() != 3 || source.length() != 3){
				valid = false;
			}
				
			Date flightDate = null;

			flightDate = parseDate(date);
			
			request.setAttribute("dateObject",flightDate);
			
		} catch (ParseException e) {
			valid = false;
		}

		if(valid){
			RequestCounter requestCounter = requestCountProvider.get();
			int lastCount = requestCounter.getSearches();
			requestCounter.setSearches(++lastCount);
			chain.doFilter(request, response);
		}else{
			((HttpServletRequest)request).getSession().setAttribute("isException", true);
			((HttpServletRequest)request).getSession().setAttribute("exceptionMessage", "Invalid request parameters");
			request.getRequestDispatcher("response.jsp").forward(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
