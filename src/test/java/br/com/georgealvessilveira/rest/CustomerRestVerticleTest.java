package br.com.georgealvessilveira.rest;

import br.com.georgealvessilveira.Customer;
import br.com.georgealvessilveira.ServerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class CustomerRestVerticleTest {

    private final Vertx vertx = Vertx.vertx();
    private final int port = 8090;
    private final String host = "0.0.0.0";

    @Before
    public void iniciaConfiguracao(TestContext context) {
        JsonObject json = new JsonObject().put("http.port", port);
        DeploymentOptions options = new DeploymentOptions(json);
        vertx.deployVerticle(new ServerVerticle(), options, context.asyncAssertSuccess());
    }

    @After
    public void finalizaAplicacao(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    private io.vertx.core.http.HttpClient createClient() {
        return vertx.createHttpClient();
    }

    @Test
    public void retornoDeClienteComNome(TestContext context) {
        final Async async = context.async();
        final int id = 1;

        createClient()
        .getNow(port, host, "/api/customer/id/" + id, response -> response.handler(body -> {
            System.out.println("Body:\n" + body.toString());
            context.assertTrue(body.toString().contains("George"));
            async.complete();
        }));
    }

    @Test
    public void salvaCliente(TestContext context) {
        final Async async = context.async();
        final String json = Json.encodePrettily(new Customer(1, "George"));

        createClient()
        .post(port, host, "/api/customer")
        .putHeader("Content-Type", "application/json")
        .putHeader("Content-Length", Integer.toString(json.length()))
        .handler(response -> {
            context.assertEquals(response.statusCode(), 201);
            context.assertTrue(response.headers().get("Content-Type").contains("application/json"));

            response.handler(body -> {
                String resultJson = body.toString();
                context.assertTrue(resultJson.contains("1"));
                System.out.println(resultJson);
            });

            async.complete();
        })
        .write(json)
        .end();
    }

    @Test
    public void editaCliente(TestContext context) {
        final Async async = context.async();
        final String json = Json.encodePrettily(new Customer(1, "George"));

        createClient()
        .put(port, host, "/api/customer/")
        .putHeader("Content-Type", "application/json")
        .putHeader("Content-Length", Integer.toString(json.length()))
        .handler(response -> {
            context.assertEquals(response.statusCode(), 201);
            context.assertTrue(response.headers().get("Content-Type").contains("application/json"));

            response.handler(body -> {
                String resultJson = body.toString();
                context.assertTrue(resultJson.contains("true"));
                System.out.println(resultJson);
            });
            async.complete();
        })
        .write(json)
        .end();
    }

    @Test
    public void deletaCliente(TestContext context) {
        final Async async = context.async();
        final String json = Json.encodePrettily(new Customer(1, "George"));

        createClient()
        .delete(port, host, "/api/customer/")
        .putHeader("Content-Type", "application/json")
        .putHeader("Content-Length", Integer.toString(json.length()))
        .handler(response -> {
            context.assertEquals(response.statusCode(), 201);
            context.assertTrue(response.headers().get("Content-Type").contains("application/json"));

            response.handler(body -> {
                String resultJson = body.toString();
                context.assertTrue(resultJson.contains("true"));
                System.out.println(resultJson);
            });
            async.complete();
        })
        .write(json)
        .end();
    }
}
