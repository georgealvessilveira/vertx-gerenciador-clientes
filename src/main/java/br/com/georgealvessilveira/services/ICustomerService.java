package br.com.georgealvessilveira.services;


import br.com.georgealvessilveira.Customer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface ICustomerService {

    /*
     * return Customer
     */
    public ICustomerService getCustomerById(int id, Handler<AsyncResult<Customer>> resultHandler);

    /*
     * return last inserted customer's id
     */
    public ICustomerService saveCustomer(Customer customer, Handler<AsyncResult<Integer>> resultHandler);

    /*
     * return a boolean if the customer was updated
     */
    public ICustomerService updateCustomer(Customer customer, Handler<AsyncResult<Boolean>> resultHandler);

    /*
     * return a boolean if the customer was deleted
     */
    public ICustomerService deleteCustomer(Customer customer, Handler<AsyncResult<Boolean>> resultHandler);
}
