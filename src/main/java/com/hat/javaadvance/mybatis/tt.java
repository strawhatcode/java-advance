package com.hat.javaadvance.mybatis;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class tt {
    public static void main(String[] args) {
        AnnotationMetadata introspect = AnnotationMetadata.introspect(MapperScans.class);
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(introspect.getAnnotationAttributes(MapperScans.class.getName()));
        System.out.println("aaaaaaa");;
    }
}
