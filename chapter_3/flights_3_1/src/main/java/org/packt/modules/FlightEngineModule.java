package org.packt.modules;

import org.packt.supplier.CSV;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.JSONSupplier;
import org.packt.supplier.XMLSupplier;
import org.packt.supplier.provider.CSVSupplierProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class FlightEngineModule extends AbstractModule {

	@Override
	public void configure() {
		bind(FlightSupplier.class).
			annotatedWith(CSV.class).
				toProvider(CSVSupplierProvider.class);
		bind(FlightSupplier.class).
			annotatedWith(Names.named("xmlSupplier")).
				to(XMLSupplier.class).asEagerSingleton();
		bind(String.class).annotatedWith(Names.named("csvFolder")).toInstance(
				"./flightCSV");
	}
	
	@Provides
	@Named("jsonSupplier")
	public FlightSupplier provideJSONSupplier(){
		return new JSONSupplier();
	}
}


