package com.github.gregwhitaker.ratpackrxjava.example.endpoints;

import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import com.github.gregwhitaker.ratpackrxjava.example.services.storage.StorageService;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.http.TypedData;
import ratpack.rx.RxRatpack;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Rest endpoints that handle file uploads.
 */
public class UploadEndpoint implements Action<Chain> {

    @Inject
    private StorageService storageService;

    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("/files", ctx -> {
            Observable<Metadata> result = RxRatpack.observe(ctx.getRequest().getBody())
                    .flatMap((Func1<TypedData, Observable<Metadata>>) typedData -> storageService.save(typedData.getInputStream()));

            RxRatpack.promiseSingle(result)
                    .onError(ctx::error)
                    .then(metadata -> ctx.render(json(metadata)));
        });
    }
}
