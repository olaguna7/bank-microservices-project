package com.oscar.accountsms.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldNane, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s", resourceName, fieldNane, fieldValue));
    }

}
