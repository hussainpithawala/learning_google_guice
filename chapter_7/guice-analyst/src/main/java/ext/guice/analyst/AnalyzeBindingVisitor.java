package ext.guice.analyst;

import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.BindingTargetVisitor;
/**
 * 
 * @author hussainp
 *
 * @param <T>
 * @param <V>
 * 
 *  This interface serves as a joint point between BindingScopingVisitor and BindingTargetVisitor.
 *  The essential purpose of this Visitor interface would be to visit and log all the bindings.
 */

public interface AnalyzeBindingVisitor<T, V> extends BindingScopingVisitor<V>,
		BindingTargetVisitor<T, V> {
}
