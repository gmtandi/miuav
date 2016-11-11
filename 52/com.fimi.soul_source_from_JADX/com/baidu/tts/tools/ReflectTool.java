package com.baidu.tts.tools;

import java.lang.reflect.Method;

public class ReflectTool {
    public static Method getSupportedMethod(Class<?> cls, String str, Class<?>[] clsArr) {
        if (cls == null) {
            throw new NoSuchMethodException();
        }
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return getSupportedMethod(cls.getSuperclass(), str, clsArr);
        }
    }
}
