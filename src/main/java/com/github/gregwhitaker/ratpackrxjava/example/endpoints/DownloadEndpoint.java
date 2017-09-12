package com.github.gregwhitaker.ratpackrxjava.example.endpoints;

import com.github.gregwhitaker.ratpackrxjava.example.services.storage.StorageService;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.rx.RxRatpack;

import javax.inject.Inject;

/**
 * Rest endpoints that handle file downloads.
 */
public class DownloadEndpoint implements Action<Chain> {

    @Inject
    private StorageService storageService;

    @Override
    public void execute(Chain chain) throws Exception {
        chain.get("files/:id", ctx -> {
            String id = ctx.getPathTokens().get("id");

            RxRatpack.promiseSingle(storageService.load(id))
                    .then(path -> {
                        if (path != null) {
                            ctx.getResponse().contentType("application/octet-stream");
                            ctx.getResponse().sendFile(path);
                        } else {
                            ctx.notFound();
                        }
                    });
        });
    }
}
