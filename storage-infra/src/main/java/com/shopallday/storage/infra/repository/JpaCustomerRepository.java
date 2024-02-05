package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.exceptions.customer.DeleteCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.exceptions.customer.UpdateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Usually CustomerRepository extending CrudRepository would mean CustomerRepository interface has no implementation
 * in our code and the client would just directly the JPA via our CustomerRepository interface.
 * However, by implementing it this way, the JpaCustomerRepository class encapsulates the specific details
 * of how to interact with a customer using Spring Data JPA, and the CustomerService in the app module does not need to
 * be aware of these details. It relies on the CustomerRepository interface, and the Spring Data JPA infrastructure
 * handles the underlying implementation.
 */
public interface JpaCustomerRepository extends CrudRepository<Customer, Long>, CustomerRepository {

    // Implementing the createCustomer method using the save method provided by JpaRepository
    @Override
    default void createCustomer(Customer customer) {
        save(customer);
    }

    @Override
    default Customer findCustomerById(final Long id) throws ReadCustomerException{
        return findById(id).orElseThrow(() -> new ReadCustomerException(""));
    }

    @Override
    default List<Customer> findCustomersById(final List<Long> ids) {
        return StreamSupport.stream(findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    default List<Customer> getCustomers() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    default void updateCustomer(final Customer customer) {
        save(customer);
    }

    @Override
    default void deleteCustomer(final Customer customer) {
        delete(customer);
    }

    @Override
    default void deleteCustomerById(final Long id) {
        deleteById(id);
    }
}
