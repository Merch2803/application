package com.epam.exceptions;

public class WorkspaceServiceUnavailableException extends RuntimeException {

    public WorkspaceServiceUnavailableException() {
        super();
    }

    public WorkspaceServiceUnavailableException(String message) {
        super(message);
    }
}
