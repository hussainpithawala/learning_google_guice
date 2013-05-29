package org.packt.web.listener;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;

import ext.guice.analyst.AssistInstallModule;

public class FlightServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		
		return Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				install(new JpaPersistModule("flight"));
				install(new Struts2GuicePluginModule());
				install(new AssistInstallModule("org.packt.modules"));
				bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
		        filter("/*").through(StrutsPrepareAndExecuteFilter.class);
		        filter("/*").through(PersistFilter.class);
			}
		});
	}
}
