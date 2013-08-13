package org.packt.supplier;

import java.util.Set;

import org.packt.exceptions.NoExcelAvailableException;

public class ExcelSupplier implements FlightSupplier {

	public ExcelSupplier() throws NoExcelAvailableException{
		throw new NoExcelAvailableException();
	}
	
	@Override
	public Set<SearchResponse> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
