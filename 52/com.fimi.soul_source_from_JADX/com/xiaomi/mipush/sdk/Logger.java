package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.push.log.C2625a;
import com.xiaomi.push.log.C2626b;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    static {
        sDisablePushLog = false;
        sUserLogger = null;
    }

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), Opcodes.ACC_SYNTHETIC).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (Object equals : strArr) {
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    private static void setPushLog(Context context) {
        Object obj = sUserLogger != null ? 1 : null;
        LoggerInterface c2626b = new C2626b(context);
        if (!sDisablePushLog && hasWritePermission(context) && obj != null) {
            C2463b.m14121a(new C2625a(sUserLogger, c2626b));
        } else if (!sDisablePushLog && hasWritePermission(context)) {
            C2463b.m14121a(c2626b);
        } else if (obj != null) {
            C2463b.m14121a(sUserLogger);
        } else {
            C2463b.m14121a(new C2625a(null, null));
        }
    }
}
