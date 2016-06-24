package com.example.xuqiwei.commontestproject.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuqiwei on 16-6-21.
 */
public class AnnoTEst {
    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = ElementType.FIELD)
    public @interface ClassAnnotation {
        String asda() ;
    }
    @AnnoTEst.ClassAnnotation(asda = "123")
    public String sd;
}
