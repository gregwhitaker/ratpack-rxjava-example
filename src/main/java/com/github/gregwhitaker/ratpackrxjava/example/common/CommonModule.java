package com.github.gregwhitaker.ratpackrxjava.example.common;

import com.github.gregwhitaker.ratpackrxjava.example.common.handlers.CommonHandlers;
import com.github.gregwhitaker.ratpackrxjava.example.common.handlers.CorsHandler;
import com.google.inject.AbstractModule;

/**
 * Guice module that loads common components.
 */
public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CorsHandler.class);
        bind(CommonHandlers.class);
    }
}
