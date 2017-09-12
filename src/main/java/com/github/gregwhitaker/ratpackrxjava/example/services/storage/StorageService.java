package com.github.gregwhitaker.ratpackrxjava.example.services.storage;

import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import rx.Observable;

import java.io.File;
import java.io.InputStream;

public interface StorageService {

    Observable<Metadata> save(InputStream inputStream);

    Observable<File> load();
}
