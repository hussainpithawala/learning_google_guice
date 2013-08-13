package org.packt.supplier.provider;

import org.packt.exceptions.NoExcelAvailableException;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.ExcelSupplier;

public class ExcelCheckedSupplierProvider implements ExcelCheckedProvider<FlightSupplier> {

	@Override
	public FlightSupplier get() throws NoExcelAvailableException {
		return new ExcelSupplier();
	}

}
