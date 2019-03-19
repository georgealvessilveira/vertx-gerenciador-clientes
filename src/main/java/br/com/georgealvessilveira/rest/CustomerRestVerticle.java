package br.com.georgealvessilveira.rest;

import br.com.georgealvessilveira.Customer;
import br.com.georgealvessilveira.services.CustomerService;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class CustomerRestVerticle extends AbstractRestVerticle {

    private final CustomerService service;
    private final Router router;

    public CustomerRestVerticle(CustomerService service) {
        this.service = service;
        this.router = Router.router(vertx);
    }

    @Override
    public void start() throws Exception {
        super.start();
        router.route().handler(BodyHandler.create());
        router.get("/api/customer/id/:id").handler(this::getCustomerById);
        router.post("/api/customer").handler(this::saveCustomer);
        router.put("/api/customer").handler(this::updateCustomer);
        router.delete("/api/customer").handler(this::deleteCustomer);
    }

    @Override
    public void accept(HttpServerRequest request) {
        router.accept(request);
    }

    private void getCustomerById(RoutingContext context) {
        int id = Integer.valueOf(context.request().getParam("id"));

        service.getCustomerById(id, handler -> {
            Customer customer = handler.result();
            String json = new JsonObject()
                    .put("id", customer.getId())
                    .put("name", customer.getName())
                    .encodePrettily();

            context
            .response()
            .putHeader("Content-Type", "application/json")
            .end(json);
        });
    }

    private void saveCustomer(RoutingContext context) {
        String json = context.getBody().toString();
        Customer customer = Json.decodeValue(json, Customer.class);

        service.saveCustomer(customer, handler -> {
            Integer id = handler.result();

            context
            .response()
            .putHeader("Content-Type", "application/json")
            .setStatusCode(201)
            .end(json);
        });
    }

    private void updateCustomer(RoutingContext context) {
        String json = context.getBody().toString();
        Customer customer = Json.decodeValue(json, Customer.class);

        service.updateCustomer(customer, handler -> {
            boolean wasUpdated = handler.result();

            context
            .response()
            .putHeader("Content-Type", "application/json")
            .setStatusCode(201)
            .end(new JsonObject().put("was_updated:", wasUpdated).encode());
        });
    }

    private void deleteCustomer(RoutingContext context) {
        String json = context.getBody().toString();
        Customer customer = Json.decodeValue(json, Customer.class);

        service.deleteCustomer(customer, resultHandler -> {
            boolean wasDeleted = resultHandler.result();

            context
            .response()
            .putHeader("Content-Type", "application/json")
            .setStatusCode(201)
            .end(new JsonObject().put("was_deleted", wasDeleted).encode());
        });
    }
}
