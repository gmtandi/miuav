package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.tencent.mm.sdk.platformtools.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0119a;

public abstract class MAutoDBItem implements MDBItem {
    public static final String SYSTEM_ROWID_FIELD = "rowid";
    public long systemRowid;

    public MAutoDBItem() {
        this.systemRowid = -1;
    }

    public static boolean checkIOEqual(ContentValues contentValues, Cursor cursor) {
        if (contentValues == null) {
            return cursor == null;
        } else {
            if (cursor == null || cursor.getCount() != 1) {
                return false;
            }
            cursor.moveToFirst();
            int columnCount = cursor.getColumnCount();
            int size = contentValues.size();
            if (contentValues.containsKey(SYSTEM_ROWID_FIELD)) {
                size--;
            }
            if (cursor.getColumnIndex(SYSTEM_ROWID_FIELD) != -1) {
                columnCount--;
            }
            if (size != columnCount) {
                return false;
            }
            try {
                for (Entry key : contentValues.valueSet()) {
                    String str = (String) key.getKey();
                    if (!str.equals(SYSTEM_ROWID_FIELD)) {
                        columnCount = cursor.getColumnIndex(str);
                        if (columnCount == -1) {
                            return false;
                        }
                        if (contentValues.get(str) instanceof byte[]) {
                            byte[] bArr = (byte[]) contentValues.get(str);
                            byte[] blob = cursor.getBlob(columnCount);
                            if (bArr.length != blob.length) {
                                return false;
                            }
                            for (columnCount = 0; columnCount < bArr.length; columnCount++) {
                                if (bArr[columnCount] != blob[columnCount]) {
                                    return false;
                                }
                            }
                            continue;
                        } else if (cursor.getString(columnCount) == null && contentValues.get(str) != null) {
                            return false;
                        } else {
                            if (contentValues.get(str) == null) {
                                return false;
                            }
                            if (!contentValues.get(str).toString().equals(cursor.getString(columnCount))) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static Cursor getCursorForProjection(ContentValues contentValues, String[] strArr) {
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = contentValues.get(strArr[i]);
        }
        Cursor matrixCursor = new MatrixCursor(strArr);
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    public static String[] getFullColumns(Field[] fieldArr) {
        String[] strArr = new String[(fieldArr.length + 1)];
        for (int i = 0; i < fieldArr.length; i++) {
            strArr[i] = fieldArr[i].getName().substring(6);
        }
        strArr[fieldArr.length] = SYSTEM_ROWID_FIELD;
        return strArr;
    }

    public static Field[] getValidFields(Class<?> cls) {
        List linkedList = new LinkedList();
        for (Field field : cls.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            String name = field.getName();
            if (name != null && name.startsWith("field_") && Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers)) {
                if (name.endsWith("field_rowid")) {
                    C0119a.m172a("field_rowid reserved by MAutoDBItem, change now!", false);
                }
                linkedList.add(field);
            }
        }
        return (Field[]) linkedList.toArray(new Field[0]);
    }

    public static Map<String, String> identify(Field[] fieldArr, StringBuilder stringBuilder, String str) {
        Map<String, String> hashMap = new HashMap();
        int i = 0;
        while (i < fieldArr.length) {
            String type = CursorFieldHelper.type(fieldArr[i].getType());
            if (type == null) {
                Log.m13541e("MicroMsg.SDK.MAutoDBItem", "failed identify on column: " + fieldArr[i].getName() + ", skipped");
            } else {
                String substring = fieldArr[i].getName().substring(6);
                if (stringBuilder != null) {
                    stringBuilder.append(substring + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + type + (substring.equals(str) ? " PRIMARY KEY " : C2915a.f14760f));
                    stringBuilder.append(i == fieldArr.length + -1 ? C2915a.f14760f : ", ");
                }
                hashMap.put(substring, type);
            }
            i++;
        }
        return hashMap;
    }

    public void convertFrom(Cursor cursor) {
        int columnIndexOrThrow;
        for (Field field : fields()) {
            try {
                if (cursor.getColumnIndexOrThrow(field.getName().substring(6)) != -1) {
                    try {
                        Method method = CursorFieldHelper.get(field.getType(), false);
                        if (method != null) {
                            method.invoke(null, new Object[]{field, this, cursor, Integer.valueOf(r5)});
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
            }
        }
        try {
            columnIndexOrThrow = cursor.getColumnIndexOrThrow(SYSTEM_ROWID_FIELD);
        } catch (Exception e3) {
            columnIndexOrThrow = -1;
        }
        if (columnIndexOrThrow > 0) {
            this.systemRowid = cursor.getLong(columnIndexOrThrow);
        }
    }

    public ContentValues convertTo() {
        ContentValues contentValues = new ContentValues();
        for (Field type : fields()) {
            try {
                Method method = CursorFieldHelper.get(type.getType(), true);
                if (method != null) {
                    method.invoke(null, new Object[]{type, this, contentValues});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.systemRowid > 0) {
            contentValues.put(SYSTEM_ROWID_FIELD, Long.valueOf(this.systemRowid));
        }
        return contentValues;
    }

    protected abstract Field[] fields();
}
