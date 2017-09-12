package com.github.gregwhitaker.ratpackrxjava.example.services.storage;

import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import rx.Observable;

import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {

    Observable<Metadata> save(InputStream inputStream);

    Observable<Path> load(String id);
}
