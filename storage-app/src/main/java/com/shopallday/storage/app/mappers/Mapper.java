package com.shopallday.storage.app.mappers;

import java.util.List;

public interface Mapper <Domain, Dto> {
    Dto mapFromDomainToDto(Domain domain);
    Domain mapFromDtoToDomain(Dto Dto);
    List<Dto> mapFromDomainToDto(List<Domain> domainList);
    List<Domain> mapFromDtoToDomain(List<Dto> dtos);
}



