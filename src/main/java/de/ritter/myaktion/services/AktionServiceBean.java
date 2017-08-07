/**
 * 
 */
package de.ritter.myaktion.services;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Organisator;
import de.ritter.myaktion.util.Loggers.FachLog;

/**  
 * @author christian
 *
 */
@RolesAllowed("Organisator")
@Stateless
public class AktionServiceBean implements AktionService {
	
	@Inject
	@FachLog
	private Logger logger;
	
	@Inject
	private EntityManager entityManager;
	
	@Resource
	private SessionContext sessionContext;

	/**
	 * 
	 */
	@Override
	public List<Aktion> getAllAktionen() {
		logger.info("Alle Aktionen selektieren erfolgreich!");
		TypedQuery<Aktion> query = entityManager.createNamedQuery(Aktion.findByOrganisator, Aktion.class);
		query.setParameter("organisator", getLoggedinOrganisator());
		List<Aktion> aktionen = query.getResultList();
		for(Aktion a: aktionen){
			Double bisherGespendet = getBisherGespendet(a);
			a.setBisherGespendet(bisherGespendet);
		}
		return aktionen;
	}
	
	public void addAktion(Aktion aktion){
		Organisator organisator = getLoggedinOrganisator();
		aktion.setOrganisator(organisator);
		entityManager.persist(aktion);
	}

	public void deleteAktion(Aktion aktion){
		Aktion managedAktion = entityManager.find(Aktion.class, aktion.getId());
		entityManager.remove(managedAktion);
	}

	public void updateAktion(Aktion aktion){
		entityManager.merge(aktion);
	}
	
	private Double getBisherGespendet(Aktion aktion) {
		TypedQuery<Double> query = entityManager.createNamedQuery(Aktion.getBisherGespendet, Double.class);
		logger.info("Selektiere Spende für Aktion" + aktion.getName());
		query.setParameter("aktion", aktion);
		Double result = query.getSingleResult();
		if(result==null)
			result = 0d;
		return result;
	}
	
	private Organisator getLoggedinOrganisator() {
		String organisatorEmail = sessionContext.getCallerPrincipal().getName();
		Organisator organisator = entityManager.createNamedQuery(Organisator.findByEmail, Organisator.class).setParameter("email", organisatorEmail).getSingleResult();
		return organisator;
	}

}
