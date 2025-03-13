package com.eulerity.hackathon.imagefinder.Exceptions;

public class UrlLimitExceeded extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public UrlLimitExceeded() {
        super("Already explored too many urls");
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UrlLimitExceeded(String message) {
        super(message);
    }
}
