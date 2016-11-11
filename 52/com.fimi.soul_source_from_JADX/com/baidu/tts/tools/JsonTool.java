package com.baidu.tts.tools;

import java.util.Set;
import org.json.JSONArray;

public class JsonTool {
    public static JSONArray fromSetToJson(Set<String> set) {
        return set != null ? new JSONArray(set) : null;
    }

    public static String[] getStringarray(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }
}
