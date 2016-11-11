package com.baidu.tts.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.client.model.ModelInfo;
import com.baidu.tts.database.C0784c.C0779a;
import com.baidu.tts.tools.SqlTool;
import com.fimi.soul.entity.User;
import com.tencent.mm.sdk.platformtools.LocaleUtil;

public class SpeechModelTable {

    /* renamed from: com.baidu.tts.database.SpeechModelTable.1 */
    final class C07811 implements C0779a {
        final /* synthetic */ ModelBags f4328a;

        C07811(ModelBags modelBags) {
            this.f4328a = modelBags;
        }

        public boolean m6711a(SQLiteDatabase sQLiteDatabase) {
            try {
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement("insert into speechModel (name, version_min, version_max, language, gender, speaker, domain, quality, text_data_id, speech_data_id, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                for (ModelInfo modelInfo : this.f4328a.getModelInfos()) {
                    String name = modelInfo.getName();
                    String versionMin = modelInfo.getVersionMin();
                    String versionMax = modelInfo.getVersionMax();
                    String language = modelInfo.getLanguage();
                    String gender = modelInfo.getGender();
                    String speaker = modelInfo.getSpeaker();
                    String domain = modelInfo.getDomain();
                    String quality = modelInfo.getQuality();
                    String textDataId = modelInfo.getTextDataId();
                    String speechDataId = modelInfo.getSpeechDataId();
                    String serverId = modelInfo.getServerId();
                    compileStatement.bindString(1, name);
                    compileStatement.bindString(2, versionMin);
                    compileStatement.bindString(3, versionMax);
                    compileStatement.bindString(4, language);
                    compileStatement.bindString(5, gender);
                    compileStatement.bindString(6, speaker);
                    compileStatement.bindString(7, domain);
                    compileStatement.bindString(8, quality);
                    compileStatement.bindString(9, textDataId);
                    compileStatement.bindString(10, speechDataId);
                    compileStatement.bindString(11, serverId);
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
        TEXT_DATA_ID("text_data_id", "integer"),
        SPEECH_DATA_ID("speech_data_id", "integer"),
        NAME(User.FN_NAME, "varchar(256) not null default unnamed"),
        VERSION_MIN("version_min", "integer"),
        VERSION_MAX("version_max", "integer"),
        LANGUAGE("language", "varchar(20)"),
        GENDER("gender", "varchar(20)"),
        SPEAKER("speaker", "varchar(256)"),
        DOMAIN("domain", "varchar(50)"),
        QUALITY("quality", "varchar(50)");
        
        private final String f4341l;
        private final String f4342m;

        private Field(String str, String str2) {
            this.f4341l = str;
            this.f4342m = str2;
        }

        public String getColumnName() {
            return this.f4341l;
        }

        public String getDataType() {
            return this.f4342m;
        }
    }

    public static int m6712a(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.delete("speechModel", "id=?", new String[]{str});
    }

    public static String m6713a() {
        return SqlTool.sqlCreateTable("speechModel", Field.values());
    }

    public static void m6714a(SQLiteDatabase sQLiteDatabase, ModelBags modelBags) {
        new C0784c(sQLiteDatabase, new C07811(modelBags)).m6731a();
    }

    public static String m6715b() {
        return SqlTool.sqlDropTable("speechModel");
    }
}
