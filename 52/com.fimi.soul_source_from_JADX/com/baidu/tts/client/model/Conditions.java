package com.baidu.tts.client.model;

import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.JsonTool;
import com.baidu.tts.tools.StringTool;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Conditions {
    private Set<String> f4222a;
    private String f4223b;
    private Set<String> f4224c;
    private Set<String> f4225d;
    private Set<String> f4226e;
    private Set<String> f4227f;
    private Set<String> f4228g;

    public void appendDomain(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4227f == null) {
                this.f4227f = new HashSet();
            }
            this.f4227f.add(str);
        }
    }

    public void appendGender(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4225d == null) {
                this.f4225d = new HashSet();
            }
            this.f4225d.add(str);
        }
    }

    public void appendId(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4222a == null) {
                this.f4222a = new HashSet();
            }
            this.f4222a.add(str);
        }
    }

    public void appendLanguage(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4224c == null) {
                this.f4224c = new HashSet();
            }
            this.f4224c.add(str);
        }
    }

    public void appendQuality(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4228g == null) {
                this.f4228g = new HashSet();
            }
            this.f4228g.add(str);
        }
    }

    public void appendSpeaker(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4226e == null) {
                this.f4226e = new HashSet();
            }
            this.f4226e.add(str);
        }
    }

    public String[] getDomainArray() {
        return DataTool.fromSetToArray(this.f4227f);
    }

    public Set<String> getDomains() {
        return this.f4227f;
    }

    public String[] getGenderArray() {
        return DataTool.fromSetToArray(this.f4225d);
    }

    public JSONArray getGenderJA() {
        return JsonTool.fromSetToJson(this.f4225d);
    }

    public Set<String> getGenders() {
        return this.f4225d;
    }

    public JSONObject getJSONConditions() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C0794g.ID.m6742b(), JsonTool.fromSetToJson(this.f4222a));
            jSONObject.put(C0794g.VERSION.m6742b(), this.f4223b);
            jSONObject.put(C0794g.LANGUAGE.m6742b(), JsonTool.fromSetToJson(this.f4224c));
            jSONObject.put(C0794g.GENDER.m6742b(), JsonTool.fromSetToJson(this.f4225d));
            jSONObject.put(C0794g.SPEAKER.m6742b(), JsonTool.fromSetToJson(this.f4226e));
            jSONObject.put(C0794g.DOMAIN.m6742b(), JsonTool.fromSetToJson(this.f4227f));
            jSONObject.put(C0794g.QUALITY.m6742b(), JsonTool.fromSetToJson(this.f4228g));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String[] getLanguageArray() {
        return DataTool.fromSetToArray(this.f4224c);
    }

    public Set<String> getLanguages() {
        return this.f4224c;
    }

    public Set<String> getModelIds() {
        return this.f4222a;
    }

    public String[] getModelIdsArray() {
        return DataTool.fromSetToArray(this.f4222a);
    }

    public String[] getQualityArray() {
        return DataTool.fromSetToArray(this.f4228g);
    }

    public Set<String> getQualitys() {
        return this.f4228g;
    }

    public String[] getSpeakerArray() {
        return DataTool.fromSetToArray(this.f4226e);
    }

    public JSONArray getSpeakerJA() {
        return JsonTool.fromSetToJson(this.f4226e);
    }

    public Set<String> getSpeakers() {
        return this.f4226e;
    }

    public String getVersion() {
        return this.f4223b;
    }

    public void setDomains(Set<String> set) {
        this.f4227f = set;
    }

    public void setDomains(String[] strArr) {
        this.f4227f = DataTool.fromArrayToSet(strArr);
    }

    public void setGenders(Set<String> set) {
        this.f4225d = set;
    }

    public void setLanguages(Set<String> set) {
        this.f4224c = set;
    }

    public void setLanguages(String[] strArr) {
        this.f4224c = DataTool.fromArrayToSet(strArr);
    }

    public void setModelIds(Set<String> set) {
        this.f4222a = set;
    }

    public void setQualitys(Set<String> set) {
        this.f4228g = set;
    }

    public void setQualitys(String[] strArr) {
        this.f4228g = DataTool.fromArrayToSet(strArr);
    }

    public void setSpeakers(Set<String> set) {
        this.f4226e = set;
    }

    public void setVersion(String str) {
        this.f4223b = str;
    }
}
