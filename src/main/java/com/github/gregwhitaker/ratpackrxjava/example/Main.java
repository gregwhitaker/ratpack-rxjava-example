package com.github.gregwhitaker.ratpackrxjava.example;

import com.github.gregwhitaker.ratpackrxjava.example.common.CommonModule;
import com.github.gregwhitaker.ratpackrxjava.example.common.handlers.CommonHandlers;
import com.github.gregwhitaker.ratpackrxjava.example.config.StorageConfiguration;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.DownloadEndpoint;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.EndpointsModule;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.UploadEndpoint;
import com.github.gregwhitaker.ratpackrxjava.example.services.ServicesModule;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

/**
 * Runs the Ratpack RxJava Example application.
 */
public class Main {

    public static void main(String... args) throws Exception {
        RxRatpack.initialize();

        RatpackServer.start(s -> s
                .serverConfig(c -> c
                        .yaml("config.yaml")
                        .require("/storage", StorageConfiguration.class)
                        .baseDir(BaseDir.find()).build())
                .registry(Guice.registry(b -> b
                        .bindInstance(StorageConfiguration.class, b.getServerConfig().get("/storage", StorageConfiguration.class))
                        .module(CommonModule.class)
                        .module(EndpointsModule.class)
                        .module(ServicesModule.class))
                )
                .handlers(chain -> chain
                        .insert(CommonHandlers.class)
                        .insert(DownloadEndpoint.class)
                        .insert(UploadEndpoint.class)
                )
        );
    }
}
