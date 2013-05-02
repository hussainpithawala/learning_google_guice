package org.packt.supplier.provider;

import java.io.File;
import java.util.Calendar;

import org.packt.scope.InScope;
import org.packt.supplier.CSVSupplier;

import com.google.inject.Provider;

public class CSVSupplierProvider implements Provider<CSVSupplier> {
	private long timeStamp;

	private CSVSupplier csvSupplier;
	
	private File csvFolder;
	
	public void newSupplier(){
		csvFolder = new File("./flightCSV");
		csvSupplier = new CSVSupplier();
		timeStamp = csvFolder.lastModified();
		csvSupplier.setCsvFolder(csvFolder);		
	}
	
	public CSVSupplier get() {
		if(csvSupplier == null || !inScope())
			newSupplier();
		return csvSupplier;
	}
	
	public boolean inScope(){
		Long currentTimestamp = Calendar.getInstance().getTime().getTime();
		return ((currentTimestamp - timeStamp) > 6000 ? false : true);	// Let it go out of scope after 60 seconds.
	}
}
