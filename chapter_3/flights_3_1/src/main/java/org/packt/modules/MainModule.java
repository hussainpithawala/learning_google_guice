package org.packt.modules;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new FlightEngineModule());
		install(new FlightUtilityModule());
		install(new ClientModule());
	}
}
