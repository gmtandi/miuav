package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.Map;
import org.codehaus.jackson.smile.SmileConstants;

public final class ChannelUtil {
    public static final int FLAG_NULL = 0;
    public static final int FLAG_UPDATE_EXTERNAL = 1;
    public static final int FLAG_UPDATE_NOTIP = 2;
    public static int buildRev;
    public static int channelId;
    public static boolean fullVersionInfo;
    public static String marketURL;
    public static String profileDeviceType;
    public static int updateMode;

    static {
        channelId = FLAG_NULL;
        profileDeviceType = VERSION.SDK_INT;
        updateMode = FLAG_NULL;
        buildRev = FLAG_NULL;
        marketURL = "market://details?id=" + MMApplicationContext.getPackageName();
        fullVersionInfo = false;
    }

    private ChannelUtil() {
    }

    public static String formatVersion(Context context, int i) {
        String str = ((i >> 24) & 15) + "." + ((i >> 16) & Util.MASK_8BIT);
        int i2 = Util.MASK_16BIT & i;
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                if (packageInfo != null) {
                    i2 = packageInfo.versionCode;
                    str = packageInfo.versionName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!fullVersionInfo) {
            return str;
        }
        str = str + " r" + i2 + "(build." + buildRev + ")";
        Log.m13539d("MicroMsg.SDK.ChannelUtil", "full version: " + str);
        return str;
    }

    public static void loadProfile(Context context) {
        try {
            Map parseIni = KVConfig.parseIni(Util.convertStreamToString(context.getAssets().open("profile.ini")));
            String nullAsNil = Util.nullAsNil((String) parseIni.get("PROFILE_DEVICE_TYPE"));
            profileDeviceType = nullAsNil;
            if (nullAsNil.length() <= 0) {
                profileDeviceType = VERSION.SDK_INT;
            }
            updateMode = Integer.parseInt((String) parseIni.get("UPDATE_MODE"));
            buildRev = Integer.parseInt((String) parseIni.get("BUILD_REVISION"));
            Log.w("MicroMsg.SDK.ChannelUtil", "profileDeviceType=" + profileDeviceType);
            Log.w("MicroMsg.SDK.ChannelUtil", "updateMode=" + updateMode);
            nullAsNil = (String) parseIni.get("MARKET_URL");
            if (!(nullAsNil == null || nullAsNil.trim().length() == 0 || Uri.parse(nullAsNil) == null)) {
                marketURL = nullAsNil;
            }
            Log.w("MicroMsg.SDK.ChannelUtil", "marketURL=" + marketURL);
        } catch (Exception e) {
            Log.e("MicroMsg.SDK.ChannelUtil", "setup profile from profile.ini failed");
            e.printStackTrace();
        }
    }

    public static void setupChannelId(Context context) {
        try {
            channelId = Integer.parseInt((String) KVConfig.parseIni(Util.convertStreamToString(context.getAssets().open("channel.ini"))).get("CHANNEL"));
        } catch (Exception e) {
            Log.m13541e("MicroMsg.SDK.ChannelUtil", "setup channel id from channel.ini failed");
            e.printStackTrace();
        }
    }
}
