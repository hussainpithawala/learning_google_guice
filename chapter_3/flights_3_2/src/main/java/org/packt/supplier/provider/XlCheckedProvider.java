package org.packt.supplier.provider;

import org.packt.exceptions.NoXlAvailableException;

import com.google.inject.throwingproviders.CheckedProvider;

public interface XlCheckedProvider<T> extends CheckedProvider<T>{
	T get() throws NoXlAvailableException;
}
