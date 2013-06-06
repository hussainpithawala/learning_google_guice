package ext.guice.analyst;

import com.google.inject.multibindings.MapBinderBinding;
import com.google.inject.multibindings.MultibinderBinding;
import com.google.inject.multibindings.MultibindingsTargetVisitor;
import com.google.inject.spi.DefaultBindingTargetVisitor;

public class AnalyzeMultiBindingVisitorImpl extends DefaultBindingTargetVisitor<Object, String> implements MultibindingsTargetVisitor<Object, String>{
	public String visit(MultibinderBinding<?> multibinder) {
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
