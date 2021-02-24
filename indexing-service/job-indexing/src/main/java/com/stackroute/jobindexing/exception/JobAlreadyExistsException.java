package com.stackroute.jobindexing.exception;


public class JobAlreadyExistsException extends Exception {

    private String message;
    public JobAlreadyExistsException(){}
    public JobAlreadyExistsException(String message)
    {
        super();
        this.message=message;
    }
}
