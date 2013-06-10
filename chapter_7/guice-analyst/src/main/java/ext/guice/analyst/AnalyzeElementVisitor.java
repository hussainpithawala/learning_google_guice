package ext.guice.analyst;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.StaticInjectionRequest;

public class AnalyzeElementVisitor extends DefaultElementVisitor<Void>{
	  @Override
	  public Void visit(StaticInjectionRequest staticInjectionRequest) {
		  	System.out.println("Static Injection at "+ staticInjectionRequest.getType().getName() + " " + staticInjectionRequest.getSource());
		    return visitOther(staticInjectionRequest);
	  }
	  
	  @Override
	  public <String> Void visit(Binding<String> binding) {
		  Key<String> key = binding.getKey();
		  System.out.println("Key :" + key.getTypeLiteral());
		  System.out.println("Annotation : " + key.getAnnotation());
		  System.out.println("Source : " + binding.getSource());
		  return visitOther(binding);
	  }
	  
	  public Void visit(PrivateElements privateElements) {
		  System.out.println("Private Elements " + privateElements.getSource());
		    return visitOther(privateElements);
	  }
}
