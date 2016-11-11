package com.baidu.tts.database;

import com.baidu.tts.tools.SqlTool;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

public class FsFileInfoTable {

    public enum Field {
        ABS_PATH("absPath", "varchar primary key"),
        STATE(XiaomiOAuthConstants.EXTRA_STATE_2, "integer");
        
        private final String f4317c;
        private final String f4318d;

        private Field(String str, String str2) {
            this.f4317c = str;
            this.f4318d = str2;
        }

        public String getColumnName() {
            return this.f4317c;
        }

        public String getDataType() {
            return this.f4318d;
        }
    }

    public static String m6703a() {
        return SqlTool.sqlCreateTable("fsFileInfo", Field.values());
    }

    public static String m6704b() {
        return SqlTool.sqlDropTable("fsFileInfo");
    }
}
