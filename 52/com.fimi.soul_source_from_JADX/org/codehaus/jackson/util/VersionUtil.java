package org.codehaus.jackson.util;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.codehaus.jackson.Version;

public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR;

    static {
        VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    }

    public static Version parseVersion(String str) {
        int i = 0;
        String str2 = null;
        if (str == null) {
            return null;
        }
        CharSequence trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        if (split.length < 2) {
            return null;
        }
        int parseVersionPart = parseVersionPart(split[0]);
        int parseVersionPart2 = parseVersionPart(split[1]);
        if (split.length > 2) {
            i = parseVersionPart(split[2]);
        }
        if (split.length > 3) {
            str2 = split[3];
        }
        return new Version(parseVersionPart, parseVersionPart2, i, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int parseVersionPart(java.lang.String r6) {
        /*
        r0 = 0;
        r2 = r6.toString();
        r3 = r2.length();
        r1 = r0;
    L_0x000a:
        if (r0 >= r3) goto L_0x0018;
    L_0x000c:
        r4 = r2.charAt(r0);
        r5 = 57;
        if (r4 > r5) goto L_0x0018;
    L_0x0014:
        r5 = 48;
        if (r4 >= r5) goto L_0x0019;
    L_0x0018:
        return r1;
    L_0x0019:
        r1 = r1 * 10;
        r4 = r4 + -48;
        r1 = r1 + r4;
        r0 = r0 + 1;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.util.VersionUtil.parseVersionPart(java.lang.String):int");
    }

    public static Version versionFor(Class<?> cls) {
        InputStream resourceAsStream;
        Version version = null;
        try {
            resourceAsStream = cls.getResourceAsStream(VERSION_FILE);
            if (resourceAsStream != null) {
                version = parseVersion(new BufferedReader(new InputStreamReader(resourceAsStream, C1142e.f5201a)).readLine());
                resourceAsStream.close();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (IOException e3) {
        } catch (Throwable th) {
            resourceAsStream.close();
        }
        return version == null ? Version.unknownVersion() : version;
    }
}
