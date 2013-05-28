package org.packt.web.listener;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;

import org.packt.modules.MainModule;
import org.packt.web.filter.FlightFilter;
import org.packt.web.filter.FlightSearchFilter;
import org.packt.web.servlet.FlightServe;
import org.packt.web.servlet.FlightServlet;
import org.packt.web.servlet.IndexServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class FlightServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		
		return Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				install(new MainModule());
				
				filter("/response").through(Key.get(Filter.class,FlightFilter.class));
				bind(Filter.class).annotatedWith(FlightFilter.class).to(FlightSearchFilter.class).in(Singleton.class);

				serve("/response").with(Key.get(HttpServlet.class, FlightServe.class));
				bind(HttpServlet.class).annotatedWith(FlightServe.class).to(FlightServlet.class);
				
				serve("/").with(IndexServlet.class);
				
			}
		});
	}

}
