package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class MediaUtils {
    public static final Map<String, String> ADDITIONAL_ALLOWED_MIME_TYPES;

    static {
        ADDITIONAL_ALLOWED_MIME_TYPES = ImmutableMap.of("mkv", "video/x-matroska");
    }

    @Nullable
    private static String extractExtension(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf < 0 || lastIndexOf == str.length() - 1) ? null : str.substring(lastIndexOf + 1);
    }

    @Nullable
    public static String extractMime(String str) {
        String extractExtension = extractExtension(str);
        if (extractExtension == null) {
            return null;
        }
        String toLowerCase = extractExtension.toLowerCase(Locale.US);
        extractExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase);
        return extractExtension == null ? (String) ADDITIONAL_ALLOWED_MIME_TYPES.get(toLowerCase) : extractExtension;
    }

    public static boolean isNonNativeSupportedMimeType(String str) {
        return ADDITIONAL_ALLOWED_MIME_TYPES.containsValue(str);
    }

    public static boolean isPhoto(@Nullable String str) {
        return str != null && str.startsWith("image/");
    }

    public static boolean isVideo(@Nullable String str) {
        return str != null && str.startsWith("video/");
    }
}
