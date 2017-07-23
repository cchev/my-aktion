package de.ritter.myaktion.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Organisator
 *
 */
@NamedQueries({
	@NamedQuery(name=Organisator.findByEmail, query="SELECT o FROM Organisator o WHERE o.email = :email")
})
@Entity
public class Organisator {
	public static final String findByEmail = "Organsiator.findByEmail";
	
	@NotNull
	@Size(min=3, max=20, message="Der Vorname eines Organisators muss min. 3 und darf max. 20 Zeichen lang sein.")
	private String vorname;
	
	@NotNull
	@Size(min=3, max=30, message="Der Nachname eines Organisators muss min. 3 und darf max. 30 Zeichen lang sein.")
	private String nachname; 
	
	@Id
	@Pattern(regexp=".+@.+", message="Bitte eine valide Email-Adresse angeben.")
	private String email;
	
	@NotNull
	private String passwort;

	public Organisator() {
		super();
	}   
	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}   
	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
   
}
