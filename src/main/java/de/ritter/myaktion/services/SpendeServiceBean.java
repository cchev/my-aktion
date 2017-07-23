/**
 * 
 */
package de.ritter.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Spende;


/**
 * @author christian
 *
 */
@Stateless
public class SpendeServiceBean implements SpendeService{
	
	@Inject
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see de.ritter.myaktion.services.SpendeService#getSpendeList(java.lang.Long)
	 */
	@Override
	public List<Spende> getSpendeList(Long aktionId) {
		Aktion managedAktion = entityManager.find(Aktion.class, aktionId);
		List<Spende> spenden = managedAktion.getSpenden();
		spenden.size();
		return spenden;
	}

	/* (non-Javadoc)
	 * @see de.ritter.myaktion.services.SpendeService#addSpende(java.lang.Long, de.ritter.myaktion.model.Spende)
	 */
	@Override
	public void addSpende(Long aktionId, Spende spende) {
		Aktion managedAktion = entityManager.find(Aktion.class, aktionId);
		spende.setAktion(managedAktion);
		if(spende.getBetrag() <= managedAktion.getSpendenBetrag())
			throw new RuntimeException("Spendenbetrag zu klein");
		entityManager.persist(spende);
	}

}
