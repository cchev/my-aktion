package de.ritter.myaktion.model;

import de.ritter.myaktion.model.Spende.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Spende.class)
public abstract class Spende_ {

	public static volatile SingularAttribute<Spende, Konto> konto;
	public static volatile SingularAttribute<Spende, String> spenderName;
	public static volatile SingularAttribute<Spende, Double> betrag;
	public static volatile SingularAttribute<Spende, Boolean> quittung;
	public static volatile SingularAttribute<Spende, Long> id;
	public static volatile SingularAttribute<Spende, Aktion> aktion;
	public static volatile SingularAttribute<Spende, Status> status;

}

