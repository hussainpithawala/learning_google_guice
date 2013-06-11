package org.packt.supplier.provider;

import org.packt.exceptions.NoXlAvailableException;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.XlSupplier;

public class XlCheckedSupplierProvider implements XlCheckedProvider<FlightSupplier> {

	@Override
	public FlightSupplier get() throws NoXlAvailableException {
		return new XlSupplier();
	}

}
