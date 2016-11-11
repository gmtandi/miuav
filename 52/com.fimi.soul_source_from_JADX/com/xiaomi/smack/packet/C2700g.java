package com.xiaomi.smack.packet;

/* renamed from: com.xiaomi.smack.packet.g */
public class C2700g {
    private String f13354a;

    public C2700g(String str) {
        this.f13354a = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stream:error (").append(this.f13354a).append(")");
        return stringBuilder.toString();
    }
}
