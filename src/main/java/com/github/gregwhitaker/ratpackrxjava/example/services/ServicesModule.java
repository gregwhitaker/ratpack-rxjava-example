package com.github.gregwhitaker.ratpackrxjava.example.services;

import com.github.gregwhitaker.ratpackrxjava.example.services.storage.LocalStorageService;
import com.github.gregwhitaker.ratpackrxjava.example.services.storage.StorageService;
import com.google.inject.AbstractModule;

/**
 * Guice module that loads all services.
 */
public class ServicesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StorageService.class).to(LocalStorageService.class);
    }
}
