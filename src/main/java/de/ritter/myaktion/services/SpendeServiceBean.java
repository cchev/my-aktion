/**
 * 
 */
package de.ritter.myaktion.services;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Spende;
import de.ritter.myaktion.model.Spende.Status;
import de.ritter.myaktion.util.Loggers.FachLog;


/**
 * @author christian
 *
 */
@Stateless
public class SpendeServiceBean implements SpendeService{
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	@FachLog
	private Logger logger;

	/* (non-Javadoc)
	 * @see de.ritter.myaktion.services.SpendeService#getSpendeList(java.lang.Long)
	 */
	@RolesAllowed("Organisator")
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
	@PermitAll
	@Override
	public void addSpende(Long aktionId, Spende spende) {
		Aktion managedAktion = entityManager.find(Aktion.class, aktionId);
		spende.setAktion(managedAktion);
		if(spende.getBetrag() <= managedAktion.getSpendenBetrag())
			throw new RuntimeException("Spendenbetrag zu klein");
		entityManager.persist(spende);
	}
	
	@PermitAll
	public void transferSpende(){
		logger.info("Zu bearbeitende Spenden werden überwiesen.");
		TypedQuery<Spende> query = entityManager.createNamedQuery(Spende.findByStatus, Spende.class);
		query.setParameter("status", Status.IN_BEARBEITUNG);
		List<Spende> spenden = query.getResultList();
		int count = 0;
		for(Spende spende : spenden) {
			spende.setStatus(Status.UEBERWIESEN);
			count++;
		}
		logger.info("Es wurden " + count + " Spenden überwiesen.");
	}

}
