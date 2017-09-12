package com.github.gregwhitaker.ratpackrxjava.example.services.storage;

import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import rx.Observable;

import java.io.File;

public interface StorageService {

    Observable<Metadata> save();

    Observable<File> load();
}
