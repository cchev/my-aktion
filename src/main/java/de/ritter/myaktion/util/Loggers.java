package de.ritter.myaktion.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public class Loggers {
	@Qualifier
	@Target({ TYPE, METHOD, FIELD, PARAMETER })
	@Retention( RUNTIME )
	public @interface FachLog{
	} 
	
	@Qualifier
	@Target({ TYPE, METHOD, FIELD, PARAMETER })
	@Retention( RUNTIME )
	public @interface TecLog{
	}
}
