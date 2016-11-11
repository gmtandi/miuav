package com.baidu.tts.client.model;

import com.baidu.tts.p041e.C0794g;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelInfo {
    private String f4249a;
    private String f4250b;
    private String f4251c;
    private String f4252d;
    private String f4253e;
    private String f4254f;
    private String f4255g;
    private String f4256h;
    private String f4257i;
    private String f4258j;
    private String f4259k;

    public String getDomain() {
        return this.f4256h;
    }

    public String getGender() {
        return this.f4254f;
    }

    public String getLanguage() {
        return this.f4253e;
    }

    public String getName() {
        return this.f4250b;
    }

    public String getQuality() {
        return this.f4257i;
    }

    public String getServerId() {
        return this.f4249a;
    }

    public String getSpeaker() {
        return this.f4255g;
    }

    public String getSpeechDataId() {
        return this.f4259k;
    }

    public String getTextDataId() {
        return this.f4258j;
    }

    public String getVersionMax() {
        return this.f4252d;
    }

    public String getVersionMin() {
        return this.f4251c;
    }

    public void parseJson(JSONObject jSONObject) {
        this.f4249a = jSONObject.optString(C0794g.ID.m6742b());
        this.f4250b = jSONObject.optString(C0794g.NAME.m6742b());
        this.f4251c = jSONObject.optString(C0794g.VERSION_MIN.m6742b());
        this.f4252d = jSONObject.optString(C0794g.VERSION_MAX.m6742b());
        this.f4253e = jSONObject.optString(C0794g.LANGUAGE.m6742b());
        this.f4254f = jSONObject.optString(C0794g.GENDER.m6742b());
        this.f4255g = jSONObject.optString(C0794g.SPEAKER.m6742b());
        this.f4256h = jSONObject.optString(C0794g.DOMAIN.m6742b());
        this.f4257i = jSONObject.optString(C0794g.QUALITY.m6742b());
        this.f4258j = jSONObject.optString(C0794g.TEXT_DATA_ID.m6742b());
        this.f4259k = jSONObject.optString(C0794g.SPEECH_DATA_ID.m6742b());
    }

    public void setDomain(String str) {
        this.f4256h = str;
    }

    public void setGender(String str) {
        this.f4254f = str;
    }

    public void setLanguage(String str) {
        this.f4253e = str;
    }

    public void setMap(Map<String, String> map) {
        if (map != null) {
            this.f4249a = (String) map.get(C0794g.ID.m6742b());
            this.f4250b = (String) map.get(C0794g.NAME.m6742b());
            this.f4251c = (String) map.get(C0794g.VERSION_MIN.m6742b());
            this.f4252d = (String) map.get(C0794g.VERSION_MAX.m6742b());
            this.f4253e = (String) map.get(C0794g.LANGUAGE.m6742b());
            this.f4254f = (String) map.get(C0794g.GENDER.m6742b());
            this.f4255g = (String) map.get(C0794g.SPEAKER.m6742b());
            this.f4256h = (String) map.get(C0794g.DOMAIN.m6742b());
            this.f4257i = (String) map.get(C0794g.QUALITY.m6742b());
            this.f4258j = (String) map.get(C0794g.TEXT_DATA_ID.m6742b());
            this.f4259k = (String) map.get(C0794g.SPEECH_DATA_ID.m6742b());
        }
    }

    public void setName(String str) {
        this.f4250b = str;
    }

    public void setQuality(String str) {
        this.f4257i = str;
    }

    public void setServerId(String str) {
        this.f4249a = str;
    }

    public void setSpeaker(String str) {
        this.f4255g = str;
    }

    public void setSpeechDataId(String str) {
        this.f4259k = str;
    }

    public void setTextDataId(String str) {
        this.f4258j = str;
    }

    public void setVersionMax(String str) {
        this.f4252d = str;
    }

    public void setVersionMin(String str) {
        this.f4251c = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(C0794g.ID.m6742b(), this.f4249a);
            jSONObject.putOpt(C0794g.NAME.m6742b(), this.f4250b);
            jSONObject.putOpt(C0794g.VERSION_MIN.m6742b(), this.f4251c);
            jSONObject.putOpt(C0794g.VERSION_MAX.m6742b(), this.f4252d);
            jSONObject.putOpt(C0794g.LANGUAGE.m6742b(), this.f4253e);
            jSONObject.putOpt(C0794g.GENDER.m6742b(), this.f4254f);
            jSONObject.putOpt(C0794g.SPEAKER.m6742b(), this.f4255g);
            jSONObject.putOpt(C0794g.DOMAIN.m6742b(), this.f4256h);
            jSONObject.putOpt(C0794g.QUALITY.m6742b(), this.f4257i);
            jSONObject.putOpt(C0794g.TEXT_DATA_ID.m6742b(), this.f4258j);
            jSONObject.putOpt(C0794g.SPEECH_DATA_ID.m6742b(), this.f4259k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
