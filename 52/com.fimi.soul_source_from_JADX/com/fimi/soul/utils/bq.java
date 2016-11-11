package com.fimi.soul.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.fimi.soul.drone.p114e.C1533b;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.entity.UpdateDroneItem;
import com.tencent.mm.sdk.conversation.RConversation;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

public class bq {
    public static final String f10107a = "route";
    public static final String f10108b = "flydata";
    private static final String f10109f = "waypoint";
    private static final String f10110g = "latitude";
    private static final String f10111h = "longitude";
    private static final String f10112i = "height";
    private static final String f10113j = "sequence";
    private static final String f10114k = "link";
    private static final String f10115l = "type";
    private static final String f10116m = "flyOver";
    private static final String f10117n = "create table if not exists route (id integer primary key autoincrement,planeID varchar(30),record_time varchar(30),user_id long,year integer,month integer,distance double,sportTime double,maxhight double,endddata varchar(30),uploadurl text,flag integer);";
    private static final String f10118o = "create table if not exists flydata(id integer primary key autoincrement,user_id long,latitude double,longitude double,record_time varchar(30),altitude double,distance double,speed double,model text,sporttime doule,satellitenum integer,endddata varchar(30));";
    private static final String f10119p = "create table if not exists flyid(id integer primary key autoincrement,user_id long,cloud_deck_ID text,receiver_control_ID text,fly_control_ID text,remote_control_ID text);";
    private static final String f10120q = "create table if not exists waypoint (latitude double ,longitude double ,height integer ,sequence integer ,link bit ,type integer ,flyOver bit );";
    private static final String f10121r = "latitude,longitude,height,sequence,link,type,flyOver";
    private C1968h f10122c;
    private SQLiteDatabase f10123d;
    private Context f10124e;

    public bq(Context context) {
        this.f10122c = new C1968h(context, null, null, 0);
        this.f10124e = context;
    }

