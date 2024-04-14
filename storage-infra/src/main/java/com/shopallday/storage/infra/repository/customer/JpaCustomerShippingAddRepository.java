package com.shopallday.storage.infra.repository.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.CustomerShippingAddressEntity;
import com.shopallday.storage.infra.mappers.CustomerShippingAddMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCustomerShippingAddRepository extends JpaRepository<CustomerShippingAddressEntity, Long>, CustomerShippingAddRepository {

    CustomerShippingAddMapper mapper = CustomerShippingAddMapper.INSTANCE;

    @Override
    default List<CustomerShippingAddress> findCustomerShippingAddressesById(Long id) {
        List<CustomerShippingAddressEntity> shippingAddressEntities = findAllById(List.of(id));
        return mapper.mapToDomain(shippingAddressEntities);
    }

    @Override
    default List<CustomerShippingAddress> getCustomerShippingAddresses() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<CustomerShippingAddress> createCustomerShippingAddress(final List<CustomerShippingAddress> shippingAddresses, RepositoryManager repositoryManager) {

        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<CustomerShippingAddressEntity> shippingAddressEntities = mapper.mapToEntity(shippingAddresses);

        Merge.mergeCustomerShipAddressEntity(entityManager, shippingAddressEntities);

        return mapper.mapToDomain(saveAll(shippingAddressEntities));
    }

    @Override
    default CustomerShippingAddress createCustomerShippingAddress(final CustomerShippingAddress shippingAddresses, RepositoryManager repositoryManager) {

        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final CustomerShippingAddressEntity shippingAddressEntities = mapper.mapToEntity(shippingAddresses);

        Merge.mergeCustomerShipAddressEntity(entityManager, shippingAddressEntities);

        return mapper.mapToDomain(save(shippingAddressEntities));
    }
    @Override
    default CustomerShippingAddress updateCustomerShippingAddress(final CustomerShippingAddress shippingAddress, RepositoryManager repositoryManager) {
        return createCustomerShippingAddress(shippingAddress, repositoryManager);
    }
    @Override
    default void deleteCustomerShippingAddress(final Long id) {
        deleteById(id);
    }

    @Override
    default void deleteAddressById(final Long id) {
        deleteById(id);
    }

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
