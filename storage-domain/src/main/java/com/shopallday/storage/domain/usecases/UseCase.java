package com.shopallday.storage.domain.usecases;

public interface UseCase<R,P> {
    R execute(P params);
}
