package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.repository.delete.DeleteAllRepository;

public class DeleteAllUseCase implements UseCaseNoParamNoReturnVal{

    private final DeleteAllRepository deleteAllRepository;

    public DeleteAllUseCase(DeleteAllRepository deleteAllRepository) {
        this.deleteAllRepository = deleteAllRepository;
    }

    @Override
    public void execute() throws Exception {
        deleteAllRepository.deleteAllTables();
    }
}
