package com.github.gregwhitaker.ratpackrxjava.example.endpoints;

import com.google.inject.AbstractModule;

/**
 * Guice module that loads all rest endpoints.
 */
public class EndpointsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UploadEndpoint.class);
        bind(DownloadEndpoint.class);
    }
}
