/**
 * 
 */
package de.ritter.myaktion.util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import de.ritter.myaktion.util.Loggers.FachLog;


/**
 * @author christian
 *
 */
public class DurationInterceptor {

	@Inject
	@FachLog
	private Logger logger;
	
	/**
	 * @param ic
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		try {
			long time = System.currentTimeMillis();
			Object ret = ic.proceed();
			long duration = System.currentTimeMillis()-time;
			logger.info(String.format("%s hat %d Sekunden benötigt", ic.getMethod().getName(), TimeUnit.MILLISECONDS.toSeconds(duration)));
			return ret;
		} catch(Exception e) {
			System.err.println("Fehler beim Ermitteln der Ausführungszeit.");
			throw e;
		}
	}

}
