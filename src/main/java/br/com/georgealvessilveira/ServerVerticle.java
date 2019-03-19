package br.com.georgealvessilveira;

import br.com.georgealvessilveira.rest.CustomerRestVerticle;
import br.com.georgealvessilveira.rest.AbstractRestVerticle;
import br.com.georgealvessilveira.services.CustomerService;
import io.vertx.core.AbstractVerticle;

import java.util.Arrays;
import java.util.List;

public class ServerVerticle extends AbstractVerticle {

    private final int port = Integer.getInteger(System.getenv("PORT"));
    private final String host = "0.0.0.0";

    private List<AbstractRestVerticle> restVerticles = Arrays.asList(
        new CustomerRestVerticle(new CustomerService())
    );

    @Override
    public void start() throws Exception {
        super.start();
        setsUpRestServer();
    }

    private void setsUpRestServer() {
        createHttpServer();
        deployVerticles();
    }

    private void createHttpServer() {
        vertx.createHttpServer()
        .requestHandler(handler -> {
            restVerticles.forEach(verticle -> verticle.accept(handler));
        })
        .listen(port, host);
    }

    private void deployVerticles() {
        restVerticles.forEach(verticle -> vertx.deployVerticle(verticle));
    }
}
