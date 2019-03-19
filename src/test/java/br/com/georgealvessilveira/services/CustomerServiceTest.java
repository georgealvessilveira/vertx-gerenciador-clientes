package br.com.georgealvessilveira.services;

import br.com.georgealvessilveira.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CustomerServiceTest {

    private ICustomerService service;

    @Before
    public void iniciaServico() {
        this.service = new CustomerService();
    }

    @Test
    public void buscaCliente() {
        int id = 1;
        Customer[] resultCustomer = new Customer[1];
        service.getCustomerById(id, resultHandler -> {
            resultCustomer[0] = resultHandler.result();
        });
        Assert.assertEquals(id, resultCustomer[0].getId());
    }

    @Test
    public void salvaCliente() {
        Customer customer = new Customer(2, "George");
        int[] resultLastInsertedId = { 0 };
        service.saveCustomer(customer, resultHandler -> {
            resultLastInsertedId[0] = resultHandler.result();
        });
        Assert.assertEquals(2, resultLastInsertedId[0]);
    }

    @Test
    public void editaCliente() {
        Customer customer = new Customer(2, "George");
        boolean[] wasUpdated = { false };
        service.updateCustomer(customer, resultHandler -> {
            wasUpdated[0] = resultHandler.result();
        });
        Assert.assertTrue(wasUpdated[0]);
    }

    @Test
    public void excluiCliente() {
        Customer customer = new Customer(2, "George");
        boolean[] wasDeleted = { false };
        service.deleteCustomer(customer, resultHandler -> {
            wasDeleted[0] = resultHandler.result();
        });
        Assert.assertTrue(wasDeleted[0]);
    }
}
