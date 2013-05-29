package org.packt.supplier.provider;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Provider;

public class MessageProvider implements Provider<Set<String>> {

	@Override
	public Set<String> get() {
		Set<String> messageSet = new HashSet<String>();
		messageSet.add("Hi Client");
		messageSet.add("Bye Client");
		return messageSet;
	}

}
