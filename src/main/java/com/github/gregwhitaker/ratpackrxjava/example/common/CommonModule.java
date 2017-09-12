package com.github.gregwhitaker.ratpackrxjava.example.common;

import com.github.gregwhitaker.ratpackrxjava.example.common.handlers.CommonHandlers;
import com.github.gregwhitaker.ratpackrxjava.example.common.handlers.CorsHandler;
import com.google.inject.AbstractModule;

public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CorsHandler.class);
        bind(CommonHandlers.class);
    }
}
