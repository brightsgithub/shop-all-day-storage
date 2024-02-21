package com.shopallday.storage.infra.repository.customer;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.infra.entities.CustomerEntity;
import com.shopallday.storage.infra.mappers.CustomerMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Usually CustomerRepository extending CrudRepository would mean CustomerRepository interface has no implementation
 * in our code and the client would just directly the JPA via our CustomerRepository interface.
 * However, by implementing it this way, the JpaCustomerRepository class encapsulates the specific details
 * of how to interact with a customer using Spring Data JPA, and the CustomerService in the app module does not need to
 * be aware of these details. It relies on the CustomerRepository interface, and the Spring Data JPA infrastructure
 * handles the underlying implementation.
 */
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepository {

    CustomerMapper mapper = CustomerMapper.INSTANCE;

    // Implementing the createCustomer method using the save method provided by JpaRepository
    @Override
    default List<Customer> createCustomers(List<Customer> customers) {
        List<CustomerEntity> entities = mapper.mapToEntity(customers);
        return mapper.mapToDomain(saveAll(entities));
    }

    @Override
    default Customer createCustomer(final Customer customer) throws CreateCustomerException {
        final CustomerEntity customerEntity = mapper.mapToEntity(customer);
        return mapper.mapToDomain(save(customerEntity));
    }

    @Override
    default Customer findCustomerById(final Long id) throws ReadCustomerException{
        return mapper.mapToDomain(findById(id).orElseThrow(() -> new ReadCustomerException("")));
    }

    @Override
    default List<Customer> findCustomersById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default List<Customer> getCustomers() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default Customer updateCustomer(final Customer customer) {
        final CustomerEntity customerEntity = mapper.mapToEntity(customer);
        return mapper.mapToDomain(save(customerEntity));
    }

    @Override
    default void deleteCustomer(final Customer customer) {
        delete(mapper.mapToEntity(customer));
    }

    @Override
    default void deleteCustomerById(final Long id) {
        deleteById(id);
    }
}
