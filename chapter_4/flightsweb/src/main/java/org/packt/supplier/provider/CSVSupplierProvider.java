package org.packt.supplier.provider;

import java.io.File;

import org.packt.supplier.CSVSupplier;

import com.google.inject.Provider;

public class CSVSupplierProvider implements Provider<CSVSupplier> {
	private long timeStamp;

	private CSVSupplier csvSupplier;
	
	private File csvFolder;

	public void resetSupplier(){
		csvSupplier = null;
	}
	
	public void newSupplier(){
		csvFolder = new File("webapps/flightsweb/flightCSV");
		csvSupplier = new CSVSupplier();
		timeStamp = csvFolder.lastModified();
		csvSupplier.setCsvFolder(csvFolder);		
	}
	
	@Override
	public CSVSupplier get() {
		if(csvSupplier == null)
			newSupplier();
		return csvSupplier;
	}
	
	public boolean inScope(){
		return timeStamp == csvFolder.lastModified();
	}
}
