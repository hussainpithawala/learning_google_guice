package org.packt.supplier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class FlightJPASupplier implements FlightSupplier {

	@Inject
	private Provider<EntityManager> entityManagerProvider;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Set<SearchRS> getResults() {
		Query query = entityManagerProvider.get().createNamedQuery("SearchRS.findAll");
		Set<SearchRS> resultSet = new HashSet<SearchRS>();
		resultSet.addAll((List<SearchRS>)query.getResultList());
		return resultSet;
	}
}
