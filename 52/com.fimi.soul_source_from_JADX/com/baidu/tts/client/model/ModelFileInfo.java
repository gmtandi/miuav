package com.baidu.tts.client.model;

import android.content.Context;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.ResourceTools;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelFileInfo {
    private String f4243a;
    private String f4244b;
    private String f4245c;
    private String f4246d;
    private String f4247e;
    private String f4248f;

    public void generateAbsPath(Context context) {
        this.f4247e = ResourceTools.getModelFileAbsName(context, this.f4246d);
    }

    public String getAbsPath() {
        return this.f4247e;
    }

    public String getLength() {
        return this.f4244b;
    }

    public String getMd5() {
        return this.f4245c;
    }

    public String getName() {
        return this.f4246d;
    }

    public String getServerid() {
        return this.f4243a;
    }

    public String getUrl() {
        return this.f4248f;
    }

    public void parseJson(JSONObject jSONObject) {
        this.f4243a = jSONObject.optString(C0794g.ID.m6742b());
        this.f4244b = jSONObject.optString(C0794g.LENGTH.m6742b());
        this.f4245c = jSONObject.optString(C0794g.MD5.m6742b());
        this.f4246d = jSONObject.optString(C0794g.NAME.m6742b());
        this.f4248f = jSONObject.optString(C0794g.URL.m6742b());
    }

    public void setAbsPath(String str) {
        this.f4247e = str;
    }

    public void setLength(String str) {
        this.f4244b = str;
    }

    public void setMap(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            this.f4243a = (String) map.get(C0794g.ID.m6742b());
            this.f4244b = (String) map.get(C0794g.LENGTH.m6742b());
            this.f4245c = (String) map.get(C0794g.MD5.m6742b());
            this.f4246d = (String) map.get(C0794g.NAME.m6742b());
            this.f4247e = (String) map.get(C0794g.ABS_PATH.m6742b());
        }
    }

    public void setMd5(String str) {
        this.f4245c = str;
    }

    public void setName(String str) {
        this.f4246d = str;
    }

    public void setServerid(String str) {
        this.f4243a = str;
    }

    public void setUrl(String str) {
        this.f4248f = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(C0794g.ID.m6742b(), this.f4243a);
            jSONObject.putOpt(C0794g.LENGTH.m6742b(), this.f4244b);
            jSONObject.putOpt(C0794g.MD5.m6742b(), this.f4245c);
            jSONObject.putOpt(C0794g.NAME.m6742b(), this.f4246d);
            jSONObject.putOpt(C0794g.ABS_PATH.m6742b(), this.f4247e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
