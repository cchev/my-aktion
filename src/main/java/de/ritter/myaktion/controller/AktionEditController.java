/**
 * 
 */
package de.ritter.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.util.Events.Added;
import de.ritter.myaktion.util.Events.Updated;


/**
 * @author christian
 *
 */
@Named 
@SessionScoped
public class AktionEditController implements Serializable {
	private static final long serialVersionUID = -2035530493556059089L;
	
	@Inject @Added
	private Event<Aktion> aktionAddEventSrc;
	
	@Inject @Updated
	private Event<Aktion> aktionUpdatedEventSrc;
	
	public enum Mode {
		EDIT, ADD
	};
	
	private Aktion aktion;
	private Mode mode;


	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}
	
	
	/**
	 * @return the aktion
	 */
	public Aktion getAktion() {
		return aktion;
	}

	/**
	 * @param aktion the aktion to set
	 */
	public void setAktionToEdit(Mode mode) {
		setAktionToEdit(new Aktion(), mode);
	}

	/**
	 * @param aktion the aktion to set
	 */
	public void setAktionToEdit(Aktion aktion, Mode mode) {
		this.aktion = aktion;
		this.mode = mode;
	}

	public String doSave() {
		if(getMode()==Mode.ADD) {
			aktionAddEventSrc.fire(aktion);
		} else if(getMode()==Mode.EDIT) {
			aktionUpdatedEventSrc.fire(aktion);
		}
		return Pages.AKTION_LIST;
	}
	
	public String doCancel() {
		return Pages.AKTION_LIST;
	}
	
	public String getTitle() {
		return getMode()==Mode.EDIT ? "Aktion editieren" : "Neue Aktion anlegen";
	}
}
