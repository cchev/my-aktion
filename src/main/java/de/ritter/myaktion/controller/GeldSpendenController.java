/**
 * 
 */
package de.ritter.myaktion.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.ritter.myaktion.model.Spende;
import de.ritter.myaktion.model.Spende.Status;
import de.ritter.myaktion.services.SpendeService;
import de.ritter.myaktion.util.Loggers.FachLog;

@Named
@SessionScoped
/**
 * @author christian
 *
 */
public class GeldSpendenController implements Serializable {

	private static final long serialVersionUID = 4032557212304806533L;
	private String textColor = "000000";
	private String bgColor = "FFFFFF";
	private Spende spende;
	private Long aktionId;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	@FachLog
	private Logger logger;
	
	@Inject
	private SpendeService spendeService;
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.spende = new Spende();
	}

	/**
	 * @return the textColor
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the bgColor
	 */
	public String getBgColor() {
		return bgColor;
	}

	/**
	 * @param bgColor the bgColor to set
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * @return the spende
	 */
	public Spende getSpende() {
		return spende;
	}

	/**
	 * @param spende the spende to set
	 */
	public void setSpende(Spende spende) {
		this.spende = spende;
	}

	/**
	 * @return the aktionId
	 */
	public Long getAktionId() {
		return aktionId;
	}

	/**
	 * @param aktionId the aktionId to set
	 */
	public void setAktionId(Long aktionId) {
		this.aktionId = aktionId;
	}
	
	public String doSpende() {
		addSpende();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vielen Dank für die Spende!", null);
		facesContext.addMessage(null, facesMessage);
		return Pages.GELD_SPENDEN;
	}
	
	public void addSpende() {
		getSpende().setStatus(Status.IN_BEARBEITUNG);
		spendeService.addSpende(getAktionId(), getSpende());
		logger.info(spende.getSpenderName() + " hat " + spende.getBetrag() + " Euro gespendet.");
		init();
	}
}
