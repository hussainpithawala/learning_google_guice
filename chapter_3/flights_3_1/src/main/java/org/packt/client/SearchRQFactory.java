package org.packt.client;

import java.util.Date;

import com.google.inject.assistedinject.Assisted;

public interface SearchRQFactory {
	SearchRequest create(
			@Assisted("depLoc") String depLoc,
			@Assisted("arrivLoc") String arrivLoc, 
			Date flightDate);
}
