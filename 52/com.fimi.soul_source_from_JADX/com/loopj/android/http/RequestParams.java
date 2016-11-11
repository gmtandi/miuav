package com.loopj.android.http;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams {
    private static String ENCODING;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;
    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, ArrayList<String>> urlParamsWithArray;

    class FileWrapper {
        public String contentType;
        public String fileName;
        public InputStream inputStream;

        public FileWrapper(InputStream inputStream, String str, String str2) {
            this.inputStream = inputStream;
            this.fileName = str;
            this.contentType = str2;
        }

        public String getFileName() {
            return this.fileName != null ? this.fileName : "nofilename";
        }
    }

    static {
        ENCODING = C1142e.f5201a;
    }

    public RequestParams() {
        init();
    }

    public RequestParams(String str, String str2) {
        init();
        put(str, str2);
    }

    public RequestParams(Map<String, String> map) {
        init();
        for (Entry entry : map.entrySet()) {
            put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public RequestParams(Object... objArr) {
        init();
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < length; i += 2) {
            put(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
        }
    }

    private void init() {
        this.urlParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.urlParamsWithArray = new ConcurrentHashMap();
    }

    public HttpEntity getEntity() {
        if (this.fileParams.isEmpty()) {
            try {
                return new UrlEncodedFormEntity(getParamsList(), ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        HttpEntity simpleMultipartEntity = new SimpleMultipartEntity();
        for (Entry entry : this.urlParams.entrySet()) {
            simpleMultipartEntity.addPart((String) entry.getKey(), (String) entry.getValue());
        }
        int size = this.fileParams.entrySet().size() - 1;
        int i = 0;
        for (Entry entry2 : this.fileParams.entrySet()) {
            FileWrapper fileWrapper = (FileWrapper) entry2.getValue();
            if (fileWrapper.inputStream != null) {
                boolean z = i == size;
                if (fileWrapper.contentType != null) {
                    simpleMultipartEntity.addPart((String) entry2.getKey(), fileWrapper.getFileName(), fileWrapper.inputStream, fileWrapper.contentType, z);
                } else {
                    simpleMultipartEntity.addPart((String) entry2.getKey(), fileWrapper.getFileName(), fileWrapper.inputStream, z);
                }
            }
            i++;
        }
        for (Entry entry22 : this.urlParamsWithArray.entrySet()) {
            Iterator it = ((ArrayList) entry22.getValue()).iterator();
            while (it.hasNext()) {
                simpleMultipartEntity.addPart((String) entry22.getKey(), (String) it.next());
            }
        }
        return simpleMultipartEntity;
    }

    protected String getParamString() {
        return URLEncodedUtils.format(getParamsList(), ENCODING);
    }

    protected List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.urlParams.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        for (Entry entry2 : this.urlParamsWithArray.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                linkedList.add(new BasicNameValuePair((String) entry2.getKey(), (String) it.next()));
            }
        }
        return linkedList;
    }

    public void put(String str, File file) {
        put(str, new FileInputStream(file), file.getName());
    }

    public void put(String str, InputStream inputStream) {
        put(str, inputStream, null);
    }

    public void put(String str, InputStream inputStream, String str2) {
        put(str, inputStream, str2, null);
    }

    public void put(String str, InputStream inputStream, String str2, String str3) {
        if (str != null && inputStream != null) {
            this.fileParams.put(str, new FileWrapper(inputStream, str2, str3));
        }
    }

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            this.urlParams.put(str, str2);
        }
    }

    public void put(String str, ArrayList<String> arrayList) {
        if (str != null && arrayList != null) {
            this.urlParamsWithArray.put(str, arrayList);
        }
    }

    public void remove(String str) {
        this.urlParams.remove(str);
        this.fileParams.remove(str);
        this.urlParamsWithArray.remove(str);
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
        for (Entry entry2 : this.fileParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry22 : this.urlParamsWithArray.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            ArrayList arrayList = (ArrayList) entry22.getValue();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (arrayList.indexOf(str) != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append((String) entry22.getKey());
                stringBuilder.append("=");
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}
