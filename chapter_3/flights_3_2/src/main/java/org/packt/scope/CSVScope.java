package org.packt.scope;

import org.packt.supplier.provider.CSVSupplierProvider;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

public class CSVScope implements Scope {
	@Override
	public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
		unscoped = (Provider<T>) new CSVSupplierProvider();
		return unscoped;
	}

}
