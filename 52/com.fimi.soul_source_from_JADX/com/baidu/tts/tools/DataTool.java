package com.baidu.tts.tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataTool {
    public static String[] connect(String[] strArr, String[]... strArr2) {
        Object[] objArr;
        if (strArr == null) {
            objArr = new String[0];
        }
        int length = objArr.length;
        for (String[] length2 : strArr2) {
            try {
                length += length2.length;
            } catch (Exception e) {
            }
        }
        String[] strArr3 = (String[]) Arrays.copyOf(objArr, length);
        int length3 = objArr.length;
        int i = length3;
        for (Object obj : strArr2) {
            try {
                System.arraycopy(obj, 0, strArr3, i, obj.length);
                i += obj.length;
            } catch (Exception e2) {
            }
        }
        return strArr3;
    }

    public static Set<String> copy(Set<String> set) {
        if (set == null) {
            return null;
        }
        Set<String> hashSet = new HashSet();
        for (String add : set) {
            hashSet.add(add);
        }
        return hashSet;
    }

    public static Set<String> fromArrayToSet(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        Set<String> hashSet = new HashSet();
        for (Object add : strArr) {
            hashSet.add(add);
        }
        return hashSet;
    }

    public static String[] fromSetToArray(Set<String> set) {
        if (set == null) {
            return null;
        }
        String[] strArr = new String[set.size()];
        set.toArray(strArr);
        return strArr;
    }

    public static String getMapInnerValue(Map<String, Map<String, String>> map, String str, String str2) {
        if (map == null) {
            return null;
        }
        Map map2 = (Map) map.get(str);
        return map2 != null ? (String) map2.get(str2) : null;
    }

    public static String getMapValue(Map<String, String> map, String str) {
        return (map == null || map.isEmpty()) ? null : (String) map.get(str);
    }

    public static Map<String, Map<String, String>> getSuitItem(Map<String, Map<String, String>> map, String str, boolean z, String str2) {
        if (isMapEmpty(map)) {
            return null;
        }
        Map<String, Map<String, String>> hashMap = new HashMap();
        for (String str3 : map.keySet()) {
            Map map2 = (Map) map.get(str3);
            String str4 = (String) map2.get(str);
            if (z) {
                if (str2.equals(str4)) {
                    hashMap.put(str3, map2);
                }
            } else if (!str2.equals(str4)) {
                hashMap.put(str3, map2);
            }
        }
        return hashMap;
    }

    public static Map<String, Integer> getSuitItem(Map<String, Integer> map, boolean z, int i) {
        if (isMapEmpty(map)) {
            return null;
        }
        Map<String, Integer> hashMap = new HashMap();
        for (String str : map.keySet()) {
            int intValue = ((Integer) map.get(str)).intValue();
            if (z) {
                if (i == intValue) {
                    hashMap.put(str, Integer.valueOf(intValue));
                }
            } else if (i != intValue) {
                hashMap.put(str, Integer.valueOf(intValue));
            }
        }
        return hashMap;
    }

    public static <T extends List<?>> boolean isListEmpty(T t) {
        return t == null || t.isEmpty();
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T extends Map<?, ?>> boolean isMapEmpty(T t) {
        return t == null || t.isEmpty();
    }

    public static <T extends Set<?>> boolean isSetEmpty(T t) {
        return t == null || t.isEmpty();
    }

    public static Map<String, String> putIfAbsent(Map<String, Map<String, String>> map, String str) {
        Map<String, String> map2 = (Map) map.get(str);
        if (map2 != null) {
            return map2;
        }
        Map hashMap = new HashMap();
        map.put(str, hashMap);
        return hashMap;
    }

    public static void putMapItem(Map<String, Map<String, String>> map, String str, Map<String, String> map2) {
        Map map3 = (Map) map.get(str);
        if (map3 == null) {
            map.put(str, map2);
        } else {
            map3.putAll(map2);
        }
    }

    public static void putMapValue(Map<String, Map<String, String>> map, String str, String str2, String str3) {
        putIfAbsent(map, str).put(str2, str3);
    }
}
