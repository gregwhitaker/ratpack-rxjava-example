package com.github.gregwhitaker.ratpackrxjava.example.common.handlers;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Collection of handlers that are applied across all request chains.
 */
public class CommonHandlers implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.all(CorsHandler.class);
    }
}
