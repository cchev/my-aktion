/**
 * 
 */
package de.ritter.myaktion.services;

import java.util.List;

import de.ritter.myaktion.model.Spende;

/**
 * @author christian
 *
 */
public interface SpendeService {
	List <Spende> getSpendeList(Long aktionId);
	void addSpende(Long aktionId, Spende spende);
}
