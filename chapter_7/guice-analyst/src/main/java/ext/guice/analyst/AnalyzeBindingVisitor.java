package ext.guice.analyst;

import java.lang.annotation.Annotation;

import com.google.inject.Key;
import com.google.inject.Scope;
import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.DefaultBindingScopingVisitor;
import com.google.inject.spi.DefaultBindingTargetVisitor;
import com.google.inject.spi.InstanceBinding;

/**
 * 
 * @author hussainp
 * This class acts as an implementation of Both BindingScopingVisitor and BindingTargetVisitor
 * by using Adapter pattern.
 */

public class AnalyzeBindingVisitor extends DefaultBindingTargetVisitor<Object, String> implements BindingScopingVisitor<String>{
	private DefaultBindingScopingVisitor<String> defaultBindingScopingVisitor;
	  
	public AnalyzeBindingVisitor(){
		defaultBindingScopingVisitor = new DefaultBindingScopingVisitor<String>();
	}
	 
	public String visitEagerSingleton() {
		return defaultBindingScopingVisitor.visitEagerSingleton();
	}
	
	public String visitScope(Scope scope) {
		return scope.toString(); // Our custom implementation
	}
	
	public String visitScopeAnnotation(Class<? extends Annotation> scopeAnnotation) {
		return defaultBindingScopingVisitor.visitScopeAnnotation(scopeAnnotation);
	}
	
	public String visitNoScoping() {
		return defaultBindingScopingVisitor.visitNoScoping();
	}
	
	/**
	 * Overridden methods from DefaultBindingTargetVisitor
	 */
	public String visit(InstanceBinding<?> binding){
		  Key<?> key = binding.getKey();
		  System.out.println("Key :" + key.getTypeLiteral());
		  System.out.println("Annotation : " + key.getAnnotation());
		  System.out.println("Source : " + binding.getSource());
		System.out.println("Instance : "+binding.getInstance().toString());
		return visitOther(binding);
	}
}
