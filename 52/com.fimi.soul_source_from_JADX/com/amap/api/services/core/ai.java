package com.amap.api.services.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ai {
    private ah f3004a;
    private SQLiteDatabase f3005b;

    public ai(Context context) {
        this.f3004a = new ah(context, "logdb.db", null, 1);
    }

    private SQLiteDatabase m4521a() {
        this.f3005b = this.f3004a.getReadableDatabase();
        return this.f3005b;
    }

    private SQLiteDatabase m4522b() {
        this.f3005b = this.f3004a.getWritableDatabase();
        return this.f3005b;
    }

    public <T> void m4523a(ap<T> apVar) {
        if (apVar != null) {
            ContentValues b = apVar.m4510b();
            if (b != null && apVar.m4508a() != null) {
                if (this.f3005b == null || this.f3005b.isReadOnly()) {
                    this.f3005b = m4522b();
                }
                if (this.f3005b != null) {
                    try {
                        this.f3005b.insert(apVar.m4508a(), null, b);
                        if (this.f3005b != null) {
                            this.f3005b.close();
                            this.f3005b = null;
                        }
                    } catch (Throwable th) {
                        if (this.f3005b != null) {
                            this.f3005b.close();
                            this.f3005b = null;
                        }
                    }
                }
            }
        }
    }

    public <T> void m4524a(String str, ap<T> apVar) {
        if (apVar.m4508a() != null && str != null) {
            if (this.f3005b == null || this.f3005b.isReadOnly()) {
                this.f3005b = m4522b();
            }
            if (this.f3005b != null) {
                try {
                    this.f3005b.delete(apVar.m4508a(), str, null);
                    if (this.f3005b != null) {
                        this.f3005b.close();
                        this.f3005b = null;
                    }
                } catch (Throwable th) {
                    if (this.f3005b != null) {
                        this.f3005b.close();
                        this.f3005b = null;
                    }
                }
            }
        }
    }

    public <T> void m4525b(String str, ap<T> apVar) {
        if (apVar != null && str != null && apVar.m4508a() != null) {
            ContentValues b = apVar.m4510b();
            if (b != null) {
                if (this.f3005b == null || this.f3005b.isReadOnly()) {
                    this.f3005b = m4522b();
                }
                if (this.f3005b != null) {
                    try {
                        this.f3005b.update(apVar.m4508a(), b, str, null);
                        if (this.f3005b != null) {
                            this.f3005b.close();
                            this.f3005b = null;
                        }
                    } catch (Throwable th) {
                        if (this.f3005b != null) {
                            this.f3005b.close();
                            this.f3005b = null;
                        }
                    }
                }
            }
        }
    }

    public <T> List<T> m4526c(String str, ap<T> apVar) {
        Cursor query;
        Throwable th;
        List<T> arrayList = new ArrayList();
        if (this.f3005b == null) {
            this.f3005b = m4521a();
        }
        if (this.f3005b == null || apVar.m4508a() == null || str == null) {
            return arrayList;
        }
        try {
            query = this.f3005b.query(apVar.m4508a(), null, str, null, null, null, null);
            if (query == null) {
                try {
                    this.f3005b.close();
                    this.f3005b = null;
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            ay.m4590a(th2, "DataBase", "searchListData");
                            th2.printStackTrace();
                        }
                    }
                    try {
                        if (this.f3005b != null) {
                            this.f3005b.close();
                            this.f3005b = null;
                        }
                    } catch (Throwable th22) {
                        ay.m4590a(th22, "DataBase", "searchListData");
                        th22.printStackTrace();
                    }
                    return arrayList;
                } catch (Throwable th3) {
                    th22 = th3;
                }
            } else {
                while (query.moveToNext()) {
                    arrayList.add(apVar.m4511b(query));
                }
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th222) {
                        ay.m4590a(th222, "DataBase", "searchListData");
                        th222.printStackTrace();
                    }
                }
                try {
                    if (this.f3005b != null) {
                        this.f3005b.close();
                        this.f3005b = null;
                    }
                } catch (Throwable th4) {
                    th222 = th4;
                    ay.m4590a(th222, "DataBase", "searchListData");
                    th222.printStackTrace();
                    return arrayList;
                }
                return arrayList;
            }
        } catch (Throwable th5) {
            th222 = th5;
            query = null;
            if (query != null) {
                query.close();
            }
            if (this.f3005b != null) {
                this.f3005b.close();
                this.f3005b = null;
            }
            throw th222;
        }
    }
}
