package org.packt.intercerptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Invoking " +invocation.getMethod().getName());
		Object[] objectArray = invocation.getArguments();
		int i = 0;
		for(Object object : objectArray){
			System.out.println("  Argument [" +i +"]"+object.getClass().getName());
			++i;
		}
		return invocation.proceed();
	}

}
