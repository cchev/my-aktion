/**
 * 
 */
package de.ritter.myaktion.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author christian
 *
 */
@NamedQueries({
	@NamedQuery(name=Spende.findByStatus, query="SELECT s FROM Spende s WHERE s.status = :status")
})
@Entity
public class Spende {
	public static final String findByStatus = "Spende.findByStatus";
	
	@GeneratedValue
	@Id
	private Long id;
	
	@NotNull(message="Bitte einen Spendenbetrag angeben.")
	@DecimalMin(value="1.00", message="Der Spendenbetrag muss min. 1 Euro sein.")
	private Double betrag;
	
	@NotNull
	@Size(min=5, max=40, message="Der Name eines Spenders muss min. 5 und darf max. 40 Zeichen lang sein.")
	private String spenderName;
	
	@NotNull
	private Boolean quittung;
	
	@NotNull
	private Status status;
	
	@NotNull
	@Embedded
	private Konto konto;
	
	@NotNull
	@ManyToOne
	private Aktion aktion;
	
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

	public enum Status {
		UEBERWIESEN, IN_BEARBEITUNG;
	}

	public Spende() {
		this.konto = new Konto();
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the betrag
	 */
	public Double getBetrag() {
		return betrag;
	}

	/**
	 * @param betrag the betrag to set
	 */
	public void setBetrag(Double betrag) {
		this.betrag = betrag;
	}

	/**
	 * @return the spenderName
	 */
	public String getSpenderName() {
		return spenderName;
	}

	/**
	 * @param spenderName the spenderName to set
	 */
	public void setSpenderName(String spenderName) {
		this.spenderName = spenderName;
	}

	/**
	 * @return the quittung
	 */
	public Boolean getQuittung() {
		return quittung;
	}

	/**
	 * @param quittung the quittung to set
	 */
	public void setQuittung(Boolean quittung) {
		this.quittung = quittung;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the konto
	 */
	public Konto getKonto() {
		return konto;
	}

	/**
	 * @param konto the konto to set
	 */
	public void setKonto(Konto konto) {
		this.konto = konto;
	}
}
