package com.hat.javaadvance.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C {
    @Autowired
    A a;
}
