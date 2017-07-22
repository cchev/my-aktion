/**
 * 
 */
package de.ritter.myaktion.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.ritter.myaktion.controller.AktionEditController.Mode;
import de.ritter.myaktion.model.Aktion;
import de.ritter.myaktion.util.Events.Deleted;
import de.ritter.myaktion.util.Loggers.FachLog;

@Named
@SessionScoped
/**
 * @author christian
 *
 */
public class AktionListController implements Serializable {
	private static final long serialVersionUID = 4617990229815748356L;

	@Inject
	private AktionEditController aktionEditController;
	
	@Inject
	private SpendeListController spendeListController;
	
	@Inject
	private SpendeFormEditController spendeFormEditController;
	
	@Inject
	@Deleted
	private Event<Aktion> aktionDeleteEventSrc;

	@Inject
	@FachLog
	private Logger logger;
	
	private Aktion aktionToDelete;
	
	/**
	 * Add a new Aktion
	 * @return next navigation point
	 */
	public String doAddAktion() {
		// System.out.println("Add Aktion");
		aktionEditController.setAktionToEdit(Mode.ADD);
		return Pages.AKTION_EDIT;
	}
	/**
	 * Edit a given Aktion
	 * @param aktion The Aktion to edit
	 * @return next navigation point
	 */
	public String doEditAktion(Aktion aktion) {
		// System.out.println("Edit Aktion " + aktion);
		aktionEditController.setAktionToEdit(aktion, Mode.EDIT);
		return Pages.AKTION_EDIT;
	}
	/**
	 * Edit the form of the Spende for a given Aktion
	 * @param aktion The Aktion to edit the form for
	 * @return next navigation point
	 */
	public String doEditSpendeForm(Aktion aktion) {
		// System.out.println("Edit Spende Form " + aktion);
		spendeFormEditController.setAktion(aktion);
		return Pages.SPENDE_FORM_EDIT;
	}
	/**
	 * List the Spenden for a given Aktion
	 * @param aktion The Aktion to open the list for
	 * @return next navigation point
	 */
	public String doListSpende(Aktion aktion) {
		// System.out.println("List Spende " + aktion);
		spendeListController.setAktion(aktion);
		return Pages.SPENDE_LIST;
	}
	/**
	 * Mark a given Aktion for deletion
	 * @param aktion The Aktion to delete
	 */
	public void doDeleteAktion(Aktion aktion) {
		this.aktionToDelete = aktion;
		//System.out.println("Aktion zum Löschen vorgemerkt!");
		logger.info("Spendenkation \"" + aktionToDelete.getName() + "\" zum Löschen vorgemerkt!");
	}
	/**
	 * Delete the marked Aktion
	 */
	public void commitDeleteAktion() {
		logger.info("Spendenkation \"" + aktionToDelete.getName() + "\" gelöscht!");
		aktionDeleteEventSrc.fire(aktionToDelete);
	}
}
