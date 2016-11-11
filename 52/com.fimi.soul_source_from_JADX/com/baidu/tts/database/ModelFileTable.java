package com.baidu.tts.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.client.model.ModelFileInfo;
import com.baidu.tts.database.C0784c.C0779a;
import com.baidu.tts.tools.SqlTool;
import com.fimi.soul.entity.User;
import com.tencent.mm.sdk.platformtools.LocaleUtil;

public class ModelFileTable {

    /* renamed from: com.baidu.tts.database.ModelFileTable.1 */
    final class C07801 implements C0779a {
        final /* synthetic */ ModelFileBags f4319a;

        C07801(ModelFileBags modelFileBags) {
            this.f4319a = modelFileBags;
        }

        public boolean m6706a(SQLiteDatabase sQLiteDatabase) {
            try {
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement("insert into modelFile (id, length, md5, name, absPath) values (?, ?, ?, ?, ?)");
                for (ModelFileInfo modelFileInfo : this.f4319a.getModelFileInfos()) {
                    String serverid = modelFileInfo.getServerid();
                    String length = modelFileInfo.getLength();
                    String md5 = modelFileInfo.getMd5();
                    String name = modelFileInfo.getName();
                    String absPath = modelFileInfo.getAbsPath();
                    compileStatement.bindString(1, serverid);
                    compileStatement.bindString(2, length);
                    compileStatement.bindString(3, md5);
                    compileStatement.bindString(4, name);
                    compileStatement.bindString(5, absPath);
                    compileStatement.executeInsert();
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public enum Field {
        ID(LocaleUtil.INDONESIAN, "integer primary key"),
        LENGTH("length", "bigint"),
        MD5("md5", "varchar(32)"),
        NAME(User.FN_NAME, "varchar(256) not null default unnamed"),
        ABS_PATH("absPath", "varchar");
        
        private final String f4326f;
        private final String f4327g;

        private Field(String str, String str2) {
            this.f4326f = str;
            this.f4327g = str2;
        }

        public String getColumnName() {
            return this.f4326f;
        }

        public String getDataType() {
            return this.f4327g;
        }
    }

    public static int m6707a(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.delete("modelFile", "id=?", new String[]{str});
    }

    public static String m6708a() {
        return SqlTool.sqlCreateTable("modelFile", Field.values());
    }

    public static void m6709a(SQLiteDatabase sQLiteDatabase, ModelFileBags modelFileBags) {
        new C0784c(sQLiteDatabase, new C07801(modelFileBags)).m6731a();
    }

    public static String m6710b() {
        return SqlTool.sqlDropTable("modelFile");
    }
}
