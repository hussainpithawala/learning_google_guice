package org.packt.supplier.provider;

import java.io.File;

import org.packt.supplier.CSVSupplier;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class CSVSupplierProvider implements Provider<CSVSupplier> {
	
	private String csvFolderPath;
	
	@Inject
	public CSVSupplierProvider(@Named("csvFolderPath")String csvFolderPath){
		this.csvFolderPath = csvFolderPath;
	}
	
	@Override
	public CSVSupplier get() {
		CSVSupplier csvSupplier = new CSVSupplier();
		csvSupplier.setCsvFolder(new File(csvFolderPath));
		return csvSupplier;
	}
	
}
