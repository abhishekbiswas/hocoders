package com.barclaycardus.hackathon.hocoders.service.rds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by abhishek on 11/06/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RDSDependencyAttribute {
    Class[] dependsOn();
}