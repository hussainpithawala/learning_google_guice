package org.packt.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.packt.client.Client;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FlightServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Client client;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		client.makeRequest();

		try {
			request.getRequestDispatcher("response.jsp").forward(request,
					response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
