package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.SampleRepo;

public class SampleUseCase {

    private final SampleRepo sampleRepo;

    public SampleUseCase(SampleRepo sampleRepo) {
        System.out.println("SampleUseCase CREATED ");
        this.sampleRepo = sampleRepo;
    }

    public String execute() {
        return "SampleUseCase called "+ sampleRepo.execute();
    }
}