    private ContentValues m12437a(bh bhVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f10116m, Integer.valueOf(bhVar.m10508g()));
        contentValues.put(f10112i, Integer.valueOf(bhVar.m10501c()));
        contentValues.put(f10110g, Double.valueOf(bhVar.m10499b().latitude));
        contentValues.put(f10111h, Double.valueOf(bhVar.m10499b().longitude));
        contentValues.put(f10114k, Integer.valueOf(bhVar.m10505e()));
        contentValues.put(f10113j, Integer.valueOf(bhVar.m10503d()));
        contentValues.put(f10115l, Integer.valueOf(bhVar.m10507f()));
        return contentValues;
    }

    private bh m12438a(Cursor cursor) {
        bh bhVar = new bh();
        bhVar.m10506e(cursor.getInt(cursor.getColumnIndex(f10116m)));
        bhVar.m10495a(cursor.getInt(cursor.getColumnIndex(f10112i)));
        bhVar.m10496a(new LatLng(cursor.getDouble(cursor.getColumnIndex(f10110g)), cursor.getDouble(cursor.getColumnIndex(f10111h))));
        bhVar.m10500b(cursor.getInt(cursor.getColumnIndex(f10113j)));
        bhVar.m10504d(cursor.getInt(cursor.getColumnIndex(f10115l)));
        bhVar.m10502c(cursor.getInt(cursor.getColumnIndex(f10114k)));
        return bhVar;
    }

    public static void m12439a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(f10120q);
        sQLiteDatabase.execSQL(f10117n);
        sQLiteDatabase.execSQL(f10118o);
        sQLiteDatabase.execSQL(f10119p);
    }

    public int m12440a(ContentValues contentValues, String str) {
        this.f10123d = this.f10122c.getWritableDatabase();
        Long valueOf = Long.valueOf(this.f10123d.insert(str, null, contentValues));
        this.f10123d.close();
        return valueOf.intValue();
    }

    public int m12441a(String str, int i) {
        this.f10123d = this.f10122c.getWritableDatabase();
        int delete = this.f10123d.delete(str, "id=?", new String[]{Integer.toString(i)});
        this.f10123d.close();
        return delete;
    }

    public int m12442a(String str, ContentValues contentValues, String str2, String str3) {
        this.f10123d = this.f10122c.getWritableDatabase();
        int update = this.f10123d.update(str, contentValues, "record_time=? and user_id=?", new String[]{str2, str3});
        this.f10123d.close();
        return update;
    }

    public int m12443a(String str, UpdateDroneItem updateDroneItem, String str2) {
        this.f10123d = this.f10122c.getWritableDatabase();
        int delete = this.f10123d.delete(str, "record_time=? and user_id=?", new String[]{updateDroneItem.getRecord_time(), str2});
        this.f10123d.close();
        return delete;
    }

    public int m12444a(String str, List<UpdateDroneItem> list, String str2) {
        this.f10123d = this.f10122c.getWritableDatabase();
        int i = 0;
        for (UpdateDroneItem updateDroneItem : list) {
            i = this.f10123d.delete(str, "record_time=? and user_id=?", new String[]{updateDroneItem.getRecord_time(), str2}) == 1 ? i + 1 : i;
        }
        this.f10123d.close();
        return i;
    }

    public List<C1533b> m12445a(String str) {
        List<C1533b> list = null;
        this.f10123d = this.f10122c.getWritableDatabase();
        Cursor rawQuery = this.f10123d.rawQuery(str, null);
        if (rawQuery.getCount() > 0) {
            list = new ArrayList();
            while (rawQuery.moveToNext()) {
                C1533b c1533b = new C1533b();
                c1533b.m10066a(rawQuery.getDouble(rawQuery.getColumnIndex(f10110g)));
                c1533b.m10071b(rawQuery.getDouble(rawQuery.getColumnIndex(f10111h)));
                c1533b.m10074c(rawQuery.getDouble(rawQuery.getColumnIndex("altitude")));
                c1533b.m10069a(rawQuery.getString(rawQuery.getColumnIndex("record_time")));
                c1533b.m10077d(rawQuery.getDouble(rawQuery.getColumnIndex("distance")));
                c1533b.m10079e(rawQuery.getDouble(rawQuery.getColumnIndex("speed")));
                c1533b.m10072b(rawQuery.getString(rawQuery.getColumnIndex("model")));
                c1533b.m10081f(rawQuery.getDouble(rawQuery.getColumnIndex("sporttime")));
                c1533b.m10067a(rawQuery.getInt(rawQuery.getColumnIndex("satellitenum")));
                c1533b.m10075c(rawQuery.getString(rawQuery.getColumnIndex("endddata")));
                list.add(c1533b);
            }
            rawQuery.close();
            this.f10123d.close();
        }
        return list;
    }

    public List<UpdateDroneItem> m12446a(String str, String str2) {
        List<UpdateDroneItem> list = null;
        if (str2 != null) {
            this.f10123d = this.f10122c.getWritableDatabase();
            String str3 = str;
            Cursor query = this.f10123d.query(str3, null, "user_id=?", new String[]{str2}, null, null, "month");
            if (query.getCount() > 0) {
                list = new ArrayList();
            }
            while (query.moveToNext()) {
                UpdateDroneItem updateDroneItem = new UpdateDroneItem();
                updateDroneItem.setDistance(query.getDouble(query.getColumnIndex("distance")));
                updateDroneItem.setEndddata(query.getString(query.getColumnIndex("endddata")));
                updateDroneItem.setMaxhight(query.getDouble(query.getColumnIndex("maxhight")));
                updateDroneItem.setMonth(query.getInt(query.getColumnIndex("month")));
                updateDroneItem.setRecord_time(query.getString(query.getColumnIndex("record_time")));
                updateDroneItem.setSportTime(query.getDouble(query.getColumnIndex("sportTime")));
                updateDroneItem.setUploadurl(query.getString(query.getColumnIndex("uploadurl")));
                updateDroneItem.setUser_id(query.getLong(query.getColumnIndex("user_id")));
                updateDroneItem.setUser_id(query.getLong(query.getColumnIndex("planeID")));
                updateDroneItem.setYear(query.getInt(query.getColumnIndex("year")));
                updateDroneItem.setFlag(query.getInt(query.getColumnIndex(RConversation.COL_FLAG)));
                updateDroneItem.setPlaneID(query.getLong(query.getColumnIndex("planeID")) + C2915a.f14760f);
                list.add(updateDroneItem);
            }
            query.close();
            this.f10123d.close();
        }
        return list;
    }

    public void m12447a() {
        this.f10123d = this.f10122c.getWritableDatabase();
        this.f10123d.execSQL("delete from waypoint");
        this.f10123d.close();
    }

    public void m12448a(List<bh> list) {
        m12447a();
        this.f10123d = this.f10122c.getWritableDatabase();
        for (bh a : list) {
            this.f10123d.insert(f10109f, null, m12437a(a));
        }
        this.f10123d.close();
    }

    public void m12449a(List<ContentValues> list, String str) {
        Log.e("\u67e5\u6570\u636e", "fdfdfdfdfdfd");
        this.f10123d = this.f10122c.getWritableDatabase();
        for (ContentValues insert : list) {
            this.f10123d.insert(str, null, insert);
        }
        this.f10123d.close();
    }

    public int m12450b(String str, String str2) {
        int i = 0;
        this.f10123d = this.f10122c.getWritableDatabase();
        Cursor rawQuery = this.f10123d.rawQuery(str, null);
        while (rawQuery.moveToNext()) {
            i = rawQuery.getInt(rawQuery.getColumnIndex(str2));
        }
        rawQuery.close();
        this.f10123d.close();
        return i;
    }

    public List<bh> m12451b() {
        this.f10123d = this.f10122c.getReadableDatabase();
        List<bh> arrayList = new ArrayList();
        Cursor rawQuery = this.f10123d.rawQuery("select latitude,longitude,height,sequence,link,type,flyOver from waypoint", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                arrayList.add(m12438a(rawQuery));
                rawQuery.moveToNext();
            }
        } else {
            arrayList = null;
        }
        rawQuery.close();
        this.f10122c.close();
        this.f10123d.close();
        return arrayList;
    }
}
