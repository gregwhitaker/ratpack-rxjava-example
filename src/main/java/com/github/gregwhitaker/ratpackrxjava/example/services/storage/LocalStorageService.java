package com.github.gregwhitaker.ratpackrxjava.example.services.storage;

import com.github.gregwhitaker.ratpackrxjava.example.config.StorageConfiguration;
import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Singleton
public class LocalStorageService implements StorageService {

    private static final Logger LOG = LoggerFactory.getLogger(LocalStorageService.class);

    private final Path dataDirectory;

    @Inject
    public LocalStorageService(StorageConfiguration config) {
        dataDirectory = Paths.get(config.directory);

        if (!Files.exists(dataDirectory)) {
            try {
                Files.createDirectories(dataDirectory);
            } catch (IOException e) {
                throw new RuntimeException("Unable to configure LocalStorageService", e);
            }
        }
    }

    @Override
    public Observable<Metadata> save() {
        return null;
    }

    @Override
    public Observable<File> load() {
        return null;
    }
}
