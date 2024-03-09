package com.shopallday.storage.infra.repository.customer;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.infra.entities.CustomerEntity;
import com.shopallday.storage.infra.mappers.CustomerMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    default Customer findCustomerByEmail(String email) {
        final CustomerEntity customerEntity = findByEmail(email);
        return mapper.mapToDomain(customerEntity);
    }

    CustomerEntity findByEmail(String email);

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

    // case statement
    @Override
    @Query("SELECT " +
            "CASE " +
            "WHEN COUNT(c) > 0 THEN true " +
            "ELSE false END" +
            " FROM CustomerEntity c WHERE c.username = :username OR c.email = :email")
    boolean doesCustomerExist(@Param("username") String username, @Param("email") String email);

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
