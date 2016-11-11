package com.facebook.common.util;

import android.net.Uri;
import javax.annotation.Nullable;

public class UriUtil {
    public static final String DATA_SCHEME = "data";
    public static final String HTTPS_SCHEME = "https";
    public static final String HTTP_SCHEME = "http";
    public static final String LOCAL_ASSET_SCHEME = "asset";
    public static final String LOCAL_CONTENT_SCHEME = "content";
    public static final String LOCAL_FILE_SCHEME = "file";
    public static final String LOCAL_RESOURCE_SCHEME = "res";

    @Nullable
    public static String getSchemeOrNull(@Nullable Uri uri) {
        return uri == null ? null : uri.getScheme();
    }

    public static boolean isDataUri(@Nullable Uri uri) {
        return DATA_SCHEME.equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalAssetUri(@Nullable Uri uri) {
        return LOCAL_ASSET_SCHEME.equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalContentUri(@Nullable Uri uri) {
        return LOCAL_CONTENT_SCHEME.equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalFileUri(@Nullable Uri uri) {
        return LOCAL_FILE_SCHEME.equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalResourceUri(@Nullable Uri uri) {
        return LOCAL_RESOURCE_SCHEME.equals(getSchemeOrNull(uri));
    }

    public static boolean isNetworkUri(@Nullable Uri uri) {
        String schemeOrNull = getSchemeOrNull(uri);
        return HTTPS_SCHEME.equals(schemeOrNull) || HTTP_SCHEME.equals(schemeOrNull);
    }

    public static Uri parseUriOrNull(@Nullable String str) {
        return str != null ? Uri.parse(str) : null;
    }
}
