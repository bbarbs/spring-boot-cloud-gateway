package com.customer.service.impl;

import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.EmailExistsException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Maybe<List<Customer>> getAllCustomer() {
        return Maybe.fromCallable(() -> {
            List<Customer> customers = this.customerRepository.findAll();
            return customers;
        });
    }

    @Override
    public Maybe<Customer> getCustomerById(Long customerId) {
        return Maybe.fromCallable(() -> {
            AtomicReference<Customer> customer = new AtomicReference<>(new Customer());
            this.customerRepository.findById(customerId).ifPresent(res -> customer.set(res));
            if (customer.get() == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            return customer.get();
        });
    }

    @Override
    public Maybe<Customer> addCustomer(Customer customer) {
        return Maybe.fromCallable(() -> {
            // Check if email exist.
            boolean isEmailExist = this.customerRepository.existsByEmail(customer.getEmail());
            if (isEmailExist) {
                throw new EmailExistsException("Email already exist");
            }
            Customer c = this.customerRepository.save(customer);
            return c;
        });
    }

    @Override
    public Maybe<Customer> updateCustomerById(Long customerId, Customer customer) {
        return Maybe.fromCallable(() -> {
            AtomicReference<Customer> c = new AtomicReference<>(new Customer());
            this.customerRepository.findById(customerId).ifPresent(res -> c.set(res));
            if (c.get() == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            // Update customer.
            c.get().setFirstname(customer.getFirstname());
            c.get().setLastname(customer.getLastname());
            c.get().setAge(customer.getAge());
            this.customerRepository.save(c.get());
            return c.get();
        });
    }

    @Override
    public Maybe<Void> deleteCustomerById(Long customerId) {
        return Maybe.fromCallable(() -> {
            if (!this.customerRepository.existsById(customerId)) {
                throw new CustomerNotFoundException("Customer not found");
            }
            this.customerRepository.deleteById(customerId);
            return null;
        });
    }

    @Override
    public Maybe<List<Customer>> getCustomersByAge(int age) {
        return Maybe.fromCallable(() -> {
            List<Customer> customers = this.customerRepository.findAllByAge(age);
            return customers;
        });
    }
}
