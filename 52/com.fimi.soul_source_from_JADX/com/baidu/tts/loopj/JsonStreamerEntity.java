package com.baidu.tts.loopj;

import android.text.TextUtils;
import com.baidu.tts.loopj.RequestParams.FileWrapper;
import com.baidu.tts.loopj.RequestParams.StreamWrapper;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.SocialConstants;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.p125c.C3022o;

public class JsonStreamerEntity implements HttpEntity {
    private static final int BUFFER_SIZE = 4096;
    private static final UnsupportedOperationException ERR_UNSUPPORTED;
    private static final Header HEADER_GZIP_ENCODING;
    private static final Header HEADER_JSON_CONTENT;
    private static final byte[] JSON_FALSE;
    private static final byte[] JSON_NULL;
    private static final byte[] JSON_TRUE;
    private static final String LOG_TAG = "JsonStreamerEntity";
    private static final byte[] STREAM_CONTENTS;
    private static final byte[] STREAM_NAME;
    private static final byte[] STREAM_TYPE;
    private final byte[] buffer;
    private final Header contentEncoding;
    private final byte[] elapsedField;
    private final Map<String, Object> jsonParams;
    private final ResponseHandlerInterface progressHandler;

    static {
        ERR_UNSUPPORTED = new UnsupportedOperationException("Unsupported operation in this implementation.");
        JSON_TRUE = "true".getBytes();
        JSON_FALSE = "false".getBytes();
        JSON_NULL = "null".getBytes();
        STREAM_NAME = escape(User.FN_NAME);
        STREAM_TYPE = escape(SocialConstants.PARAM_TYPE);
        STREAM_CONTENTS = escape("contents");
        HEADER_JSON_CONTENT = new BasicHeader(C3004e.f15031q, RequestParams.APPLICATION_JSON);
        HEADER_GZIP_ENCODING = new BasicHeader(C3004e.f15025k, AsyncHttpClient.ENCODING_GZIP);
    }

    public JsonStreamerEntity(ResponseHandlerInterface responseHandlerInterface, boolean z, String str) {
        byte[] bArr = null;
        this.buffer = new byte[BUFFER_SIZE];
        this.jsonParams = new HashMap();
        this.progressHandler = responseHandlerInterface;
        this.contentEncoding = z ? HEADER_GZIP_ENCODING : null;
        if (!TextUtils.isEmpty(str)) {
            bArr = escape(str);
        }
        this.elapsedField = bArr;
    }

    private void endMetaData(OutputStream outputStream) {
        outputStream.write(34);
    }

    static byte[] escape(String str) {
        if (str == null) {
            return JSON_NULL;
        }
        StringBuilder stringBuilder = new StringBuilder(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        stringBuilder.append(C3022o.f15057e);
        int length = str.length();
        int i = -1;
        while (true) {
            int i2 = i + 1;
            if (i2 < length) {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case Type.DOUBLE /*8*/:
                        stringBuilder.append("\\b");
                        break;
                    case Type.ARRAY /*9*/:
                        stringBuilder.append("\\t");
                        break;
                    case Type.OBJECT /*10*/:
                        stringBuilder.append("\\n");
                        break;
                    case Opcodes.FCONST_1 /*12*/:
                        stringBuilder.append("\\f");
                        break;
                    case Opcodes.FCONST_2 /*13*/:
                        stringBuilder.append("\\r");
                        break;
                    case C1873o.f9538Z /*34*/:
                        stringBuilder.append("\\\"");
                        break;
                    case Opcodes.DUP2 /*92*/:
                        stringBuilder.append("\\\\");
                        break;
                    default:
                        if (charAt > '\u001f' && ((charAt < '\u007f' || charAt > '\u009f') && (charAt < '\u2000' || charAt > '\u20ff'))) {
                            stringBuilder.append(charAt);
                            break;
                        }
                        String toHexString = Integer.toHexString(charAt);
                        stringBuilder.append("\\u");
                        int length2 = 4 - toHexString.length();
                        for (i = 0; i < length2; i++) {
                            stringBuilder.append('0');
                        }
                        stringBuilder.append(toHexString.toUpperCase(Locale.US));
                        break;
                        break;
                }
                i = i2;
            } else {
                stringBuilder.append(C3022o.f15057e);
                return stringBuilder.toString().getBytes();
            }
        }
    }

    private void writeMetaData(OutputStream outputStream, String str, String str2) {
        outputStream.write(STREAM_NAME);
        outputStream.write(58);
        outputStream.write(escape(str));
        outputStream.write(44);
        outputStream.write(STREAM_TYPE);
        outputStream.write(58);
        outputStream.write(escape(str2));
        outputStream.write(44);
        outputStream.write(STREAM_CONTENTS);
        outputStream.write(58);
        outputStream.write(34);
    }

