package org.p122a.p123a.p167i;

import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import com.fimi.soul.drone.p107c.p108a.p109a.bn;
import com.fimi.soul.module.setting.newhand.ae;
import it.p074a.p075a.C2799f;
import java.util.Locale;
import org.apache.http.ReasonPhraseCatalog;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.j */
public class C3207j implements ReasonPhraseCatalog {
    public static final C3207j f15651a;
    private static final String[][] f15652b;

    static {
        f15651a = new C3207j();
        f15652b = new String[][]{null, new String[3], new String[8], new String[8], new String[25], new String[8]};
        C3207j.m17788a(C2799f.f14282t, "OK");
        C3207j.m17788a(bj.f6779b, "Created");
        C3207j.m17788a(C2799f.f14283u, "Accepted");
        C3207j.m17788a(C1458u.f6934b, "No Content");
        C3207j.m17788a(301, "Moved Permanently");
        C3207j.m17788a(302, "Moved Temporarily");
        C3207j.m17788a(304, "Not Modified");
        C3207j.m17788a(400, "Bad Request");
        C3207j.m17788a(401, "Unauthorized");
        C3207j.m17788a(403, "Forbidden");
        C3207j.m17788a(404, "Not Found");
        C3207j.m17788a(C2799f.f14263a, "Internal Server Error");
        C3207j.m17788a(C2799f.f14264b, "Not Implemented");
        C3207j.m17788a(C2799f.f14265c, "Bad Gateway");
        C3207j.m17788a(C2799f.f14266d, "Service Unavailable");
        C3207j.m17788a(100, "Continue");
        C3207j.m17788a(307, "Temporary Redirect");
        C3207j.m17788a(405, "Method Not Allowed");
        C3207j.m17788a(409, "Conflict");
        C3207j.m17788a(412, "Precondition Failed");
        C3207j.m17788a(413, "Request Too Long");
        C3207j.m17788a(414, "Request-URI Too Long");
        C3207j.m17788a(415, "Unsupported Media Type");
        C3207j.m17788a(ae.f9482j, "Multiple Choices");
        C3207j.m17788a(303, "See Other");
        C3207j.m17788a(305, "Use Proxy");
        C3207j.m17788a(402, "Payment Required");
        C3207j.m17788a(406, "Not Acceptable");
        C3207j.m17788a(407, "Proxy Authentication Required");
        C3207j.m17788a(408, "Request Timeout");
        C3207j.m17788a(Opcodes.LSUB, "Switching Protocols");
        C3207j.m17788a(bn.f6797b, "Non Authoritative Information");
        C3207j.m17788a(205, "Reset Content");
        C3207j.m17788a(206, "Partial Content");
        C3207j.m17788a(C2799f.f14267e, "Gateway Timeout");
        C3207j.m17788a(505, "Http Version Not Supported");
        C3207j.m17788a(410, "Gone");
        C3207j.m17788a(411, "Length Required");
        C3207j.m17788a(416, "Requested Range Not Satisfiable");
        C3207j.m17788a(417, "Expectation Failed");
        C3207j.m17788a(Opcodes.FSUB, "Processing");
        C3207j.m17788a(207, "Multi-Status");
        C3207j.m17788a(422, "Unprocessable Entity");
        C3207j.m17788a(419, "Insufficient Space On Resource");
        C3207j.m17788a(420, "Method Failure");
        C3207j.m17788a(423, "Locked");
        C3207j.m17788a(507, "Insufficient Storage");
        C3207j.m17788a(424, "Failed Dependency");
    }

    protected C3207j() {
    }

    private static void m17788a(int i, String str) {
        int i2 = i / 100;
        f15652b[i2][i - (i2 * 100)] = str;
    }

    public String getReason(int i, Locale locale) {
        boolean z = i >= 100 && i < 600;
        C3234a.m17888a(z, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        return f15652b[i2].length > i3 ? f15652b[i2][i3] : null;
    }
}
