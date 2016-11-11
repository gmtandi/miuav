package org.p122a.p123a.p167i.p168a;

import com.tencent.mm.sdk.conversation.RConversationStorage;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.impl.auth.UnsupportedDigestAlgorithmException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2920e;
import org.p122a.p123a.p159n.C3223a;
import org.p122a.p123a.p178k.C3209a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.a.e */
public class C3059e extends C3056t {
    private static final char[] f15129a;
    private static final int f15130c = -1;
    private static final int f15131d = 0;
    private static final int f15132e = 1;
    private static final int f15133f = 2;
    private boolean f15134b;
    private String f15135g;
    private long f15136h;
    private String f15137i;
    private String f15138j;
    private String f15139k;

    static {
        f15129a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public C3059e() {
        this(C2922b.f14782f);
    }

    public C3059e(Charset charset) {
        super(charset);
        this.f15134b = false;
    }

    @Deprecated
    public C3059e(C2920e c2920e) {
        super(c2920e);
    }

    static String m17182a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * f15133f)];
        for (int i = f15131d; i < length; i += f15132e) {
            int i2 = bArr[i] & 15;
            cArr[i * f15133f] = f15129a[(bArr[i] & 240) >> 4];
            cArr[(i * f15133f) + f15132e] = f15129a[i2];
        }
        return new String(cArr);
    }

    private static MessageDigest m17183a(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    private Header m17184a(Credentials credentials, HttpRequest httpRequest) {
        int i;
        String parameter = getParameter("uri");
        String parameter2 = getParameter("realm");
        String parameter3 = getParameter("nonce");
        String parameter4 = getParameter("opaque");
        String parameter5 = getParameter("methodname");
        String parameter6 = getParameter("algorithm");
        if (parameter6 == null) {
            parameter6 = "MD5";
        }
        Set hashSet = new HashSet(8);
        int i2 = f15130c;
        String parameter7 = getParameter("qop");
        if (parameter7 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(parameter7, MiPushClient.ACCEPT_TIME_SEPARATOR);
            while (stringTokenizer.hasMoreTokens()) {
                hashSet.add(stringTokenizer.nextToken().trim().toLowerCase(Locale.ENGLISH));
            }
            if ((httpRequest instanceof HttpEntityEnclosingRequest) && hashSet.contains("auth-int")) {
                i2 = f15132e;
            } else if (hashSet.contains("auth")) {
                i2 = f15133f;
            }
            i = i2;
        } else {
            i = f15131d;
        }
        if (i == f15130c) {
            throw new AuthenticationException("None of the qop methods is supported: " + parameter7);
        }
        String parameter8 = getParameter("charset");
        if (parameter8 == null) {
            parameter8 = "ISO-8859-1";
        }
        parameter7 = parameter6.equalsIgnoreCase("MD5-sess") ? "MD5" : parameter6;
        try {
            MessageDigest a = C3059e.m17183a(parameter7);
            String name = credentials.getUserPrincipal().getName();
            parameter7 = credentials.getPassword();
            if (parameter3.equals(this.f15135g)) {
                this.f15136h++;
            } else {
                this.f15136h = 1;
                this.f15137i = null;
                this.f15135g = parameter3;
            }
            Appendable stringBuilder = new StringBuilder(Opcodes.ACC_NATIVE);
            Formatter formatter = new Formatter(stringBuilder, Locale.US);
            Object[] objArr = new Object[f15132e];
            objArr[f15131d] = Long.valueOf(this.f15136h);
            formatter.format("%08x", objArr);
            formatter.close();
            String stringBuilder2 = stringBuilder.toString();
            if (this.f15137i == null) {
                this.f15137i = C3059e.m17185f();
            }
            this.f15138j = null;
            this.f15139k = null;
            if (parameter6.equalsIgnoreCase("MD5-sess")) {
                stringBuilder.setLength(f15131d);
                stringBuilder.append(name).append(':').append(parameter2).append(':').append(parameter7);
                parameter7 = C3059e.m17182a(a.digest(EncodingUtils.getBytes(stringBuilder.toString(), parameter8)));
                stringBuilder.setLength(f15131d);
                stringBuilder.append(parameter7).append(':').append(parameter3).append(':').append(this.f15137i);
                this.f15138j = stringBuilder.toString();
            } else {
                stringBuilder.setLength(f15131d);
                stringBuilder.append(name).append(':').append(parameter2).append(':').append(parameter7);
                this.f15138j = stringBuilder.toString();
            }
            String a2 = C3059e.m17182a(a.digest(EncodingUtils.getBytes(this.f15138j, parameter8)));
            if (i == f15133f) {
                this.f15139k = parameter5 + ':' + parameter;
            } else if (i == f15132e) {
                HttpEntity httpEntity = null;
                if (httpRequest instanceof HttpEntityEnclosingRequest) {
                    httpEntity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
                }
                if (httpEntity == null || httpEntity.isRepeatable()) {
                    OutputStream c3062h = new C3062h(a);
                    if (httpEntity != null) {
                        try {
                            httpEntity.writeTo(c3062h);
                        } catch (Throwable e) {
                            throw new AuthenticationException("I/O error reading entity content", e);
                        }
                    }
                    c3062h.close();
                    this.f15139k = parameter5 + ':' + parameter + ':' + C3059e.m17182a(c3062h.m17196a());
                } else if (hashSet.contains("auth")) {
                    i = f15133f;
                    this.f15139k = parameter5 + ':' + parameter;
                } else {
                    throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
                }
            } else {
                this.f15139k = parameter5 + ':' + parameter;
            }
            parameter7 = C3059e.m17182a(a.digest(EncodingUtils.getBytes(this.f15139k, parameter8)));
            if (i == 0) {
                stringBuilder.setLength(f15131d);
                stringBuilder.append(a2).append(':').append(parameter3).append(':').append(parameter7);
                parameter8 = stringBuilder.toString();
            } else {
                stringBuilder.setLength(f15131d);
                stringBuilder.append(a2).append(':').append(parameter3).append(':').append(stringBuilder2).append(':').append(this.f15137i).append(':').append(i == f15132e ? "auth-int" : "auth").append(':').append(parameter7);
                parameter8 = stringBuilder.toString();
            }
            parameter8 = C3059e.m17182a(a.digest(EncodingUtils.getAsciiBytes(parameter8)));
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (m17172a()) {
                charArrayBuffer.append(C3004e.f15002N);
            } else {
                charArrayBuffer.append(C3004e.f15022h);
            }
            charArrayBuffer.append(": Digest ");
            List arrayList = new ArrayList(20);
            arrayList.add(new BasicNameValuePair(RConversationStorage.PRIMARY_KEY, name));
            arrayList.add(new BasicNameValuePair("realm", parameter2));
            arrayList.add(new BasicNameValuePair("nonce", parameter3));
            arrayList.add(new BasicNameValuePair("uri", parameter));
            arrayList.add(new BasicNameValuePair("response", parameter8));
            if (i != 0) {
                arrayList.add(new BasicNameValuePair("qop", i == f15132e ? "auth-int" : "auth"));
                arrayList.add(new BasicNameValuePair("nc", stringBuilder2));
                arrayList.add(new BasicNameValuePair("cnonce", this.f15137i));
            }
            arrayList.add(new BasicNameValuePair("algorithm", parameter6));
            if (parameter4 != null) {
                arrayList.add(new BasicNameValuePair("opaque", parameter4));
            }
            for (i2 = f15131d; i2 < arrayList.size(); i2 += f15132e) {
                BasicNameValuePair basicNameValuePair = (BasicNameValuePair) arrayList.get(i2);
                if (i2 > 0) {
                    charArrayBuffer.append(", ");
                }
                String name2 = basicNameValuePair.getName();
                Object obj = ("nc".equals(name2) || "qop".equals(name2) || "algorithm".equals(name2)) ? f15132e : null;
                C3209a.f15655b.formatNameValuePair(charArrayBuffer, basicNameValuePair, obj == null);
            }
            return new BufferedHeader(charArrayBuffer);
        } catch (UnsupportedDigestAlgorithmException e2) {
            throw new AuthenticationException("Unsuppported digest algorithm: " + parameter7);
        }
    }

    public static String m17185f() {
        byte[] bArr = new byte[8];
        new SecureRandom().nextBytes(bArr);
        return C3059e.m17182a(bArr);
    }

    public Header m17186a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) credentials, "Credentials");
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if (getParameter("realm") == null) {
            throw new AuthenticationException("missing realm in challenge");
        } else if (getParameter("nonce") == null) {
            throw new AuthenticationException("missing nonce in challenge");
        } else {
            m17178h().put("methodname", httpRequest.getRequestLine().getMethod());
            m17178h().put("uri", httpRequest.getRequestLine().getUri());
            if (getParameter("charset") == null) {
                m17178h().put("charset", m17175a(httpRequest));
            }
            return m17184a(credentials, httpRequest);
        }
    }

    public void m17187a(String str, String str2) {
        m17178h().put(str, str2);
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return m17186a(credentials, httpRequest, new C3223a());
    }

    String m17188c() {
        return this.f15137i;
    }

    String m17189d() {
        return this.f15138j;
    }

    String m17190e() {
        return this.f15139k;
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isComplete() {
        return "true".equalsIgnoreCase(getParameter("stale")) ? false : this.f15134b;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void processChallenge(Header header) {
        super.processChallenge(header);
        this.f15134b = true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DIGEST [complete=").append(this.f15134b).append(", nonce=").append(this.f15135g).append(", nc=").append(this.f15136h).append("]");
        return stringBuilder.toString();
    }
}
