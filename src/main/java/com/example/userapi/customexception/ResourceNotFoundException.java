package com.example.userapi.customexception;

public class ResourceNotFoundException extends  Exception{

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String exception){
        this.exceptionString = exceptionString;
    }

    private String exceptionString;

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException{" +
                "exceptionString='" + exceptionString + '\'' +
                '}';
    }
}
