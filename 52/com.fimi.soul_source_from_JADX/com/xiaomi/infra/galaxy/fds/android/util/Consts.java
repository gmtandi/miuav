package com.xiaomi.infra.galaxy.fds.android.util;

import com.baidu.tts.loopj.RequestParams;

public final class Consts {
    public static String APPLICATION_OCTET_STREAM = null;
    public static final String ESTIMATED_OBJECT_SIZE = "x-xiaomi-estimated-object-size";
    public static final String EXPIRES = "Expires";
    public static final String GALAXY_ACCESS_KEY_ID = "GalaxyAccessKeyId";
    public static final String SIGNATURE = "Signature";
    public static final String XIAOMI_HEADER_PREFIX = "x-xiaomi-";
    public static final String XIAOMI_META_HEADER_PREFIX = "x-xiaomi-meta-";

    static {
        APPLICATION_OCTET_STREAM = RequestParams.APPLICATION_OCTET_STREAM;
    }
}
