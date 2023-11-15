package com.ouc.mallcommon.exception;

public class ServiceException extends RuntimeException{
    private int code;
    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
