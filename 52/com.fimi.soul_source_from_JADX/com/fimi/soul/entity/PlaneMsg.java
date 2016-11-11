package com.fimi.soul.entity;

import java.io.Serializable;

public class PlaneMsg implements Serializable {
    private static final long serialVersionUID = 1;
    private String commandCode;
    private Object data;
    private String errorCode;
    private String errorMessage;
    private Page page;
    private boolean success;

    public String getCommandCode() {
        return this.commandCode;
    }

    public Object getData() {
        return this.data;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Page getPage() {
        return this.page;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setCommandCode(String str) {
        this.commandCode = str;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String toString() {
        return "PlaneMsg{errorMessage='" + this.errorMessage + '\'' + ", commandCode='" + this.commandCode + '\'' + ", errorCode='" + this.errorCode + '\'' + ", data=" + this.data + ", page=" + this.page + ", success=" + this.success + '}';
    }
}
