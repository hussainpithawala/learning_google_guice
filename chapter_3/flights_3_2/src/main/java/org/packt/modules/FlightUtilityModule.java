package org.packt.modules;

import org.packt.utils.FlightUtils;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class FlightUtilityModule extends AbstractModule {
	@Override
	protected void configure(){
		bindConstant().annotatedWith(Names.named("dateFormat")).to("dd-MM-yy");
		requestStaticInjection(FlightUtils.class);
	}
}
