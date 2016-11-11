package p147m.framework.p149b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: m.framework.b.d */
public class C2860d {
    private String m16496a(String str, ArrayList<Object> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[\n");
        String stringBuilder = new StringBuilder(String.valueOf(str)).append("\t").toString();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (i > 0) {
                stringBuffer.append(",\n");
            }
            stringBuffer.append(stringBuilder);
            if (next instanceof HashMap) {
                stringBuffer.append(m16497a(stringBuilder, (HashMap) next));
            } else if (next instanceof ArrayList) {
                stringBuffer.append(m16496a(stringBuilder, (ArrayList) next));
            } else if (next instanceof String) {
                stringBuffer.append(C3022o.f15057e).append(next).append(C3022o.f15057e);
            } else {
                stringBuffer.append(next);
            }
            i++;
        }
        stringBuffer.append('\n').append(str).append(']');
        return stringBuffer.toString();
    }

    private String m16497a(String str, HashMap<String, Object> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{\n");
        String stringBuilder = new StringBuilder(String.valueOf(str)).append("\t").toString();
        int i = 0;
        for (Entry entry : hashMap.entrySet()) {
            if (i > 0) {
                stringBuffer.append(",\n");
            }
            stringBuffer.append(stringBuilder).append(C3022o.f15057e).append((String) entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                stringBuffer.append(m16497a(stringBuilder, (HashMap) value));
            } else if (value instanceof ArrayList) {
                stringBuffer.append(m16496a(stringBuilder, (ArrayList) value));
            } else if (value instanceof String) {
                stringBuffer.append(C3022o.f15057e).append(value).append(C3022o.f15057e);
            } else {
                stringBuffer.append(value);
            }
            i++;
        }
        stringBuffer.append('\n').append(str).append('}');
        return stringBuffer.toString();
    }

    private ArrayList<Object> m16498a(JSONArray jSONArray) {
        ArrayList<Object> arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                opt = m16499a((JSONObject) opt);
            } else if (opt instanceof JSONArray) {
                opt = m16498a((JSONArray) opt);
            }
            arrayList.add(opt);
        }
        return arrayList;
    }

    private HashMap<String, Object> m16499a(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (JSONObject.NULL.equals(opt)) {
                opt = null;
            }
            if (opt != null) {
                if (opt instanceof JSONObject) {
                    opt = m16499a((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = m16498a((JSONArray) opt);
                }
                hashMap.put(str, opt);
            }
        }
        return hashMap;
    }

    private JSONArray m16500a(ArrayList<Object> arrayList) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof HashMap) {
                next = m16503b((HashMap) next);
            } else if (next instanceof ArrayList) {
                next = m16500a((ArrayList) next);
            }
            jSONArray.put(next);
        }
        return jSONArray;
    }

    private boolean m16501a(Object obj) {
        return (obj instanceof byte[]) || (obj instanceof short[]) || (obj instanceof int[]) || (obj instanceof long[]) || (obj instanceof float[]) || (obj instanceof double[]) || (obj instanceof char[]) || (obj instanceof boolean[]) || (obj instanceof String[]);
    }

    private ArrayList<?> m16502b(Object obj) {
        int i = 0;
        ArrayList<?> arrayList;
        int length;
        if (obj instanceof byte[]) {
            arrayList = new ArrayList();
            byte[] bArr = (byte[]) obj;
            length = bArr.length;
            while (i < length) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof short[]) {
            arrayList = new ArrayList();
            short[] sArr = (short[]) obj;
            length = sArr.length;
            while (i < length) {
                arrayList.add(Short.valueOf(sArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof int[]) {
            arrayList = new ArrayList();
            int[] iArr = (int[]) obj;
            length = iArr.length;
            while (i < length) {
                arrayList.add(Integer.valueOf(iArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof long[]) {
            arrayList = new ArrayList();
            long[] jArr = (long[]) obj;
            length = jArr.length;
            while (i < length) {
                arrayList.add(Long.valueOf(jArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof float[]) {
            arrayList = new ArrayList();
            float[] fArr = (float[]) obj;
            length = fArr.length;
            while (i < length) {
                arrayList.add(Float.valueOf(fArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof double[]) {
            arrayList = new ArrayList();
            double[] dArr = (double[]) obj;
            length = dArr.length;
            while (i < length) {
                arrayList.add(Double.valueOf(dArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof char[]) {
            arrayList = new ArrayList();
            char[] cArr = (char[]) obj;
            length = cArr.length;
            while (i < length) {
                arrayList.add(Character.valueOf(cArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof boolean[]) {
            arrayList = new ArrayList();
            boolean[] zArr = (boolean[]) obj;
            length = zArr.length;
            while (i < length) {
                arrayList.add(Boolean.valueOf(zArr[i]));
                i++;
            }
            return arrayList;
        } else if (!(obj instanceof String[])) {
            return null;
        } else {
            arrayList = new ArrayList();
            String[] strArr = (String[]) obj;
            length = strArr.length;
            while (i < length) {
                arrayList.add(strArr[i]);
                i++;
            }
            return arrayList;
        }
    }

    private JSONObject m16503b(HashMap<String, Object> hashMap) {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                value = m16503b((HashMap) value);
            } else if (value instanceof ArrayList) {
                value = m16500a((ArrayList) value);
            } else if (m16501a(value)) {
                value = m16500a(m16502b(value));
            }
            jSONObject.put((String) entry.getKey(), value);
        }
        return jSONObject;
    }

    public String m16504a(HashMap<String, Object> hashMap) {
        try {
            return m16503b((HashMap) hashMap).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    public HashMap<String, Object> m16505a(String str) {
        try {
            if (str.startsWith("[") && str.endsWith("]")) {
                str = "{\"fakelist\":" + str + "}";
            }
            return m16499a(new JSONObject(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return new HashMap();
        }
    }

    public String m16506b(String str) {
        try {
            return m16497a(C2915a.f14760f, m16505a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }
}
