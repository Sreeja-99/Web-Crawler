package com.eulerity.hackathon.imagefinder.Exceptions;

import java.io.IOException;

public class UrlConnectionError extends IOException {
    public UrlConnectionError(String message) {
        super(message);
    }

    public UrlConnectionError() {
        super("Could not connect to specified URL");
    }
}
