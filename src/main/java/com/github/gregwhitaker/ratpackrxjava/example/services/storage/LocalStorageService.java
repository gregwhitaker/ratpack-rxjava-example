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
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

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
    public Observable<Metadata> save(InputStream inputStream) {
        return Observable.create(subscriber -> {
            String id = UUID.randomUUID().toString();

            FileChannel fchannel = null;
            try {
                Path newPath = Paths.get(dataDirectory.toString(), id);

                LOG.info("Saving file: {}", newPath.toString());

                fchannel = FileChannel.open(newPath, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND);
                fchannel.transferFrom(Channels.newChannel(inputStream), 0, inputStream.available());

                subscriber.onNext(new Metadata(id));
            } catch (IOException e) {
                subscriber.onError(e);
            } finally {
                if (fchannel != null) {
                    try {
                        fchannel.close();
                    } catch (IOException e) {
                        // Noop
                    }
                }
            }

            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Path> load(String id) {
        return Observable.create(subscriber -> {
            Path storagePath = Paths.get(dataDirectory.toString(), id);

            if (Files.exists(storagePath)) {
                subscriber.onNext(storagePath);
            } else {
                subscriber.onNext(null);
            }

            subscriber.onCompleted();
        });
    }
}
