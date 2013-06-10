package ext.guice.analyst;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderWithExtensionVisitor;

public class AssistInstallModule extends AbstractModule{
	
	private Set<Module> modules = new HashSet<Module>();
	
	private String packageName;
	
	public AssistInstallModule(String packageName) {
		this.packageName = packageName;
	}

	public Void get() {
		return null;
	}

	public void configure() {
		try {
			ClassPath classPath = ClassPath.from(AssistInstallModule.class.getClassLoader());
			for(ClassInfo classInfo : classPath.getTopLevelClassesRecursive(packageName)){
				if(validateClass(classInfo)){
					Module module = (Module)classInfo.load().newInstance();
					modules.add(module);
					install(module);
				}
			}			
		} catch (IOException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}finally{
		}
		
		/**
		 * Using the following block below, we analyze the various 
		 * elements being bound for injection, in various modules.
		System.out.println("Visiting the Elements");
		
		AnalyzeElementVisitor defaultElementVisitor = new AnalyzeElementVisitor();
		for(Element element : Elements.getElements(Stage.DEVELOPMENT,modules)){
			element.acceptVisitor(defaultElementVisitor);
		}
		*/
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
