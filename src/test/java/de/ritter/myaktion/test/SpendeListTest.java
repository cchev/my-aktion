/**
 * 
 */
package de.ritter.myaktion.test;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ritter.myaktion.controller.GeldSpendenController;
import de.ritter.myaktion.controller.SpendeListController;
import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Konto;
import de.ritter.myaktion.model.Spende;
import de.ritter.myaktion.services.AktionService;
import de.ritter.myaktion.services.AktionServiceBean;
import de.ritter.myaktion.services.SpendeService;
import de.ritter.myaktion.services.SpendeServiceBean;
import de.ritter.myaktion.util.Resources;

/**
 * @author christian
 *
 */
@RunWith(Arquillian.class)
public class SpendeListTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
	            .addClasses(Aktion.class, Konto.class, Spende.class, 
	            		SpendeListController.class, GeldSpendenController.class,
	            		SpendeService.class, SpendeServiceBean.class,
	            		AktionService.class, AktionServiceBean.class,
	            		Resources.class, TestDataFactory.class,
	            		Logger.class)
	            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}
	
	@Inject
	GeldSpendenController geldSpendenController;
	
	@Inject
	AktionService aktionService;
	
	@Inject
	SpendeService spendeService;
	
	@Test 
	public void testBisherGespendet() throws Exception {
		Aktion aktion = TestDataFactory.createTestAktion();
		aktionService.addAktion(aktion);
		// zwei Spenden erstellen
		spendeService.addSpende(aktion.getId(), TestDataFactory.createTestSpende());
		spendeService.addSpende(aktion.getId(), TestDataFactory.createTestSpende());
		// persistierte Aktion in der Liste aller Aktionen suchen
		List<Aktion> aktionen = aktionService.getAllAktionen();
		Aktion persistedAktion = null;
		for(Aktion a : aktionen) {
			if(a.getId() == aktion.getId())
				persistedAktion = a;
		}
		Assert.assertNotNull(persistedAktion);
		// Überprüfen, ob der gespendete Wert für die Aktion de zweifache Spendenbetrag ist
		Double bisherGespendet = persistedAktion.getBisherGespendet();
		Assert.assertEquals(new Double(2*TestDataFactory.createTestSpende().getBetrag()), bisherGespendet);
	}
	
	@Test 
	public void testAddSpende() throws Exception{
		Aktion aktion = TestDataFactory.createTestAktion();
		aktionService.addAktion(aktion);
		geldSpendenController.setAktionId(aktion.getId());
		geldSpendenController.setSpende(TestDataFactory.createTestSpende());
		geldSpendenController.addSpende();
		// Überprüfen, ob nach der Spende eine Liste von Spende-Objekten für die Aktion existiert
		List<Spende> spenden = spendeService.getSpendeList(aktion.getId());
		Assert.assertNotNull(spenden);
		// Überprüfen, ob der Betrag der Spende in der Liste derselbe ist, wie in der gemockten Spende
		Assert.assertEquals(TestDataFactory.createTestSpende().getBetrag(), spenden.get(0).getBetrag());
	}
}
