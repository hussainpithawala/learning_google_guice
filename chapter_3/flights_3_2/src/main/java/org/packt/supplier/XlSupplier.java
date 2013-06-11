package org.packt.supplier;

import java.util.Set;

import org.packt.exceptions.NoXlAvailableException;

public class XlSupplier implements FlightSupplier {

	public XlSupplier() throws NoXlAvailableException{
		throw new NoXlAvailableException();
	}
	
	@Override
	public Set<SearchRS> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
