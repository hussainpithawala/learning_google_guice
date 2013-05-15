package org.packt.web.listener;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.packt.modules.MainModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;

public class FlightServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		
		return Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				install(new Struts2GuicePluginModule());
				install(new MainModule());
				bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
		        filter("/*").through(StrutsPrepareAndExecuteFilter.class);
			}
		});
	}
}
