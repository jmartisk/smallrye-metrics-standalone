package com.github.jmartisk.metrics;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetricBean {

    @Counted
    public void countedMethod() {
        System.out.println("Counted method was called");
    }

}
