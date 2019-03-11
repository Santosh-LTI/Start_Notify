package com.karle.CRUDspringMySQL.exception;

public class FileException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;
  
    public String getErrorMessage() {
        return errorMessage;
    }
    public FileException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public FileException() {
        super();
    }
}