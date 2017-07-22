/**
 * 
 */
package de.ritter.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.ritter.myaktion.model.Aktion;

@Named
@SessionScoped
/**
 * @author christian
 *
 */
public class SpendeFormEditController implements Serializable {

	private static final long serialVersionUID = 1790011270539011153L;
	private String textColor = "000000";
	private String bgColor = "FFFFFF";
	private Aktion aktion;
	
	@Inject
	private HttpServletRequest req;
	
	public String doOk() {
		return Pages.AKTION_LIST;
	}
	
	public String getAppUrl() {
		// HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		
		return scheme + "://" + serverName + ":" + serverPort + contextPath;
	}
	
	public String getUrl() {
		// return "http://localhost:8080/my-aktion/geldSpenden.faces?bgColor=" + bgColor + "&textColor=" + textColor + "&aktionId=" + aktion.getId();
		return getAppUrl() + "/" + Pages.GELD_SPENDEN + ".faces" + 
								   "?bgColor=" + bgColor + 
								   "&textColor=" + textColor + 
								   "&aktionId=" + aktion.getId();
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
	 * @return the aktion
	 */
	public Aktion getAktion() {
		return aktion;
	}

	/**
	 * @param aktion the aktion to set
	 */
	public void setAktion(Aktion aktion) {
		this.aktion = aktion;
	}

}
