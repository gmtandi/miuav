package com.baidu.tts.loopj;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams implements Serializable {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    protected static final String LOG_TAG = "RequestParams";
    protected boolean autoCloseInputStreams;
    protected String contentEncoding;
    protected String elapsedFieldInJsonStreamer;
    protected final ConcurrentHashMap<String, List<FileWrapper>> fileArrayParams;
    protected final ConcurrentHashMap<String, FileWrapper> fileParams;
    protected boolean forceMultipartEntity;
    protected boolean isRepeatable;
    protected final ConcurrentHashMap<String, StreamWrapper> streamParams;
    protected final ConcurrentHashMap<String, String> urlParams;
    protected final ConcurrentHashMap<String, Object> urlParamsWithObjects;
    protected boolean useJsonStreamer;

    /* renamed from: com.baidu.tts.loopj.RequestParams.1 */
    class C08501 extends HashMap<String, String> {
        final /* synthetic */ String val$key;
        final /* synthetic */ String val$value;

        C08501(String str, String str2) {
            this.val$key = str;
            this.val$value = str2;
            put(this.val$key, this.val$value);
        }
    }

    public class FileWrapper implements Serializable {
        public final String contentType;
        public final String customFileName;
        public final File file;

        public FileWrapper(File file, String str, String str2) {
            this.file = file;
            this.contentType = str;
            this.customFileName = str2;
        }
    }

    public class StreamWrapper {
        public final boolean autoClose;
        public final String contentType;
        public final InputStream inputStream;
        public final String name;

        public StreamWrapper(InputStream inputStream, String str, String str2, boolean z) {
            this.inputStream = inputStream;
            this.name = str;
            this.contentType = str2;
            this.autoClose = z;
        }

        static StreamWrapper newInstance(InputStream inputStream, String str, String str2, boolean z) {
            if (str2 == null) {
                str2 = RequestParams.APPLICATION_OCTET_STREAM;
            }
            return new StreamWrapper(inputStream, str, str2, z);
        }
    }

    public RequestParams() {
        this((Map) null);
    }

    public RequestParams(String str, String str2) {
        this(new C08501(str, str2));
    }

    public RequestParams(Map<String, String> map) {
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.urlParams = new ConcurrentHashMap();
        this.streamParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.fileArrayParams = new ConcurrentHashMap();
        this.urlParamsWithObjects = new ConcurrentHashMap();
        this.contentEncoding = C1142e.f5201a;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public RequestParams(Object... objArr) {
        int i = 0;
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.urlParams = new ConcurrentHashMap();
        this.streamParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.fileArrayParams = new ConcurrentHashMap();
        this.urlParamsWithObjects = new ConcurrentHashMap();
        this.contentEncoding = C1142e.f5201a;
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        while (i < length) {
            put(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
            i += 2;
        }
    }

    private HttpEntity createFormEntity() {
        try {
            return new UrlEncodedFormEntity(getParamsList(), this.contentEncoding);
        } catch (Throwable e) {
            AsyncHttpClient.log.m6891e(LOG_TAG, "createFormEntity failed", e);
            return null;
        }
    }

    private HttpEntity createJsonStreamerEntity(ResponseHandlerInterface responseHandlerInterface) {
        boolean z = (this.fileParams.isEmpty() && this.streamParams.isEmpty()) ? false : true;
        HttpEntity jsonStreamerEntity = new JsonStreamerEntity(responseHandlerInterface, z, this.elapsedFieldInJsonStreamer);
        for (Entry entry : this.urlParams.entrySet()) {
            jsonStreamerEntity.addPart((String) entry.getKey(), entry.getValue());
        }
        for (Entry entry2 : this.urlParamsWithObjects.entrySet()) {
            jsonStreamerEntity.addPart((String) entry2.getKey(), entry2.getValue());
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            jsonStreamerEntity.addPart((String) entry22.getKey(), entry22.getValue());
        }
        for (Entry entry222 : this.streamParams.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry222.getValue();
            if (streamWrapper.inputStream != null) {
                jsonStreamerEntity.addPart((String) entry222.getKey(), StreamWrapper.newInstance(streamWrapper.inputStream, streamWrapper.name, streamWrapper.contentType, streamWrapper.autoClose));
            }
        }
        return jsonStreamerEntity;
    }

    private HttpEntity createMultipartEntity(ResponseHandlerInterface responseHandlerInterface) {
        HttpEntity simpleMultipartEntity = new SimpleMultipartEntity(responseHandlerInterface);
        simpleMultipartEntity.setIsRepeatable(this.isRepeatable);
        for (Entry entry : this.urlParams.entrySet()) {
            simpleMultipartEntity.addPartWithCharset((String) entry.getKey(), (String) entry.getValue(), this.contentEncoding);
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            simpleMultipartEntity.addPartWithCharset(basicNameValuePair.getName(), basicNameValuePair.getValue(), this.contentEncoding);
        }
        for (Entry entry2 : this.streamParams.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry2.getValue();
            if (streamWrapper.inputStream != null) {
                simpleMultipartEntity.addPart((String) entry2.getKey(), streamWrapper.name, streamWrapper.inputStream, streamWrapper.contentType);
            }
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            FileWrapper fileWrapper = (FileWrapper) entry22.getValue();
            simpleMultipartEntity.addPart((String) entry22.getKey(), fileWrapper.file, fileWrapper.contentType, fileWrapper.customFileName);
        }
        for (Entry entry222 : this.fileArrayParams.entrySet()) {
            for (FileWrapper fileWrapper2 : (List) entry222.getValue()) {
                simpleMultipartEntity.addPart((String) entry222.getKey(), fileWrapper2.file, fileWrapper2.contentType, fileWrapper2.customFileName);
            }
        }
        return simpleMultipartEntity;
    }

    private List<BasicNameValuePair> getParamsList(String str, Object obj) {
        List<BasicNameValuePair> linkedList = new LinkedList();
        if (obj instanceof Map) {
            Map map = (Map) obj;
            List arrayList = new ArrayList(map.keySet());
            if (arrayList.size() > 0 && (arrayList.get(0) instanceof Comparable)) {
                Collections.sort(arrayList);
            }
            for (Object next : arrayList) {
                if (next instanceof String) {
                    Object obj2 = map.get(next);
                    if (obj2 != null) {
                        String str2;
                        if (str == null) {
                            str2 = (String) next;
                        } else {
                            str2 = String.format(Locale.US, "%s[%s]", new Object[]{str, next});
                        }
                        linkedList.addAll(getParamsList(str2, obj2));
                    }
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            r3 = list.size();
            for (r0 = 0; r0 < r3; r0++) {
                linkedList.addAll(getParamsList(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), list.get(r0)));
            }
        } else if (obj instanceof Object[]) {
            for (Object paramsList : (Object[]) obj) {
                linkedList.addAll(getParamsList(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), paramsList));
            }
        } else if (obj instanceof Set) {
            for (Object paramsList2 : (Set) obj) {
                linkedList.addAll(getParamsList(str, paramsList2));
            }
        } else {
            linkedList.add(new BasicNameValuePair(str, obj.toString()));
        }
        return linkedList;
    }

    public void add(String str, String str2) {
        if (str != null && str2 != null) {
            Object obj = this.urlParamsWithObjects.get(str);
            if (obj == null) {
                obj = new HashSet();
                put(str, obj);
            }
            if (obj instanceof List) {
                ((List) obj).add(str2);
            } else if (obj instanceof Set) {
                ((Set) obj).add(str2);
            }
        }
    }

    public HttpEntity getEntity(ResponseHandlerInterface responseHandlerInterface) {
        return this.useJsonStreamer ? createJsonStreamerEntity(responseHandlerInterface) : (!this.forceMultipartEntity && this.streamParams.isEmpty() && this.fileParams.isEmpty() && this.fileArrayParams.isEmpty()) ? createFormEntity() : createMultipartEntity(responseHandlerInterface);
    }

    protected String getParamString() {
        return URLEncodedUtils.format(getParamsList(), this.contentEncoding);
    }

    protected List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.urlParams.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        linkedList.addAll(getParamsList(null, this.urlParamsWithObjects));
        return linkedList;
    }

    public boolean has(String str) {
        return (this.urlParams.get(str) == null && this.streamParams.get(str) == null && this.fileParams.get(str) == null && this.urlParamsWithObjects.get(str) == null && this.fileArrayParams.get(str) == null) ? false : true;
    }

    public void put(String str, int i) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(i));
        }
    }

    public void put(String str, long j) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(j));
        }
    }

    public void put(String str, File file) {
        put(str, file, null, null);
    }

    public void put(String str, File file, String str2) {
        put(str, file, str2, null);
    }

    public void put(String str, File file, String str2, String str3) {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        } else if (str != null) {
            this.fileParams.put(str, new FileWrapper(file, str2, str3));
        }
    }

    public void put(String str, InputStream inputStream) {
        put(str, inputStream, null);
    }

    public void put(String str, InputStream inputStream, String str2) {
        put(str, inputStream, str2, null);
    }

    public void put(String str, InputStream inputStream, String str2, String str3) {
        put(str, inputStream, str2, str3, this.autoCloseInputStreams);
    }

    public void put(String str, InputStream inputStream, String str2, String str3, boolean z) {
        if (str != null && inputStream != null) {
            this.streamParams.put(str, StreamWrapper.newInstance(inputStream, str2, str3, z));
        }
    }

    public void put(String str, Object obj) {
        if (str != null && obj != null) {
            this.urlParamsWithObjects.put(str, obj);
        }
    }

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            this.urlParams.put(str, str2);
        }
    }

    public void put(String str, String str2, File file) {
        put(str, file, null, str2);
    }

    public void put(String str, File[] fileArr) {
        put(str, fileArr, null, null);
    }

    public void put(String str, File[] fileArr, String str2, String str3) {
        if (str != null) {
            List arrayList = new ArrayList();
            for (File file : fileArr) {
                if (file == null || !file.exists()) {
                    throw new FileNotFoundException();
                }
                arrayList.add(new FileWrapper(file, str2, str3));
            }
            this.fileArrayParams.put(str, arrayList);
        }
    }

    public void remove(String str) {
        this.urlParams.remove(str);
        this.streamParams.remove(str);
        this.fileParams.remove(str);
        this.urlParamsWithObjects.remove(str);
        this.fileArrayParams.remove(str);
    }

    public void setAutoCloseInputStreams(boolean z) {
        this.autoCloseInputStreams = z;
    }

    public void setContentEncoding(String str) {
        if (str != null) {
            this.contentEncoding = str;
        } else {
            AsyncHttpClient.log.m6888d(LOG_TAG, "setContentEncoding called with null attribute");
        }
    }

    public void setElapsedFieldInJsonStreamer(String str) {
        this.elapsedFieldInJsonStreamer = str;
    }

    public void setForceMultipartEntityContentType(boolean z) {
        this.forceMultipartEntity = z;
    }

    public void setHttpEntityIsRepeatable(boolean z) {
        this.isRepeatable = z;
    }

    public void setUseJsonStreamer(boolean z) {
        this.useJsonStreamer = z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.urlParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.streamParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("STREAM");
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry22.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry222 : this.fileArrayParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry222.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILES(SIZE=").append(((List) entry222.getValue()).size()).append(")");
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(basicNameValuePair.getName());
            stringBuilder.append("=");
            stringBuilder.append(basicNameValuePair.getValue());
        }
        return stringBuilder.toString();
    }
}
