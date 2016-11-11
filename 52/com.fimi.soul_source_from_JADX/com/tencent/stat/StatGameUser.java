package com.tencent.stat;

import org.p122a.p123a.C2915a;

public class StatGameUser implements Cloneable {
    private String f12227a;
    private String f12228b;
    private String f12229c;

    public StatGameUser() {
        this.f12227a = C2915a.f14760f;
        this.f12228b = C2915a.f14760f;
        this.f12229c = C2915a.f14760f;
    }

    public StatGameUser clone() {
        try {
            return (StatGameUser) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getAccount() {
        return this.f12228b;
    }

    public String getLevel() {
        return this.f12229c;
    }

    public String getWorldName() {
        return this.f12227a;
    }

    public void setAccount(String str) {
        this.f12228b = str;
    }

    public void setLevel(String str) {
        this.f12229c = str;
    }

    public void setWorldName(String str) {
        this.f12227a = str;
    }
}
