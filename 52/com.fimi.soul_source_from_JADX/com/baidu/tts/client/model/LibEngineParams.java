package com.baidu.tts.client.model;

import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.JsonTool;
import org.json.JSONException;
import org.json.JSONObject;

public class LibEngineParams {
    private String f4234a;
    private String f4235b;
    private String[] f4236c;
    private String[] f4237d;
    private String[] f4238e;

    public LibEngineParams(String str) {
        this.f4234a = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f4235b = jSONObject.optString(C0794g.VERSION.m6742b());
            this.f4236c = JsonTool.getStringarray(jSONObject.optJSONArray(C0794g.DOMAIN.m6742b()));
            this.f4237d = JsonTool.getStringarray(jSONObject.optJSONArray(C0794g.LANGUAGE.m6742b()));
            this.f4238e = JsonTool.getStringarray(jSONObject.optJSONArray(C0794g.QUALITY.m6742b()));
        } catch (Exception e) {
        }
    }

    public String[] getDomain() {
        return this.f4236c;
    }

    public JSONObject getJsonResult() {
        try {
            return new JSONObject(this.f4234a);
        } catch (JSONException e) {
            return null;
        }
    }

    public String[] getLanguage() {
        return this.f4237d;
    }

    public String[] getQuality() {
        return this.f4238e;
    }

    public String getResult() {
        return this.f4234a;
    }

    public String getVersion() {
        return this.f4235b;
    }

    public void setDomain(String[] strArr) {
        this.f4236c = strArr;
    }

    public void setLanguage(String[] strArr) {
        this.f4237d = strArr;
    }

    public void setQuality(String[] strArr) {
        this.f4238e = strArr;
    }

    public void setVersion(String str) {
        this.f4235b = str;
    }
}
