/**
 * 
 */
package de.ritter.myaktion.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import de.ritter.myaktion.services.SpendeService;

/**
 * @author christian
 *
 */
@Singleton
public class SchedulerBean {
	
	@Inject
	private SpendeService spendeService;
	
	/**
	 * Ruft alle 5 Minuten die Methode transferSpende des SpendeService auf.
	 */
	@Schedule(hour="*", minute="*/5", persistent=false)
	public void doTransferSpende() {
		spendeService.transferSpende();
	}

}
