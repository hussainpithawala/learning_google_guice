package org.packt.modules;

import java.util.Set;

import org.packt.scope.InScope;
import org.packt.supplier.CSV;
import org.packt.supplier.FlightSupplier;
import org.packt.supplier.JSONSupplier;
import org.packt.supplier.XMLSupplier;
import org.packt.supplier.provider.CSVSupplierProvider;
import org.packt.supplier.provider.ExcelCheckedProvider;
import org.packt.supplier.provider.ExcelCheckedSupplierProvider;
import org.packt.supplier.provider.MessageProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
public class FlightEngineModule extends AbstractModule {

	@Override
	public void configure() {

		bind(FlightSupplier.class).annotatedWith(CSV.class).toProvider(CSVSupplierProvider.class).in(InScope.class);

		/**
		 * This statement needs to be commented out
		 * in case we need to use @CheckedProvides as
		 * Guice will flag an exception during runtime
		 * once we try to prepare exactly similar bindings
		 * for a Key.
		 */
		ThrowingProviderBinder.create(binder()).
			bind(ExcelCheckedProvider.class, FlightSupplier.class).
				to(ExcelCheckedSupplierProvider.class);
		
		/**
		 * This statement needs to be uncommented
		 * once we plan to use @CheckedProvides rather
		 * than the concrete implementation.
		 * 
		install(ThrowingProviderBinder.forModule(this));
		*/
		bind(FlightSupplier.class).
			annotatedWith(Names.named("xmlSupplier")).
				to(XMLSupplier.class).asEagerSingleton();
		
		bind(new TypeLiteral<Set<String>>(){}).
			toProvider(MessageProvider.class).
				in(Singleton.class);
		
		 Multibinder<FlightSupplier> multiBinder = Multibinder.newSetBinder(binder(),FlightSupplier.class);
		 
		 multiBinder.addBinding().to(XMLSupplier.class).asEagerSingleton();
		 multiBinder.addBinding().toInstance(new JSONSupplier());
	}
	/**
	 * Uncomment the following method in case we need to 
	 * use the @CheckedProvides implementation over the concrete
	 * type
	@CheckedProvides(ExcelCheckedProvider.class)
	public FlightSupplier provideXlCheckedProvider() throws NoExcelAvailableException{
		return new ExcelSupplier();
	}
	*/
}
