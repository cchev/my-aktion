/**
 * 
 */
package de.ritter.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.model.Spende.Status;
import de.ritter.myaktion.services.SpendeService;


/**
 * @author christian
 *
 */
@Named
@RequestScoped
public class SpendeListController implements Serializable {

	private static final long serialVersionUID = -5851368294741362716L;
	private Aktion aktion;
	
	@Inject
	private SpendeService spendeService;
	
	/**
	 * @return the aktion
	 */
	public Aktion getAktion() {
		return aktion;
	}

	/**
	 * @param aktion the aktion to set
	 */
	public void setAktion(Aktion aktion) {
		aktion.setSpenden(spendeService.getSpendeList(aktion.getId()));
		this.aktion = aktion;
	}
	
	public String doOk() {
		return Pages.AKTION_LIST;
	}
	
	public String convertStatus(Status status) {
		switch(status){
		case UEBERWIESEN: return "ueberwiesen";
		case IN_BEARBEITUNG: return "in Bearbeitung";
		default: return "";
		}
	}
}
