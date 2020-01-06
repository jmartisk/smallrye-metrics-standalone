package com.github.jmartisk.metrics;

import io.smallrye.metrics.MetricsRequestHandler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.util.stream.Stream;

public class Handler implements io.vertx.core.Handler<HttpServerRequest> {

    private final MetricsRequestHandler handler;

    public Handler(MetricsRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(HttpServerRequest request) {
        HttpServerResponse response = request.response();
        if (request.path().startsWith("/metrics")) {
            try {
                Stream<String> acceptHeaders = request.headers().getAll("Accept").stream();
                handler.handleRequest(request.path(), "/metrics", request.rawMethod(), acceptHeaders, (status, message, headers) -> {
                    response.setStatusCode(status);
                    headers.forEach(response::putHeader);
                    response.end(Buffer.buffer(message));
                });
            }
            catch (IOException e) {
                e.printStackTrace();
                response.setStatusCode(503);
                response.end();
            }
        }
        else {
            response.setStatusCode(404);
            response.end();
        }
    }
}
