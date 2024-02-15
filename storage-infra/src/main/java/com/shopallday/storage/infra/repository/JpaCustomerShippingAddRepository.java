package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.CustomerShippingAddressEntity;
import com.shopallday.storage.infra.mappers.CustomerShippingAddMapper;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCustomerShippingAddRepository extends JpaRepository<CustomerShippingAddressEntity, Long>, CustomerShippingAddRepository {

    CustomerShippingAddMapper mapper = CustomerShippingAddMapper.INSTANCE;

    @Override
    default CustomerShippingAddress findCustomerShippingAddressById(final Long id) {
        CustomerShippingAddressEntity shippingAddressEntity = findById(id).orElse(null);
        return mapper.mapToDomain(shippingAddressEntity);
    }

    @Override
    default List<CustomerShippingAddress> findCustomerShippingAddressesById(final List<Long> ids) {
        List<CustomerShippingAddressEntity> shippingAddressEntities = findAllById(ids);
        return mapper.mapToDomain(shippingAddressEntities);
    }

    @Override
    default List<CustomerShippingAddress> getCustomerShippingAddresses() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default void createCustomerShippingAddress(final List<CustomerShippingAddress> shippingAddresses, RepositoryManager repositoryManager) {

        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<CustomerShippingAddressEntity> shippingAddressEntities = mapper.mapToEntity(shippingAddresses);

        for (CustomerShippingAddressEntity shippingAddressEntity : shippingAddressEntities) {
            Merge.mergeCustomerEntity(entityManager, shippingAddressEntity);
        }

        saveAll(shippingAddressEntities);
    }

    @Override
    default void updateCustomerShippingAddress(final CustomerShippingAddress shippingAddress, RepositoryManager repositoryManager) {
        createCustomerShippingAddress(List.of(shippingAddress), repositoryManager);
    }

    @Override
    default void deleteCustomerShippingAddress(final CustomerShippingAddress shippingAddress) {
        delete(mapper.mapToEntity(shippingAddress));
    }
}
