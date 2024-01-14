package com.sky.tv.comics.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //it is apply for method only
@Retention(RetentionPolicy.RUNTIME) //it available at runtime
@Documented
public @interface Analysis {

}
