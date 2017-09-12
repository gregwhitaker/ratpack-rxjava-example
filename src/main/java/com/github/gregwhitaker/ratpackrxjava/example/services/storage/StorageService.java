package com.github.gregwhitaker.ratpackrxjava.example.services.storage;

import com.github.gregwhitaker.ratpackrxjava.example.model.Metadata;
import rx.Observable;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * Interface that all file storage services must implement.
 */
public interface StorageService {

    /**
     * Save the file.
     *
     * @param inputStream file data
     * @return {@link Metadata} containing the id of the newly saved file
     */
    Observable<Metadata> save(InputStream inputStream);

    /**
     * Load a file by id.
     *
     * @param id file identifier
     * @return path to the file
     */
    Observable<Path> load(String id);
}
