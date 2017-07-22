package de.ritter.myaktion.util;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import de.ritter.myaktion.util.Loggers.FachLog;


public class Resources {
	
	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	@FachLog
	public Logger produceFachLog(InjectionPoint injectionPoint) {
		return Logger.getLogger("FachLog: " + injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	//@Produces 
	//@TecLog
	//public Logger produceTecLog(InjectionPoint injectionPoint) {
	//	return Logger.getLogger("TecLog: " + injectionPoint.getMember().getDeclaringClass().getName());
	//}
	
	@Produces
	@RequestScoped
	public FacesContext producesFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	HttpServletRequest produceRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
