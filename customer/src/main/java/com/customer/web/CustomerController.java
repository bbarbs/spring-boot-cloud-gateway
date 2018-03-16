package com.customer.web;

import com.customer.model.Customer;
import com.customer.service.CustomerService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Get list of customers.
     *
     * @return
     */
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Maybe<List<Customer>> getAllCustomer() {
        return this.customerService.getAllCustomer();
    }

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> addCustomer(@RequestBody Customer customer) {
        return this.customerService.addCustomer(customer);
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @GetMapping(
            value = "/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Customer> getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return this.customerService.getCustomerById(customerId);
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @DeleteMapping(
            value = "/{customerId}",
            produces = {APPLICATION_JSON_VALUE, TEXT_PLAIN_VALUE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Maybe<Void> deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return this.customerService.deleteCustomerById(customerId);
    }

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    @PutMapping(
            value = "/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> updateCustomerById(@PathVariable(name = "customerId") Long customerId, @RequestBody Customer customer) {
        return this.customerService.updateCustomerById(customerId, customer);
    }

    /**
     * Get customers by age.
     *
     * @param age
     * @return
     */
    @GetMapping(
            params = {"age"},
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Maybe<List<Customer>> getCustomersByAge(@RequestParam(name = "age") int age) {
        return this.customerService.getCustomersByAge(age);
    }
}
