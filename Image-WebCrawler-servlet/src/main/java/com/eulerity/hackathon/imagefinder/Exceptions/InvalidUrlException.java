package com.eulerity.hackathon.imagefinder.Exceptions;

public class InvalidUrlException extends Exception {

    public InvalidUrlException(String message) {
        super(message);
    }

    public InvalidUrlException() {
        super("Invalid URL");
    }
}