    private void writeToFromFile(OutputStream outputStream, FileWrapper fileWrapper) {
        writeMetaData(outputStream, fileWrapper.file.getName(), fileWrapper.contentType);
        long j = 0;
        long length = fileWrapper.file.length();
        InputStream fileInputStream = new FileInputStream(fileWrapper.file);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        while (true) {
            int read = fileInputStream.read(this.buffer);
            if (read != -1) {
                base64OutputStream.write(this.buffer, 0, read);
                j += (long) read;
                this.progressHandler.sendProgressMessage(j, length);
            } else {
                AsyncHttpClient.silentCloseOutputStream(base64OutputStream);
                endMetaData(outputStream);
                AsyncHttpClient.silentCloseInputStream(fileInputStream);
                return;
            }
        }
    }

    private void writeToFromStream(OutputStream outputStream, StreamWrapper streamWrapper) {
        writeMetaData(outputStream, streamWrapper.name, streamWrapper.contentType);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        while (true) {
            int read = streamWrapper.inputStream.read(this.buffer);
            if (read == -1) {
                break;
            }
            base64OutputStream.write(this.buffer, 0, read);
        }
        AsyncHttpClient.silentCloseOutputStream(base64OutputStream);
        endMetaData(outputStream);
        if (streamWrapper.autoClose) {
            AsyncHttpClient.silentCloseInputStream(streamWrapper.inputStream);
        }
    }

    public void addPart(String str, Object obj) {
        this.jsonParams.put(str, obj);
    }

    public void consumeContent() {
    }

    public InputStream getContent() {
        throw ERR_UNSUPPORTED;
    }

    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    public long getContentLength() {
        return -1;
    }

    public Header getContentType() {
        return HEADER_JSON_CONTENT;
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalStateException("Output stream cannot be null.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.contentEncoding != null) {
            outputStream = new GZIPOutputStream(outputStream, BUFFER_SIZE);
        }
        outputStream.write(Opcodes.LSHR);
        Set<String> keySet = this.jsonParams.keySet();
        int size = keySet.size();
        if (size > 0) {
            int i = 0;
            for (String str : keySet) {
                int i2 = i + 1;
                Object obj = this.jsonParams.get(str);
                outputStream.write(escape(str));
                outputStream.write(58);
                if (obj == null) {
                    outputStream.write(JSON_NULL);
                } else {
                    boolean z = obj instanceof FileWrapper;
                    if (z || (obj instanceof StreamWrapper)) {
                        outputStream.write(Opcodes.LSHR);
                        if (z) {
                            writeToFromFile(outputStream, (FileWrapper) obj);
                        } else {
                            try {
                                writeToFromStream(outputStream, (StreamWrapper) obj);
                            } catch (Throwable th) {
                                if (this.elapsedField != null || i2 < size) {
                                    outputStream.write(44);
                                }
                            }
                        }
                        outputStream.write(Opcodes.LUSHR);
                    } else if (obj instanceof JsonValueInterface) {
                        outputStream.write(((JsonValueInterface) obj).getEscapedJsonValue());
                    } else if (obj instanceof JSONObject) {
                        outputStream.write(obj.toString().getBytes());
                    } else if (obj instanceof JSONArray) {
                        outputStream.write(obj.toString().getBytes());
                    } else if (obj instanceof Boolean) {
                        outputStream.write(((Boolean) obj).booleanValue() ? JSON_TRUE : JSON_FALSE);
                    } else if (obj instanceof Long) {
                        outputStream.write((((Number) obj).longValue() + C2915a.f14760f).getBytes());
                    } else if (obj instanceof Double) {
                        outputStream.write((((Number) obj).doubleValue() + C2915a.f14760f).getBytes());
                    } else if (obj instanceof Float) {
                        outputStream.write((((Number) obj).floatValue() + C2915a.f14760f).getBytes());
                    } else if (obj instanceof Integer) {
                        outputStream.write((((Number) obj).intValue() + C2915a.f14760f).getBytes());
                    } else {
                        outputStream.write(escape(obj.toString()));
                    }
                }
                if (this.elapsedField != null || i2 < size) {
                    outputStream.write(44);
                }
                i = i2;
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.elapsedField != null) {
                outputStream.write(this.elapsedField);
                outputStream.write(58);
                outputStream.write((currentTimeMillis2 + C2915a.f14760f).getBytes());
            }
            AsyncHttpClient.log.m6892i(LOG_TAG, "Uploaded JSON in " + Math.floor((double) (currentTimeMillis2 / 1000)) + " seconds");
        }
        outputStream.write(Opcodes.LUSHR);
        outputStream.flush();
        AsyncHttpClient.silentCloseOutputStream(outputStream);
    }
}
