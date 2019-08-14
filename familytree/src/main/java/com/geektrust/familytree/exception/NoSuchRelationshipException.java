package com.geektrust.familytree.exception;


public class NoSuchRelationshipException extends Exception {
    public NoSuchRelationshipException() {
        super();
    }

    public NoSuchRelationshipException(String message) {
        super(message);
    }
}
