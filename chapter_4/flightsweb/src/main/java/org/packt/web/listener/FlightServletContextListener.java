package org.packt.web.listener;

import org.packt.modules.MainModule;
import org.packt.web.servlet.FlightServlet;
import org.packt.web.servlet.IndexServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class FlightServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				install(new MainModule());
				serve("/response").with(FlightServlet.class);
				serve("/").with(IndexServlet.class);
			}
		});
	}

}
