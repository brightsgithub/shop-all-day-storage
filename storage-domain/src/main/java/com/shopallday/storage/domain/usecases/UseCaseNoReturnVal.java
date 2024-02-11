package com.shopallday.storage.domain.usecases;

public interface UseCaseNoReturnVal<P> {
    void execute(P param) throws Exception;
}
