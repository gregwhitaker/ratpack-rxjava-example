package com.github.gregwhitaker.ratpackrxjava.example.endpoints;

import com.github.gregwhitaker.ratpackrxjava.example.services.storage.StorageService;
import ratpack.func.Action;
import ratpack.handling.Chain;

import javax.inject.Inject;

/**
 * Rest endpoints that handle file downloads.
 */
public class DownloadEndpoint implements Action<Chain> {

    @Inject
    private StorageService storageService;

    @Override
    public void execute(Chain chain) throws Exception {

    }
}
