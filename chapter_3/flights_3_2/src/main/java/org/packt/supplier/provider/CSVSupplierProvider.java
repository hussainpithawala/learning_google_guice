package org.packt.supplier.provider;

import java.io.File;

import org.packt.supplier.CSVSupplier;

import com.google.inject.Provider;

public class CSVSupplierProvider implements Provider<CSVSupplier> {
	@Override
	public CSVSupplier get() {
		CSVSupplier csvSupplier = new CSVSupplier();
		csvSupplier.setCsvFolder(new File("./flightCSV"));
		return csvSupplier;
	}
}
