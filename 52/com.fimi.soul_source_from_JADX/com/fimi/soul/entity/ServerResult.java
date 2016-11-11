package com.fimi.soul.entity;

public class ServerResult<T> {
    private String commandCode;
    private T data;
    private String errorCode;
    private String errorMessage;
    private String page;
    private String success;

    public String getCommandCode() {
        return this.commandCode;
    }

    public T getData() {
        return this.data;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getPage() {
        return this.page;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setCommandCode(String str) {
        this.commandCode = str;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setPage(String str) {
        this.page = str;
    }

    public void setSuccess(String str) {
        this.success = str;
    }
}
