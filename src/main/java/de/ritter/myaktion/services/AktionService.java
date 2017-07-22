/**
 * 
 */
package de.ritter.myaktion.services;

import java.util.List;

import de.ritter.myaktion.model.Aktion;

/**
 * @author christian
 *
 */
public interface AktionService {
	List<Aktion> getAllAktionen();
	void addAktion(Aktion aktion);
	void deleteAktion(Aktion aktion);
	void updateAktion(Aktion aktion);
}
