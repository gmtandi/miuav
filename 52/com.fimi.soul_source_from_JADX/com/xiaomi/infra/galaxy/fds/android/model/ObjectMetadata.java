package com.xiaomi.infra.galaxy.fds.android.model;

import com.xiaomi.infra.galaxy.fds.android.util.Consts;
import com.xiaomi.infra.galaxy.fds.android.util.Util;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.p122a.p123a.C3004e;

public class ObjectMetadata {
    private static final Set<String> PREDEFINED_HEADERS;
    private final Map<String, String> predefinedMetadata;
    private final Map<String, String> userMetadata;

    static {
        PREDEFINED_HEADERS = new HashSet();
        PREDEFINED_HEADERS.add(C3004e.f14995G);
        PREDEFINED_HEADERS.add(C3004e.f15029o);
        PREDEFINED_HEADERS.add(C3004e.f15031q);
        PREDEFINED_HEADERS.add(C3004e.f15027m);
        PREDEFINED_HEADERS.add(C3004e.f15025k);
        PREDEFINED_HEADERS.add(C3004e.f15023i);
    }

    public ObjectMetadata() {
        this.userMetadata = new HashMap();
        this.predefinedMetadata = new HashMap();
    }

    public static ObjectMetadata parseObjectMetadata(Header[] headerArr) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        for (Header header : headerArr) {
            String name = header.getName();
            String value = header.getValue();
            if (name.startsWith(Consts.XIAOMI_META_HEADER_PREFIX)) {
                objectMetadata.addUserMetadata(name, value);
            } else if (PREDEFINED_HEADERS.contains(name)) {
                objectMetadata.addPredefinedMetadata(name, value);
            }
        }
        return objectMetadata;
    }

    public void addPredefinedMetadata(String str, String str2) {
        this.predefinedMetadata.put(str, str2);
    }

    public void addUserMetadata(String str, String str2) {
        this.userMetadata.put(str, str2);
    }

    public Map<String, String> getAllMetadata() {
        Map<String, String> hashMap = new HashMap(this.predefinedMetadata);
        hashMap.putAll(this.userMetadata);
        return hashMap;
    }

    public String getCacheControl() {
        return (String) this.predefinedMetadata.get(C3004e.f15023i);
    }

    public String getContentEncoding() {
        return (String) this.predefinedMetadata.get(C3004e.f15025k);
    }

    public long getContentLength() {
        String str = (String) this.predefinedMetadata.get(C3004e.f15027m);
        return str != null ? Long.parseLong(str) : -1;
    }

    public String getContentMD5() {
        return (String) this.predefinedMetadata.get(C3004e.f15029o);
    }

    public String getContentType() {
        return (String) this.predefinedMetadata.get(C3004e.f15031q);
    }

    public Date getLastModified() {
        String str = (String) this.predefinedMetadata.get(C3004e.f14995G);
        if (str == null) {
            return null;
        }
        try {
            return Util.parseDate(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public void setCacheControl(String str) {
        this.predefinedMetadata.put(C3004e.f15023i, str);
    }

    public void setContentEncoding(String str) {
        this.predefinedMetadata.put(C3004e.f15025k, str);
    }

    public void setContentLength(long j) {
        this.predefinedMetadata.put(C3004e.f15027m, Long.toString(j));
    }

    public void setContentMD5(String str) {
        this.predefinedMetadata.put(C3004e.f15029o, str);
    }

    public void setContentType(String str) {
        this.predefinedMetadata.put(C3004e.f15031q, str);
    }

    public void setLastModified(Date date) {
        this.predefinedMetadata.put(C3004e.f14995G, Util.formatDateString(date));
    }
}
