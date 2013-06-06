package ext.guice.analyst;

import java.lang.annotation.Annotation;

import com.google.inject.Scope;
import com.google.inject.multibindings.MapBinderBinding;
import com.google.inject.multibindings.MultibinderBinding;
import com.google.inject.multibindings.MultibindingsTargetVisitor;
import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.DefaultBindingScopingVisitor;
import com.google.inject.spi.DefaultBindingTargetVisitor;
import com.google.inject.spi.DefaultElementVisitor;

/**
 * 
 * @author hussainp
 * This class acts as an implementation of Both BindingScopingVisitor and BindingTargetVisitor
 * by using Adapter pattern.
 */

public class AnalyzeBindingVisitorImpl extends DefaultBindingTargetVisitor<Object, String> implements BindingScopingVisitor<String>{
	private DefaultBindingScopingVisitor<String> defaultBindingScopingVisitor;
	  
	public AnalyzeBindingVisitorImpl(){
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
}
