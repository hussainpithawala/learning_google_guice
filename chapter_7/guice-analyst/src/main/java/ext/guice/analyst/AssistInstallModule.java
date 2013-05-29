package ext.guice.analyst;

import java.io.IOException;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderWithExtensionVisitor;

public class AssistInstallModule<T> implements
		Module,ProviderWithExtensionVisitor<T> {
	
	private String packageName;
	
	public AssistInstallModule(String packageName) {
		this.packageName = packageName;
	}

	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	public <B, V> V acceptExtensionVisitor(BindingTargetVisitor<B, V> visitor,
			ProviderInstanceBinding<? extends B> binding) {
		return null;
	}

	public void configure(Binder binder) {
		try {
			ClassPath classPath = ClassPath.from(AssistInstallModule.class.getClassLoader());
			for(ClassInfo classInfo : classPath.getTopLevelClassesRecursive(packageName)){
				if(validateClass(classInfo))
					binder.install((Module)classInfo.load().newInstance());
			}			
		} catch (IOException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
	}
	
	boolean validateClass(ClassInfo classInfo){
		if(classInfo.load().getSuperclass().equals(AbstractModule.class))
			return true;	// load if it's a sub class of AbstractModule 
		else{
			for(Class<?> interfaces : classInfo.load().getInterfaces()){
				if(interfaces.equals(Module.class)) 
					return true; // load if it implements Module
			}
			return false;
		}
	}

}
