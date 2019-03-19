package br.com.georgealvessilveira.services;

import br.com.georgealvessilveira.Customer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CustomerService implements ICustomerService {

    @Override
    public ICustomerService getCustomerById(int id, Handler<AsyncResult<Customer>> resultHandler) {
        Future<Customer> future = Future.future();
        future.setHandler(resultHandler);
        future.complete(new Customer(1, "George"));
        return this;
    }

    @Override
    public ICustomerService saveCustomer(Customer customer, Handler<AsyncResult<Integer>> resultHandler) {
        Future<Integer> future = Future.future();
        future.setHandler(resultHandler);
        future.complete(customer.getId());
        return this;
    }

    @Override
    public ICustomerService updateCustomer(Customer customer, Handler<AsyncResult<Boolean>> resultHandler) {
        Future<Boolean> future = Future.future();
        future.setHandler(resultHandler);
        future.complete(true);
        return this;
    }

    @Override
    public ICustomerService deleteCustomer(Customer customer, Handler<AsyncResult<Boolean>> resultHandler) {
        Future<Boolean> future = Future.future();
        future.setHandler(resultHandler);
        future.complete(true);
        return this;
    }
}
