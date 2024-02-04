package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.repository.SampleRepo;

public class SampleRepoImpl implements SampleRepo {

    public SampleRepoImpl() {
        System.out.println("SampleRepoImpl CREATED ");
    }

    @Override
    public String execute() {
        return "SampleRepo called";
    }

}
