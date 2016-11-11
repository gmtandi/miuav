package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public final class LocaleUtil {
    public static final String ARABIC = "ar";
    public static final String CHINA = "zh_CN";
    public static final String ENGLISH = "en";
    public static final String HEBREW = "iw";
    public static final String HINDI = "hi";
    public static final String HONGKONG = "zh_HK";
    public static final String INDONESIAN = "id";
    public static final String ITALIAN = "it";
    public static final String JAPANESE = "ja";
    public static final String KOREAN = "ko";
    public static final String LANGUAGE_DEFAULT = "language_default";
    public static final String LANGUAGE_KEY = "language_key";
    public static final String MALAY = "ms";
    public static final String POLISH = "pl";
    public static final String PORTUGUESE = "pt";
    public static final String RUSSIAN = "ru";
    public static final String SPANISH = "es";
    public static final String TAIWAN = "zh_TW";
    public static final String THAI = "th";
    public static final String TURKEY = "tr";
    public static final String VIETNAMESE = "vi";

    private LocaleUtil() {
    }

    private static String m13538b(String str) {
        String trim = Locale.getDefault().getLanguage().trim();
        if (trim.equals(ENGLISH)) {
            return trim;
        }
        trim = Locale.getDefault().getLanguage().trim() + "_" + Locale.getDefault().getCountry().trim();
        return (trim.equals(TAIWAN) || trim.equals(HONGKONG)) ? TAIWAN : new StringBuilder().append(Locale.getDefault().getLanguage().trim()).append("_").append(Locale.getDefault().getCountry().trim()).toString().equals(CHINA) ? CHINA : Locale.getDefault().getLanguage().trim().equals(THAI) ? THAI : Locale.getDefault().getLanguage().trim().equals(INDONESIAN) ? INDONESIAN : Locale.getDefault().getLanguage().trim().equals(VIETNAMESE) ? VIETNAMESE : Locale.getDefault().getLanguage().trim().equals(PORTUGUESE) ? PORTUGUESE : Locale.getDefault().getLanguage().trim().equals(SPANISH) ? SPANISH : Locale.getDefault().getLanguage().trim().equals(RUSSIAN) ? RUSSIAN : Locale.getDefault().getLanguage().trim().equals(ARABIC) ? ARABIC : Locale.getDefault().getLanguage().trim().equals(HEBREW) ? HEBREW : Locale.getDefault().getLanguage().trim().equals(POLISH) ? POLISH : Locale.getDefault().getLanguage().trim().equals(HINDI) ? HINDI : Locale.getDefault().getLanguage().trim().equals(JAPANESE) ? JAPANESE : Locale.getDefault().getLanguage().trim().equals(ITALIAN) ? ITALIAN : Locale.getDefault().getLanguage().trim().equals(KOREAN) ? KOREAN : Locale.getDefault().getLanguage().trim().equals(MALAY) ? MALAY : Locale.getDefault().getLanguage().trim().equals(TURKEY) ? TURKEY : str;
    }

    public static String getApplicationLanguage() {
        String nullAsNil = Util.nullAsNil(SystemProperty.getProperty(LANGUAGE_KEY));
        return (nullAsNil.length() <= 0 || nullAsNil.equals(LANGUAGE_DEFAULT)) ? m13538b(ENGLISH) : nullAsNil;
    }

    public static String getCurrentCountryCode() {
        return Locale.getDefault().getCountry().trim();
    }

    public static boolean isLanguageSupported(String str) {
        return Util.isNullOrNil(str) ? false : str.equalsIgnoreCase(TAIWAN) || str.equalsIgnoreCase(HONGKONG) || str.equalsIgnoreCase(CHINA) || str.equalsIgnoreCase(ENGLISH) || str.equalsIgnoreCase(THAI) || str.equals(INDONESIAN) || str.equals(VIETNAMESE) || str.equalsIgnoreCase(PORTUGUESE) || str.equalsIgnoreCase(SPANISH) || str.equalsIgnoreCase(RUSSIAN) || str.equalsIgnoreCase(ARABIC) || str.equalsIgnoreCase(HEBREW) || str.equalsIgnoreCase(POLISH) || str.equalsIgnoreCase(HINDI) || str.equalsIgnoreCase(JAPANESE) || str.equalsIgnoreCase(ITALIAN) || str.equalsIgnoreCase(KOREAN) || str.equalsIgnoreCase(MALAY) || str.equalsIgnoreCase(TURKEY);
    }

    public static String loadApplicationLanguage(SharedPreferences sharedPreferences, Context context) {
        String nullAsNil = Util.nullAsNil(sharedPreferences.getString(LANGUAGE_KEY, null));
        if (nullAsNil.length() <= 0 || nullAsNil.equals(LANGUAGE_DEFAULT)) {
            nullAsNil = m13538b(ENGLISH);
            SystemProperty.setProperty(LANGUAGE_KEY, nullAsNil);
            return nullAsNil;
        }
        SystemProperty.setProperty(LANGUAGE_KEY, nullAsNil);
        return nullAsNil;
    }

    public static String loadApplicationLanguageSettings(SharedPreferences sharedPreferences, Context context) {
        String nullAsNil = Util.nullAsNil(sharedPreferences.getString(LANGUAGE_KEY, null));
        return !Util.isNullOrNil(nullAsNil) ? nullAsNil : LANGUAGE_DEFAULT;
    }

    public static void saveApplicationLanguage(SharedPreferences sharedPreferences, Context context, String str) {
        if (sharedPreferences.edit().putString(LANGUAGE_KEY, str).commit()) {
            SystemProperty.setProperty(LANGUAGE_KEY, str);
            Log.m13549w("MicroMsg.LocaleUtil", "save application lang as:" + str);
            return;
        }
        Log.m13541e("MicroMsg.LocaleUtil", "saving application lang failed");
    }

    public static Locale transLanguageToLocale(String str) {
        if (str.equals(TAIWAN) || str.equals(HONGKONG)) {
            return Locale.TAIWAN;
        }
        if (str.equals(ENGLISH)) {
            return Locale.ENGLISH;
        }
        if (str.equals(CHINA)) {
            return Locale.CHINA;
        }
        if (str.equalsIgnoreCase(THAI)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(INDONESIAN)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(VIETNAMESE)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(PORTUGUESE)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(SPANISH)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(RUSSIAN)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(ARABIC)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(HEBREW)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(POLISH)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(HINDI)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(JAPANESE)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(ITALIAN)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(KOREAN)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(MALAY)) {
            return new Locale(str);
        }
        if (str.equalsIgnoreCase(TURKEY)) {
            return new Locale(str);
        }
        Log.m13541e("MicroMsg.LocaleUtil", "transLanguageToLocale country = " + str);
        return Locale.ENGLISH;
    }

    public static void updateApplicationResourceLocale(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (!configuration.locale.equals(locale)) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration.locale = locale;
            resources.updateConfiguration(configuration, displayMetrics);
            Resources.getSystem().updateConfiguration(configuration, displayMetrics);
        }
    }
}
