package org.packt.supplier.provider;

import org.packt.exceptions.NoExcelAvailableException;

import com.google.inject.throwingproviders.CheckedProvider;

public interface ExcelCheckedProvider<T> extends CheckedProvider<T>{
	T get() throws NoExcelAvailableException;
}
