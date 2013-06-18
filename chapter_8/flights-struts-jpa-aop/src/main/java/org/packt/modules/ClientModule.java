package org.packt.modules;

import org.packt.intercerptor.LoggingInterceptor;
import org.packt.intercerptor.WrapIt;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class ClientModule extends AbstractModule {
	@Override
	protected void configure() {
		bindInterceptor(Matchers.any(),
				Matchers.annotatedWith(WrapIt.class),
				new LoggingInterceptor());
	}
}
