package com.shopallday.storage.domain.usecases;

public interface UseCaseNoParam<R> {
    R execute() throws Exception;
}
