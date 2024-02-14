package com.shopallday.storage.domain.repository;

public class RepositoryManager<Manager> {
    private final Manager manager;

    public RepositoryManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }
}
