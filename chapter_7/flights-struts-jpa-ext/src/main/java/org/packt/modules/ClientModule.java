package org.packt.modules;

import org.packt.client.SearchRQFactory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ClientModule extends AbstractModule {
	@Override
	protected void configure() {
		FactoryModuleBuilder fb =  new FactoryModuleBuilder();		
		install(fb.build(SearchRQFactory.class));
	}
}
