package org.packt.modules;

import java.io.IOException;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		
		try {
			ClassPath classPath = ClassPath.from(MainModule.class.getClassLoader());
			for(ClassInfo classInfo : classPath.getTopLevelClassesRecursive(MainModule.class.getPackage().getName())){
				if(validateClass(classInfo))
					install((Module)classInfo.load().newInstance());
			}			
		} catch (IOException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
	}
	
	boolean validateClass(ClassInfo classInfo){
		if(classInfo.getName().equals(MainModule.class.getName()))
			return false;	// no need to load the current class
		else if(classInfo.load().getSuperclass().equals(AbstractModule.class))
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
