package com.p016a;

import android.content.Context;
import java.lang.reflect.Constructor;

/* renamed from: com.a.ak */
public class ak {
    public static <T> T m1032a(Context context, gd gdVar, String str, Class cls, Class[] clsArr, Object[] objArr) {
        aq a;
        try {
            a = aq.m1063a(context, gdVar, an.m1048a(context, gdVar.m1938a(), gdVar.m1940b()), an.m1045a(context), null, context.getClassLoader());
        } catch (Throwable th) {
            C0247g.m1917a(th, "InstanceFactory", "getInstance");
            a = null;
        }
        if (a != null) {
            try {
                if (a.m1074a() && a.f560a) {
                    Class loadClass = a.loadClass(str);
                    if (loadClass != null) {
                        T newInstance = loadClass.getConstructor(clsArr).newInstance(objArr);
                        return newInstance;
                    }
                }
            } catch (Throwable th2) {
                C0247g.m1917a(th2, "InstanceFactory", "getInstance()");
            } catch (Throwable th22) {
                C0247g.m1917a(th22, "InstanceFactory", "getInstance()");
            } catch (Throwable th222) {
                C0247g.m1917a(th222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222) {
                C0247g.m1917a(th2222, "InstanceFactory", "getInstance()");
            } catch (Throwable th22222) {
                C0247g.m1917a(th22222, "InstanceFactory", "getInstance()");
            } catch (Throwable th222222) {
                C0247g.m1917a(th222222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222222) {
                C0247g.m1917a(th2222222, "InstanceFactory", "getInstance()");
            }
        }
        try {
            Constructor constructor = cls.getConstructor(clsArr);
            constructor.setAccessible(true);
            newInstance = constructor.newInstance(objArr);
            return newInstance;
        } catch (Throwable th22222222) {
            C0247g.m1917a(th22222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        } catch (Throwable th222222222) {
            C0247g.m1917a(th222222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        } catch (Throwable th2222222222) {
            C0247g.m1917a(th2222222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        } catch (Throwable th22222222222) {
            C0247g.m1917a(th22222222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        } catch (Throwable th222222222222) {
            C0247g.m1917a(th222222222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        } catch (Throwable th2222222222222) {
            C0247g.m1917a(th2222222222222, "InstanceFactory", "getInstance()");
            throw new fm("\u83b7\u53d6\u5bf9\u8c61\u9519\u8bef");
        }
    }
}
