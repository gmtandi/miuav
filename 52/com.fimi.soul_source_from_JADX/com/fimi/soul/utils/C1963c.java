package com.fimi.soul.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.fimi.soul.entity.BatteryOverDischange;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.utils.c */
public class C1963c {
    public static C1963c f10125a;
    public static final String f10126b;
    private C1968h f10127c;
    private SQLiteDatabase f10128d;
    private Context f10129e;

    static {
        f10126b = "create table  if not exists " + C1964d.f10130a + "(" + C1964d.f10131b + " varchar(20) not null ," + C1964d.f10132c + " varchar(20) not null ," + C1964d.f10133d + " varchar(20) not null ," + C1964d.f10134e + " varchar(20) not null ," + C1964d.f10135f + " varchar(20) not null ," + C1964d.f10136g + " varchar(20) not null ," + C1964d.f10137h + " varchar(20) not null ," + C1964d.f10138i + " varchar(20) not null ," + C1964d.f10139j + " varchar(20) not null ," + C1964d.f10140k + " varchar(20) not null ," + C1964d.f10141l + " varchar(20) not null ," + C1964d.f10142m + " varchar(20) not null ," + C1964d.f10143n + " varchar(20) not null ," + C1964d.f10144o + " varchar(20) not null)";
    }

    public C1963c(Context context) {
        this.f10129e = context;
        this.f10127c = new C1968h(context, null, null, 0);
        this.f10128d = this.f10127c.getWritableDatabase();
        this.f10128d.execSQL(f10126b);
    }

    public static C1963c m12455a(Context context) {
        if (f10125a == null) {
            f10125a = new C1963c(context);
        }
        return f10125a;
    }

    private ContentValues m12456b(BatteryOverDischange batteryOverDischange) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C1964d.f10131b, batteryOverDischange.getUserId());
        contentValues.put(C1964d.f10132c, batteryOverDischange.getBatteryId());
        contentValues.put(C1964d.f10133d, batteryOverDischange.getBatteryLevel());
        contentValues.put(C1964d.f10134e, batteryOverDischange.getVoltage());
        contentValues.put(C1964d.f10135f, batteryOverDischange.getBatteryCurrent());
        contentValues.put(C1964d.f10136g, batteryOverDischange.getTemperature());
        contentValues.put(C1964d.f10137h, batteryOverDischange.getBatteryFull());
        contentValues.put(C1964d.f10138i, batteryOverDischange.getBatteryOne());
        contentValues.put(C1964d.f10139j, batteryOverDischange.getBatteryTwo());
        contentValues.put(C1964d.f10140k, batteryOverDischange.getBatteryThree());
        contentValues.put(C1964d.f10141l, batteryOverDischange.getBatteryFour());
        contentValues.put(C1964d.f10142m, batteryOverDischange.getBatteryRecyle());
        contentValues.put(C1964d.f10143n, batteryOverDischange.getVersion());
        contentValues.put(C1964d.f10144o, batteryOverDischange.getAppType());
        return contentValues;
    }

    public long m12457a(BatteryOverDischange batteryOverDischange) {
        return this.f10128d.insert(C1964d.f10130a, null, m12456b(batteryOverDischange));
    }

    public List<BatteryOverDischange> m12458a() {
        String str = "select * from " + C1964d.f10130a;
        SQLiteDatabase writableDatabase = this.f10127c.getWritableDatabase();
        List<BatteryOverDischange> arrayList = new ArrayList();
        Cursor rawQuery = writableDatabase.rawQuery(str, null);
        while (rawQuery.moveToNext()) {
            BatteryOverDischange batteryOverDischange = new BatteryOverDischange();
            batteryOverDischange.setUserId(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10131b)));
            batteryOverDischange.setBatteryId(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10132c)));
            batteryOverDischange.setBatteryLevel(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10133d)));
            batteryOverDischange.setVoltage(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10134e)));
            batteryOverDischange.setBatteryCurrent(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10135f)));
            batteryOverDischange.setTemperature(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10136g)));
            batteryOverDischange.setBatteryFull(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10137h)));
            batteryOverDischange.setBatteryOne(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10138i)));
            batteryOverDischange.setBatteryTwo(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10139j)));
            batteryOverDischange.setBatteryThree(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10140k)));
            batteryOverDischange.setBatteryFour(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10141l)));
            batteryOverDischange.setBatteryRecyle(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10142m)));
            batteryOverDischange.setVersion(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10143n)));
            batteryOverDischange.setAppType(rawQuery.getString(rawQuery.getColumnIndex(C1964d.f10144o)));
            arrayList.add(batteryOverDischange);
        }
        return arrayList;
    }

    public boolean m12459b() {
        return this.f10128d.delete(C1964d.f10130a, null, null) > 0;
    }

    public long m12460c() {
        this.f10128d = this.f10127c.getReadableDatabase();
        Cursor rawQuery = this.f10128d.rawQuery("select count(*)from" + C1964d.f10130a, null);
        rawQuery.moveToFirst();
        return rawQuery.getLong(0);
    }
}
