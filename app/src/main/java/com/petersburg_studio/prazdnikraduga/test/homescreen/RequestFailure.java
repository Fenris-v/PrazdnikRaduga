package com.petersburg_studio.prazdnikraduga.test.homescreen;

import com.petersburg_studio.prazdnikraduga.test.util.Retryable;

public class RequestFailure {

    private Retryable retryable;
    private String errorMessage;

    public RequestFailure(Retryable retryable, String errorMessage) {
        this.retryable = retryable;
        this.errorMessage = errorMessage;
    }

    public Retryable getRetryable() {
        return retryable;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
