package com.fimi.soul.entity;

import com.tencent.connect.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

public class SuggestBean implements Serializable {
    private String commandCode;
    private String contact;
    private String content;
    private List<String> list;
    private String model;
    private String userID;

    public SuggestBean() {
        this.commandCode = "addSuggestion";
        this.list = new ArrayList();
    }

    public String getCommandCode() {
        return this.commandCode;
    }

    public String getContact() {
        return this.contact;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getList() {
        return this.list;
    }

    public String getModel() {
        return this.model;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setCommandCode(String str) {
        this.commandCode = str;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setUserID(String str) {
        if (str == null || C2915a.f14760f.equals(str)) {
            this.userID = Constants.VIA_RESULT_SUCCESS;
        } else {
            this.userID = str;
        }
    }
}
