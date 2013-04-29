package org.packt.scope;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import com.google.inject.ScopeAnnotation;

@Target({ FIELD, TYPE}) @Retention(RUNTIME)
@ScopeAnnotation
public @interface InScope {}
