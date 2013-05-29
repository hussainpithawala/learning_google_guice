package ext.guice.analyst;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderWithExtensionVisitor;

public class AssistInstallModule<T> implements Module,
		ProviderWithExtensionVisitor<T> {

	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	public <B, V> V acceptExtensionVisitor(BindingTargetVisitor<B, V> visitor,
			ProviderInstanceBinding<? extends B> binding) {
		// TODO Auto-generated method stub
		return null;
	}

	public void configure(Binder binder) {
		// TODO Auto-generated method stub

	}

}
