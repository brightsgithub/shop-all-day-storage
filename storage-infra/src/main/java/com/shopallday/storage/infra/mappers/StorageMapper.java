package com.shopallday.storage.infra.mappers;

import java.util.List;

public interface StorageMapper<Entity,Domain> {
    Entity mapToEntity(Domain domain);
    Domain mapToDomain(Entity entity);
    List<Entity> mapToEntity(List<Domain> domainList);
    List<Domain> mapToDomain(List<Entity> entities);
}
