package org.packt.modules;

import org.packt.scope.CSVScope;
import org.packt.scope.InScope;

import com.google.inject.AbstractModule;

public class CSVScopeModule extends AbstractModule {
	@Override
	protected void configure() {
		bindScope(InScope.class, new CSVScope());
	}
}
