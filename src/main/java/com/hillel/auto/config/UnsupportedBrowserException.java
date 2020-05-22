package com.hillel.auto.config;

public class UnsupportedBrowserException extends AssertionError {

    public UnsupportedBrowserException(String browser) {
        super("Unsupported browser " +  browser + "!");
    }
}
