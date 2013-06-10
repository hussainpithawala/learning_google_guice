package ext.guice.analyst;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.multibindings.MapBinderBinding;
import com.google.inject.multibindings.MultibinderBinding;
import com.google.inject.multibindings.MultibindingsTargetVisitor;
import com.google.inject.spi.DefaultBindingTargetVisitor;

public class AnalyzeMultiBindingVisitor extends DefaultBindingTargetVisitor<Object, String> implements MultibindingsTargetVisitor<Object, String>{
	public String visit(MultibinderBinding<?> multibinder) {
		
		System.out.println("MultiBinding - Key: " + multibinder.getSetKey());
		for(Binding<?> binding : multibinder.getElements()){
			  Key<? extends Object> key = binding.getKey();
			  System.out.println("Key :" + key.getTypeLiteral());
			  System.out.println("Annotation : " + key.getAnnotation());
			  System.out.println("Source : " + binding.getSource());
		}
		
		return "Key: " + multibinder.getSetKey() +" \n"
	          + " uses bindings: " + multibinder.getElements() +" \n"
	          + " permitsDuplicates: " + multibinder.permitsDuplicates() +"\n";
	}
	
	public String visit(MapBinderBinding<?> mapbinder) {
		return "Key: " + mapbinder.getMapKey()
	          + " uses entries: " + mapbinder.getEntries()
	          + " permitsDuplicates: " + mapbinder.permitsDuplicates();
	}
}
