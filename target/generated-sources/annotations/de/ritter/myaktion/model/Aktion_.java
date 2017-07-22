package de.ritter.myaktion.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aktion.class)
public abstract class Aktion_ {

	public static volatile SingularAttribute<Aktion, Konto> konto;
	public static volatile SingularAttribute<Aktion, String> name;
	public static volatile ListAttribute<Aktion, Spende> spenden;
	public static volatile SingularAttribute<Aktion, Long> id;
	public static volatile SingularAttribute<Aktion, Double> spendenBetrag;
	public static volatile SingularAttribute<Aktion, Double> spendenZiel;

}

