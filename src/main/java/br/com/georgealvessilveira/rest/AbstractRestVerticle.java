package br.com.georgealvessilveira.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;

public abstract class AbstractRestVerticle extends AbstractVerticle {

    public abstract void accept(HttpServerRequest request);
}
