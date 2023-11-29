package com.sky.tv.comics.anotation.mesurement;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //it is apply for method only
@Retention(RetentionPolicy.RUNTIME) //it available at runtime
@Documented
public @interface LogExecutionTime {}
