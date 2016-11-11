package com.amap.api.mapcore.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class ck {
    private static Map<Class<? extends cj>, cj> f2342d;
    private cp f2343a;
    private SQLiteDatabase f2344b;
    private cj f2345c;

    static {
        f2342d = new HashMap();
    }

    public ck(Context context, cj cjVar) {
        try {
            this.f2343a = new cp(context, cjVar.m3880b(), null, cjVar.m3881c(), cjVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f2345c = cjVar;
    }

    private ContentValues m3882a(Object obj, cl clVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : m3891a(obj.getClass(), clVar.m3904b())) {
            field.setAccessible(true);
            m3889a(obj, field, contentValues);
        }
        return contentValues;
    }

    private SQLiteDatabase m3883a(boolean z) {
        try {
            if (this.f2344b != null) {
                this.f2344b.close();
            }
            this.f2344b = this.f2343a.getReadableDatabase();
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                cb.m3809a(th, "DBOperation", "getReadAbleDataBase");
            }
        }
        return this.f2344b;
    }

    public static synchronized cj m3884a(Class<? extends cj> cls) {
        cj cjVar;
        synchronized (ck.class) {
            if (f2342d.get(cls) == null) {
                f2342d.put(cls, cls.newInstance());
            }
            cjVar = (cj) f2342d.get(cls);
        }
        return cjVar;
    }

    private <T> T m3885a(Cursor cursor, Class<T> cls, cl clVar) {
        Field[] a = m3891a((Class) cls, clVar.m3904b());
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(cm.class);
            if (annotation != null) {
                cm cmVar = (cm) annotation;
                int b = cmVar.m3906b();
                int columnIndex = cursor.getColumnIndex(cmVar.m3905a());
                switch (b) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case Type.BYTE /*3*/:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case Type.INT /*5*/:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case Type.FLOAT /*6*/:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case Type.LONG /*7*/:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                    default:
                        break;
                }
            }
        }
        return newInstance;
    }

    private <T> String m3886a(cl clVar) {
        return clVar == null ? null : clVar.m3903a();
    }

    public static String m3887a(Map<String, String> map) {
        if (map == null) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : map.keySet()) {
            Object obj2;
            if (obj != null) {
                stringBuilder.append(str).append(" = '").append((String) map.get(str)).append("'");
                obj2 = null;
            } else {
                stringBuilder.append(" and ").append(str).append(" = '").append((String) map.get(str)).append("'");
                obj2 = obj;
            }
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    private <T> void m3888a(SQLiteDatabase sQLiteDatabase, T t) {
        cl b = m3893b(t.getClass());
        Object a = m3886a(b);
        if (!TextUtils.isEmpty(a) && t != null && sQLiteDatabase != null) {
            ContentValues a2 = m3882a((Object) t, b);
            if (a2 != null) {
                sQLiteDatabase.insert(a, null, a2);
            }
        }
    }

    private void m3889a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(cm.class);
        if (annotation != null) {
            cm cmVar = (cm) annotation;
            switch (cmVar.m3906b()) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    try {
                        contentValues.put(cmVar.m3905a(), Short.valueOf(field.getShort(obj)));
                        return;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    contentValues.put(cmVar.m3905a(), Integer.valueOf(field.getInt(obj)));
                    return;
                case Type.BYTE /*3*/:
                    contentValues.put(cmVar.m3905a(), Float.valueOf(field.getFloat(obj)));
                    return;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    contentValues.put(cmVar.m3905a(), Double.valueOf(field.getDouble(obj)));
                    return;
                case Type.INT /*5*/:
                    contentValues.put(cmVar.m3905a(), Long.valueOf(field.getLong(obj)));
                    return;
                case Type.FLOAT /*6*/:
                    String str = C2915a.f14760f;
                    contentValues.put(cmVar.m3905a(), (String) field.get(obj));
                    return;
                case Type.LONG /*7*/:
                    contentValues.put(cmVar.m3905a(), (byte[]) field.get(obj));
                    return;
                default:
                    return;
            }
            e.printStackTrace();
        }
    }

    private boolean m3890a(Annotation annotation) {
        return annotation != null;
    }

    private Field[] m3891a(Class<?> cls, boolean z) {
        return cls == null ? null : z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    private SQLiteDatabase m3892b(boolean z) {
        try {
            if (this.f2344b == null || this.f2344b.isReadOnly()) {
                if (this.f2344b != null) {
                    this.f2344b.close();
                }
                this.f2344b = this.f2343a.getWritableDatabase();
            }
        } catch (Throwable th) {
            cb.m3809a(th, "DBOperation", "getReadAbleDataBase");
        }
        return this.f2344b;
    }

    private <T> cl m3893b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(cl.class);
        return !m3890a(annotation) ? null : (cl) annotation;
    }

    public <T> List<T> m3894a(String str, Class<T> cls, boolean z) {
        Throwable th;
        synchronized (this.f2345c) {
            List<T> arrayList = new ArrayList();
            cl b = m3893b((Class) cls);
            Object a = m3886a(b);
            if (this.f2344b == null) {
                this.f2344b = m3883a(z);
            }
            if (this.f2344b == null || TextUtils.isEmpty(a) || str == null) {
                return arrayList;
            }
            Cursor query;
            try {
                query = this.f2344b.query(a, null, str, null, null, null, null);
                if (query == null) {
                    try {
                        this.f2344b.close();
                        this.f2344b = null;
                        if (query != null) {
                            try {
                                query.close();
                            } catch (Throwable th2) {
                                if (!z) {
                                    cb.m3809a(th2, "DataBase", "searchListData");
                                }
                            }
                        }
                        try {
                            if (this.f2344b != null) {
                                this.f2344b.close();
                                this.f2344b = null;
                            }
                        } catch (Throwable th22) {
                            if (!z) {
                                cb.m3809a(th22, "DataBase", "searchListData");
                            }
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        th22 = th3;
                    }
                } else {
                    while (query.moveToNext()) {
                        arrayList.add(m3885a(query, (Class) cls, b));
                    }
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th222) {
                            if (!z) {
                                cb.m3809a(th222, "DataBase", "searchListData");
                            }
                        }
                    }
                    try {
                        if (this.f2344b != null) {
                            this.f2344b.close();
                            this.f2344b = null;
                        }
                    } catch (Throwable th2222) {
                        if (!z) {
                            cb.m3809a(th2222, "DataBase", "searchListData");
                        }
                    }
                    return arrayList;
                }
            } catch (Throwable th4) {
                th2222 = th4;
                query = null;
                if (query != null) {
                    query.close();
                }
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
                throw th2222;
            }
        }
    }

    public <T> void m3895a(T t) {
        m3897a((Object) t, false);
    }

    public void m3896a(Object obj, String str) {
        synchronized (this.f2345c) {
            List b = m3902b(str, obj.getClass());
            if (b == null || b.size() == 0) {
                m3895a(obj);
            } else {
                m3899a(str, obj);
            }
        }
    }

    public <T> void m3897a(T t, boolean z) {
        synchronized (this.f2345c) {
            this.f2344b = m3892b(z);
            if (this.f2344b == null) {
                return;
            }
            try {
                m3888a(this.f2344b, (Object) t);
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            } catch (Throwable th) {
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            }
        }
    }

    public <T> void m3898a(String str, Class<T> cls) {
        synchronized (this.f2345c) {
            Object a = m3886a(m3893b((Class) cls));
            if (TextUtils.isEmpty(a)) {
                return;
            }
            this.f2344b = m3892b(false);
            if (this.f2344b == null) {
                return;
            }
            try {
                this.f2344b.delete(a, str, null);
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            } catch (Throwable th) {
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            }
        }
    }

    public <T> void m3899a(String str, Object obj) {
        m3900a(str, obj, false);
    }

    public <T> void m3900a(String str, Object obj, boolean z) {
        synchronized (this.f2345c) {
            if (obj == null) {
                return;
            }
            cl b = m3893b(obj.getClass());
            Object a = m3886a(b);
            if (TextUtils.isEmpty(a)) {
                return;
            }
            ContentValues a2 = m3882a(obj, b);
            if (a2 == null) {
                return;
            }
            this.f2344b = m3892b(z);
            if (this.f2344b == null) {
                return;
            }
            try {
                this.f2344b.update(a, a2, str, null);
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            } catch (Throwable th) {
                if (this.f2344b != null) {
                    this.f2344b.close();
                    this.f2344b = null;
                }
            }
            return;
        }
    }

    public <T> void m3901a(List<T> list) {
        synchronized (this.f2345c) {
            if (list == null || list.size() == 0) {
                return;
            }
            this.f2344b = m3892b(false);
            if (this.f2344b == null) {
                return;
            }
            try {
                this.f2344b.beginTransaction();
                for (T a : list) {
                    m3888a(this.f2344b, (Object) a);
                }
                this.f2344b.setTransactionSuccessful();
            } catch (Throwable th) {
                cb.m3809a(th, "DataBase", "insertListData");
            } finally {
                this.f2344b.endTransaction();
                this.f2344b.close();
                this.f2344b = null;
            }
        }
    }

    public <T> List<T> m3902b(String str, Class<T> cls) {
        return m3894a(str, (Class) cls, false);
    }
}
