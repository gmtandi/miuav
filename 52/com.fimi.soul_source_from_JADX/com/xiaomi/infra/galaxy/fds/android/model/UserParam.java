package com.xiaomi.infra.galaxy.fds.android.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;

public class UserParam {
    protected final Map<String, String> params;

    public UserParam() {
        this.params = new HashMap();
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(this.params);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Entry entry : this.params.entrySet()) {
            if (i != 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append((String) entry.getKey());
            String str = (String) entry.getValue();
            if (str != null) {
                stringBuilder.append(SignatureVisitor.INSTANCEOF);
                stringBuilder.append(str);
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
