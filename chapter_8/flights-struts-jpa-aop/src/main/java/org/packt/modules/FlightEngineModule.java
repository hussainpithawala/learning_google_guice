package org.packt.modules;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.packt.scope.InScope;
import org.packt.supplier.CSV;
import org.packt.supplier.FlightJPASupplier;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.JSONSupplier;
import org.packt.supplier.XMLSupplier;
import org.packt.supplier.provider.CSVSupplierProvider;
import org.packt.supplier.provider.MessageProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
public class FlightEngineModule extends AbstractModule {

	@Override
	public void configure() {
		bind(FlightSupplier.class).
			annotatedWith(CSV.class).toProvider(CSVSupplierProvider.class).in(InScope.class);
		
		bind(FlightSupplier.class).
			annotatedWith(Names.named("xmlSupplier")).
				to(XMLSupplier.class).asEagerSingleton();
		
		bind(new TypeLiteral<Set<String>>(){}).
			toProvider(MessageProvider.class).
				in(Singleton.class);
		
		bind(FlightSupplier.class).annotatedWith(Names.named("jpa")).to(FlightJPASupplier.class).in(Singleton.class);

		Multibinder<FlightSupplier> multiBinder = Multibinder.newSetBinder(binder(),FlightSupplier.class);
		
		 multiBinder.addBinding().to(XMLSupplier.class).asEagerSingleton();
		 multiBinder.addBinding().toInstance(new JSONSupplier());
	}
}
