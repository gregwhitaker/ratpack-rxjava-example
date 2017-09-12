package com.github.gregwhitaker.ratpackrxjava.example.model;

/**
 * File metadata returned when a file is uploaded.
 */
public class Metadata {

    private final String id;

    public Metadata(final String id) {
        this.id = id;
    }

    /**
     * File identifier
     */
    public String getId() {
        return id;
    }
}
