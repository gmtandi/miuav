package com.tencent.mm.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.Resolver;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2538k;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MMSharedPreferences implements SharedPreferences {
    private final String[] columns;
    private final ContentResolver f11758i;
    private final HashMap<String, Object> f11759j;
    private REditor f11760k;

    class REditor implements Editor {
        private ContentResolver f11754i;
        private Map<String, Object> f11755l;
        private Set<String> f11756m;
        private boolean f11757n;

        public REditor(ContentResolver contentResolver) {
            this.f11755l = new HashMap();
            this.f11756m = new HashSet();
            this.f11757n = false;
            this.f11754i = contentResolver;
        }

        public void apply() {
        }

        public Editor clear() {
            this.f11757n = true;
            return this;
        }

        public boolean commit() {
            ContentValues contentValues = new ContentValues();
            if (this.f11757n) {
                this.f11754i.delete(SharedPref.CONTENT_URI, null, null);
                this.f11757n = false;
            }
            for (String str : this.f11756m) {
                this.f11754i.delete(SharedPref.CONTENT_URI, "key = ?", new String[]{str});
            }
            for (Entry value : this.f11755l.entrySet()) {
                if (Resolver.unresolveObj(contentValues, value.getValue())) {
                    this.f11754i.update(SharedPref.CONTENT_URI, contentValues, "key = ?", new String[]{(String) ((Entry) r2.next()).getKey()});
                }
            }
            return true;
        }

        public Editor putBoolean(String str, boolean z) {
            this.f11755l.put(str, Boolean.valueOf(z));
            this.f11756m.remove(str);
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.f11755l.put(str, Float.valueOf(f));
            this.f11756m.remove(str);
            return this;
        }

        public Editor putInt(String str, int i) {
            this.f11755l.put(str, Integer.valueOf(i));
            this.f11756m.remove(str);
            return this;
        }

        public Editor putLong(String str, long j) {
            this.f11755l.put(str, Long.valueOf(j));
            this.f11756m.remove(str);
            return this;
        }

        public Editor putString(String str, String str2) {
            this.f11755l.put(str, str2);
            this.f11756m.remove(str);
            return this;
        }

        public Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        public Editor remove(String str) {
            this.f11756m.add(str);
            return this;
        }
    }

    public MMSharedPreferences(Context context) {
        this.columns = new String[]{C2538k._ID, SharedPref.KEY, SocialConstants.PARAM_TYPE, SharedPref.VALUE};
        this.f11759j = new HashMap();
        this.f11760k = null;
        this.f11758i = context.getContentResolver();
    }

    private Object getValue(String str) {
        try {
            Cursor query = this.f11758i.query(SharedPref.CONTENT_URI, this.columns, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object resolveObj = query.moveToFirst() ? Resolver.resolveObj(query.getInt(query.getColumnIndex(SocialConstants.PARAM_TYPE)), query.getString(query.getColumnIndex(SharedPref.VALUE))) : null;
            query.close();
            return resolveObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean contains(String str) {
        return getValue(str) != null;
    }

    public Editor edit() {
        if (this.f11760k == null) {
            this.f11760k = new REditor(this.f11758i);
        }
        return this.f11760k;
    }

    public Map<String, ?> getAll() {
        try {
            Cursor query = this.f11758i.query(SharedPref.CONTENT_URI, this.columns, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex(SharedPref.KEY);
            int columnIndex2 = query.getColumnIndex(SocialConstants.PARAM_TYPE);
            int columnIndex3 = query.getColumnIndex(SharedPref.VALUE);
            while (query.moveToNext()) {
                this.f11759j.put(query.getString(columnIndex), Resolver.resolveObj(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.f11759j;
        } catch (Exception e) {
            e.printStackTrace();
            return this.f11759j;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Boolean)) ? z : ((Boolean) value).booleanValue();
    }

    public float getFloat(String str, float f) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Float)) ? f : ((Float) value).floatValue();
    }

    public int getInt(String str, int i) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Integer)) ? i : ((Integer) value).intValue();
    }

    public long getLong(String str, long j) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Long)) ? j : ((Long) value).longValue();
    }

    public String getString(String str, String str2) {
        Object value = getValue(str);
        return (value == null || !(value instanceof String)) ? str2 : (String) value;
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
