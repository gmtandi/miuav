package com.baidu.tts.client.model;

import com.baidu.tts.tools.StringTool;
import java.util.HashSet;
import java.util.Set;

public class AvailableConditions {
    private Set<String> f4219a;
    private Set<String> f4220b;

    public void appendGender(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4219a == null) {
                this.f4219a = new HashSet();
            }
            this.f4219a.add(str);
        }
    }

    public void appendSpeaker(String str) {
        if (!StringTool.isEmpty(str)) {
            if (this.f4220b == null) {
                this.f4220b = new HashSet();
            }
            this.f4220b.add(str);
        }
    }

    public Set<String> getGenders() {
        return this.f4219a;
    }

    public Set<String> getSpeakers() {
        return this.f4220b;
    }

    public void setGenders(Set<String> set) {
        this.f4219a = set;
    }

    public void setSpeakers(Set<String> set) {
        this.f4220b = set;
    }
}
