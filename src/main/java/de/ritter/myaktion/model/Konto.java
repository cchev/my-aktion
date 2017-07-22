/**
 * 
 */
package de.ritter.myaktion.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author christian 
 *
 */
@Embeddable
public class Konto {
	
	@NotNull
	@Size(min=5, max=60, message="Der Name des Besitzers eines Kontos muss min. 5 und darf max. 60 Zeichen lang sein.")
	private String name;
	
	@NotNull
	@Size(min=4, max=40, message="Der Name einer Bank muss min. 4 und darf max. 40 Zeichen lang sein.")
	private String nameDerBank;
	
	@NotNull
	@Pattern(regexp="\\d+", message="Eine Konto-Nr. besteht nur aus Ziffern.")
	private String kontoNr;
	
	@NotNull
	@Pattern(regexp="\\d{8}", message="Eine BLZ besteht immer aus 8 Ziffern.")
	private String blz;
	
	public Konto() {
		this(null, null, null, null);
	}
	
	public Konto(String name, String nameDerBank, String kontoNr, String blz) {
		this.name = name;
		this.nameDerBank = nameDerBank;
		this.kontoNr = kontoNr;
		this.blz = blz;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nameDerBank
	 */
	public String getNameDerBank() {
		return nameDerBank;
	}

	/**
	 * @param nameDerBank the nameDerBank to set
	 */
	public void setNameDerBank(String nameDerBank) {
		this.nameDerBank = nameDerBank;
	}

	/**
	 * @return the kontoNr
	 */
	public String getKontoNr() {
		return kontoNr;
	}

	/**
	 * @param kontoNr the kontoNr to set
	 */
	public void setKontoNr(String kontoNr) {
		this.kontoNr = kontoNr;
	}

	/**
	 * @return the blz
	 */
	public String getBlz() {
		return blz;
	}

	/**
	 * @param blz the blz to set
	 */
	public void setBlz(String blz) {
		this.blz = blz;
	}
}
