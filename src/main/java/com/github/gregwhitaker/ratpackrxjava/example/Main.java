package com.github.gregwhitaker.ratpackrxjava.example;

import com.github.gregwhitaker.ratpackrxjava.example.common.CommonHandlers;
import com.github.gregwhitaker.ratpackrxjava.example.config.AppConfiguration;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.DownloadEndpoint;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.EndpointsModule;
import com.github.gregwhitaker.ratpackrxjava.example.endpoints.UploadEndpoint;
import com.github.gregwhitaker.ratpackrxjava.example.services.ServicesModule;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class Main {

    public static void main(String... args) throws Exception {
        RxRatpack.initialize();

        RatpackServer.start(s -> s
                .serverConfig(c -> c
                        .yaml("config.yaml")
                        .require("/app", AppConfiguration.class)
                        .baseDir(BaseDir.find()).build())
                .registry(Guice.registry(b -> b
                        .bindInstance(AppConfiguration.class, b.getServerConfig().get("/app", AppConfiguration.class))
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
