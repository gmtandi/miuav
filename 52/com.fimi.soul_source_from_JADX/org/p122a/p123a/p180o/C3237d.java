package org.p122a.p123a.p180o;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p154h.C3050e;

/* renamed from: org.a.a.o.d */
public final class C3237d {
    private C3237d() {
    }

    public static String m17900a(HttpEntity httpEntity, String str) {
        return C3237d.m17901a(httpEntity, str != null ? Charset.forName(str) : null);
    }

    public static String m17901a(HttpEntity httpEntity, Charset charset) {
        String charArrayBuffer;
        Charset charset2 = null;
        boolean z = false;
        C3234a.m17886a((Object) httpEntity, "Entity");
        InputStream content = httpEntity.getContent();
        if (content != null) {
            try {
                if (httpEntity.getContentLength() <= 2147483647L) {
                    z = true;
                }
                C3234a.m17888a(z, "HTTP entity too large to be buffered in memory");
                int contentLength = (int) httpEntity.getContentLength();
                if (contentLength < 0) {
                    contentLength = Opcodes.ACC_SYNTHETIC;
                }
                C3050e a = C3050e.m17159a(httpEntity);
                if (a != null) {
                    charset2 = a.m17167b();
                }
                if (charset2 == null) {
                    charset2 = charset;
                }
                if (charset2 == null) {
                    charset2 = Charset.forName("ISO-8859-1");
                }
                Reader inputStreamReader = new InputStreamReader(content, charset2);
                CharArrayBuffer charArrayBuffer2 = new CharArrayBuffer(contentLength);
                char[] cArr = new char[SmileConstants.MAX_SHARED_STRING_VALUES];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    charArrayBuffer2.append(cArr, 0, read);
                }
                charArrayBuffer = charArrayBuffer2.toString();
                content.close();
            } catch (UnsupportedCharsetException e) {
                throw new UnsupportedEncodingException(e.getMessage());
            } catch (Throwable th) {
                content.close();
            }
        }
        return charArrayBuffer;
    }

    public static void m17902a(HttpEntity httpEntity) {
        try {
            C3237d.m17904b(httpEntity);
        } catch (IOException e) {
        }
    }

    public static void m17903a(HttpResponse httpResponse, HttpEntity httpEntity) {
        C3234a.m17886a((Object) httpResponse, "Response");
        C3237d.m17904b(httpResponse.getEntity());
        httpResponse.setEntity(httpEntity);
    }

    public static void m17904b(HttpEntity httpEntity) {
        if (httpEntity != null && httpEntity.isStreaming()) {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                content.close();
            }
        }
    }

    public static byte[] m17905c(HttpEntity httpEntity) {
        int i = Opcodes.ACC_SYNTHETIC;
        boolean z = false;
        C3234a.m17886a((Object) httpEntity, "Entity");
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return null;
        }
        try {
            if (httpEntity.getContentLength() <= 2147483647L) {
                z = true;
            }
            C3234a.m17888a(z, "HTTP entity too large to be buffered in memory");
            int contentLength = (int) httpEntity.getContentLength();
            if (contentLength >= 0) {
                i = contentLength;
            }
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
            byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.append(bArr, 0, read);
            }
            bArr = byteArrayBuffer.toByteArray();
            return bArr;
        } finally {
            content.close();
        }
    }

    @Deprecated
    public static String m17906d(HttpEntity httpEntity) {
        C3234a.m17886a((Object) httpEntity, "Entity");
        if (httpEntity.getContentType() == null) {
            return null;
        }
        HeaderElement[] elements = httpEntity.getContentType().getElements();
        if (elements.length <= 0) {
            return null;
        }
        NameValuePair parameterByName = elements[0].getParameterByName("charset");
        return parameterByName != null ? parameterByName.getValue() : null;
    }

    @Deprecated
    public static String m17907e(HttpEntity httpEntity) {
        C3234a.m17886a((Object) httpEntity, "Entity");
        if (httpEntity.getContentType() == null) {
            return null;
        }
        HeaderElement[] elements = httpEntity.getContentType().getElements();
        return elements.length > 0 ? elements[0].getName() : null;
    }

    public static String m17908f(HttpEntity httpEntity) {
        return C3237d.m17901a(httpEntity, (Charset) null);
    }
}
