package org.packt.modules;

import org.packt.client.SearchRQ;

import com.google.inject.AbstractModule;

public class ClientModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchRQ.class).toInstance(new SearchRQ());
	}
}
