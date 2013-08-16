package org.packt.supplier;

import java.util.Set;

import org.packt.supplier.provider.CSVSupplierProvider;

import com.google.inject.ProvidedBy;

public interface FlightSupplier {
	Set<SearchResponse> getResults();
}
