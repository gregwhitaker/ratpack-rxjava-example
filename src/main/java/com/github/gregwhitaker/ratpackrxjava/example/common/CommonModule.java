package com.github.gregwhitaker.ratpackrxjava.example.common;

import com.google.inject.AbstractModule;

public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CorsHandler.class);
        bind(CommonHandlers.class);
    }
}
