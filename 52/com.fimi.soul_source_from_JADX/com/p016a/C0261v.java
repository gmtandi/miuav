package com.p016a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.v */
public class C0261v {
    private C0262x f1313a;
    private SQLiteDatabase f1314b;
    private C0240u f1315c;

    public C0261v(Context context, C0240u c0240u) {
        try {
            this.f1313a = new C0262x(context, c0240u.m1076a(), null, c0240u.m1079b(), c0240u);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f1315c = c0240u;
    }

    private SQLiteDatabase m2041a(boolean z) {
        try {
            this.f1314b = this.f1313a.getReadableDatabase();
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                C0247g.m1917a(th, "DBOperation", "getReadAbleDataBase");
            }
        }
        return this.f1314b;
    }

    public static String m2042a(Map<String, String> map) {
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

    private <T> void m2043a(SQLiteDatabase sQLiteDatabase, C0239w<T> c0239w) {
        if (c0239w != null && sQLiteDatabase != null) {
            ContentValues a = c0239w.m963a();
            if (a != null && c0239w.m966b() != null) {
                sQLiteDatabase.insert(c0239w.m966b(), null, a);
            }
        }
    }

    private SQLiteDatabase m2044b(boolean z) {
        try {
            this.f1314b = this.f1313a.getWritableDatabase();
        } catch (Throwable th) {
            C0247g.m1917a(th, "DBOperation", "getReadAbleDataBase");
        }
        return this.f1314b;
    }

    public <T> void m2045a(C0239w<T> c0239w) {
        m2047a((C0239w) c0239w, false);
    }

    public <T> void m2046a(C0239w<T> c0239w, String str) {
        synchronized (this.f1315c) {
            List c = m2052c(str, c0239w);
            if (c == null || c.size() == 0) {
                m2045a((C0239w) c0239w);
            } else {
                m2051b(str, c0239w);
            }
        }
    }

    public <T> void m2047a(C0239w<T> c0239w, boolean z) {
        synchronized (this.f1315c) {
            if (this.f1314b == null || this.f1314b.isReadOnly()) {
                this.f1314b = m2044b(z);
            }
            if (this.f1314b == null) {
                return;
            }
            try {
                m2043a(this.f1314b, (C0239w) c0239w);
                if (this.f1314b != null) {
                    this.f1314b.close();
                    this.f1314b = null;
                }
            } catch (Throwable th) {
                if (this.f1314b != null) {
                    this.f1314b.close();
                    this.f1314b = null;
                }
            }
        }
    }

    public <T> void m2048a(String str, C0239w<T> c0239w) {
        synchronized (this.f1315c) {
            if (c0239w.m966b() == null || str == null) {
                return;
            }
            if (this.f1314b == null || this.f1314b.isReadOnly()) {
                this.f1314b = m2044b(false);
            }
            if (this.f1314b == null) {
                return;
            }
            try {
                this.f1314b.delete(c0239w.m966b(), str, null);
                if (this.f1314b != null) {
                    this.f1314b.close();
                    this.f1314b = null;
                }
            } catch (Throwable th) {
                if (this.f1314b != null) {
                    this.f1314b.close();
                    this.f1314b = null;
                }
            }
        }
    }

    public <T> void m2049a(String str, C0239w<T> c0239w, boolean z) {
        synchronized (this.f1315c) {
            if (!(c0239w == null || str == null)) {
                if (c0239w.m966b() != null) {
                    ContentValues a = c0239w.m963a();
                    if (a == null) {
                        return;
                    }
                    if (this.f1314b == null || this.f1314b.isReadOnly()) {
                        this.f1314b = m2044b(z);
                    }
                    if (this.f1314b == null) {
                        return;
                    }
                    try {
                        this.f1314b.update(c0239w.m966b(), a, str, null);
                        if (this.f1314b != null) {
                            this.f1314b.close();
                            this.f1314b = null;
                        }
                    } catch (Throwable th) {
                        if (this.f1314b != null) {
                            this.f1314b.close();
                            this.f1314b = null;
                        }
                    }
                    return;
                }
            }
        }
    }

    public <T> List<T> m2050b(String str, C0239w<T> c0239w, boolean z) {
        Cursor query;
        Throwable th;
        synchronized (this.f1315c) {
            List<T> arrayList = new ArrayList();
            if (this.f1314b == null) {
                this.f1314b = m2041a(z);
            }
            if (this.f1314b == null || c0239w.m966b() == null || str == null) {
                return arrayList;
            }
            try {
                query = this.f1314b.query(c0239w.m966b(), null, str, null, null, null, null);
                if (query == null) {
                    try {
                        this.f1314b.close();
                        this.f1314b = null;
                        if (query != null) {
                            try {
                                query.close();
                            } catch (Throwable th2) {
                                if (!z) {
                                    C0247g.m1917a(th2, "DataBase", "searchListData");
                                }
                            }
                        }
                        try {
                            if (this.f1314b != null) {
                                this.f1314b.close();
                                this.f1314b = null;
                            }
                        } catch (Throwable th22) {
                            if (!z) {
                                C0247g.m1917a(th22, "DataBase", "searchListData");
                            }
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        th22 = th3;
                    }
                } else {
                    while (query.moveToNext()) {
                        arrayList.add(c0239w.m964a(query));
                    }
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th222) {
                            if (!z) {
                                C0247g.m1917a(th222, "DataBase", "searchListData");
                            }
                        }
                    }
                    try {
                        if (this.f1314b != null) {
                            this.f1314b.close();
                            this.f1314b = null;
                        }
                    } catch (Throwable th2222) {
                        if (!z) {
                            C0247g.m1917a(th2222, "DataBase", "searchListData");
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
                if (this.f1314b != null) {
                    this.f1314b.close();
                    this.f1314b = null;
                }
                throw th2222;
            }
        }
    }

    public <T> void m2051b(String str, C0239w<T> c0239w) {
        m2049a(str, c0239w, false);
    }

    public <T> List<T> m2052c(String str, C0239w<T> c0239w) {
        return m2050b(str, c0239w, false);
    }
}
