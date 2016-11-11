package com.baidu.tts.tools;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class SqlTool {
    private static String m6956a(Method method, Object obj) {
        try {
            return (String) method.invoke(obj, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static String m6957a(Method method, Method method2, Object obj) {
        String a = m6956a(method, obj);
        if (a != null) {
            String a2 = m6956a(method2, obj);
            if (a2 != null) {
                return a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a2;
            }
        }
        return null;
    }

    public static String addPlaceholders(int i) {
        int i2 = 1;
        if (i < 1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        while (i2 < i) {
            stringBuilder.append(",?");
            i2++;
        }
        return stringBuilder.toString();
    }

    public static String buildConditions(String str, String... strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (String str2 : strArr) {
            if (!StringTool.isEmpty(str2)) {
                arrayList.add(str2);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            stringBuilder.append((String) it.next());
        }
        while (it.hasNext()) {
            String str3 = (String) it.next();
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuilder.append(str3);
        }
        return stringBuilder.toString();
    }

    public static String buildInCondition(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append(" in (");
        stringBuilder.append(addPlaceholders(strArr.length));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String[] getSQLformat(String str, String[] strArr, String[] strArr2, String[] strArr3) {
        Object obj = new String[(((strArr.length + 1) + strArr2.length) + strArr3.length)];
        obj[0] = str;
        System.arraycopy(strArr, 0, obj, 1, strArr.length);
        System.arraycopy(strArr2, 0, obj, strArr.length + 1, strArr2.length);
        System.arraycopy(strArr3, 0, obj, (strArr.length + 1) + strArr2.length, strArr3.length);
        return obj;
    }

    public static String sqlCreateTable(String str, Object[] objArr) {
        String str2 = null;
        if (!(str == null || objArr == null)) {
            Object obj = objArr[0];
            Class cls = obj.getClass();
            try {
                Method supportedMethod = ReflectTool.getSupportedMethod(cls, "getColumnName", null);
                Method supportedMethod2 = ReflectTool.getSupportedMethod(cls, "getDataType", null);
                StringBuilder stringBuilder = new StringBuilder("create Table " + str);
                String a = m6957a(supportedMethod, supportedMethod2, obj);
                if (a != null) {
                    stringBuilder.append(" (" + a);
                    int length = objArr.length;
                    for (int i = 1; i < length; i++) {
                        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        stringBuilder.append(m6957a(supportedMethod, supportedMethod2, objArr[i]));
                    }
                    stringBuilder.append(")");
                    str2 = stringBuilder.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static String sqlDropTable(String str) {
        return "drop table if exists " + str;
    }
}
