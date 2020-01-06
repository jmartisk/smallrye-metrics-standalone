package com.github.jmartisk.metrics;

import io.smallrye.metrics.MetricsRequestHandler;
import io.vertx.core.Vertx;

import javax.enterprise.inject.spi.CDI;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        // start CDI and scan for metrics
        org.jboss.weld.environment.se.StartMain.main(null);

        // start vertx and the exporters
        Vertx vertx = Vertx.vertx();
        MetricsRequestHandler internalHandler = new MetricsRequestHandler();
        vertx.createHttpServer().requestHandler(new Handler(internalHandler)).listen(8080);

        Logger.getLogger(Main.class.getName()).info("Started in " + (System.currentTimeMillis() - start) + " ms");

        // generate some actual data for start
        CDI.current().select(MetricBean.class).get().countedMethod();
    }

}
