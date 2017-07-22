/**
 * 
 */
package de.ritter.myaktion.data;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Konto;
import de.ritter.myaktion.model.Spende; 
import de.ritter.myaktion.model.Spende.Status;
import de.ritter.myaktion.services.AktionService;
import de.ritter.myaktion.util.Events.Added;
import de.ritter.myaktion.util.Events.Deleted;
import de.ritter.myaktion.util.Events.Updated;



/**
 * @author christian
 *
 */ 
@RequestScoped
public class AktionListProducer {
	private List<Aktion> aktionen;
	
	@Named
	@Produces
	public List<Aktion> getAktionen() {
		return aktionen;
	}
	
	/**
	 * 
	 */
	@Inject
	private AktionService aktionService;
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		aktionen = aktionService.getAllAktionen();
	}
	
	/**
	 * 
	 */
	public void onAktionAdded(@Observes @Added Aktion aktion) {
		aktionService.addAktion(aktion);
		init();
	}
	
	public void onAktionUpdated(@Observes @Updated Aktion aktion) {
		aktionService.updateAktion(aktion);
		init();
	}
	
	public void onAktionDeleted(@Observes @Deleted Aktion aktion) {
		aktionService.deleteAktion(aktion);
		init();
	}
	
	/**
	 * 
	 */
	public List<Aktion> createMockAktionen() {
		Spende spende1 = new Spende();
		spende1.setSpenderName("Heinz Schmidt");
		spende1.setBetrag(20d);
		spende1.setQuittung(true);
		spende1.setStatus(Status.UEBERWIESEN);
		spende1.setKonto(new Konto(spende1.getSpenderName(), "XXX Bank", "123456", "87654321"));
		
		Spende spende2 = new Spende();
		spende2.setSpenderName("Karl Meier");
		spende2.setBetrag(20d);
		spende2.setQuittung(true);
		spende2.setStatus(Status.UEBERWIESEN);
		spende2.setKonto(new Konto(spende2.getSpenderName(), "YYY Bank", "654321", "86427531"));
		
		List<Spende> spenden = new LinkedList<Spende>();
		spenden.add(spende1);
		spenden.add(spende2);
		
		Aktion aktion1 = new Aktion();
		aktion1.setName("Trikots für A-Jugend");
		aktion1.setSpendenZiel(1000d);
		aktion1.setBisherGespendet(258d);
		aktion1.setSpendenBetrag(20d);
		aktion1.setId(1L);
		aktion1.setKonto(new Konto("Max Mustermann", "ABC Bank", "100200300", "12345678"));
		aktion1.setSpenden(spenden);
		
		Aktion aktion2 = new Aktion();
		aktion2.setName("Rollstuhl für Maria");
		aktion2.setSpendenZiel(2500d);
		aktion2.setBisherGespendet(742d);
		aktion2.setSpendenBetrag(25d);
		aktion2.setId(2L);
		aktion2.setKonto(aktion1.getKonto());
		aktion2.setSpenden(spenden);
		
		List<Aktion> ret = new LinkedList<Aktion>();
		ret.add(aktion1);
		ret.add(aktion2);
		return ret;
	}
}
