package com.xiaomi.infra.galaxy.fds.android;

import com.facebook.common.util.UriUtil;
import com.google.gson.Gson;
import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import com.xiaomi.infra.galaxy.fds.android.model.HttpMethod;
import com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult;
import com.xiaomi.infra.galaxy.fds.android.model.ThumbParam;
import com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList;
import com.xiaomi.infra.galaxy.fds.android.model.UserParam;
import com.xiaomi.infra.galaxy.fds.android.util.Args;
import com.xiaomi.infra.galaxy.fds.android.util.Consts;
import com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream;
import com.xiaomi.infra.galaxy.fds.android.util.RequestFactory;
import com.xiaomi.infra.galaxy.fds.android.util.Util;
import it.p074a.p075a.C2799f;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;

public class GalaxyFDSClientImpl implements GalaxyFDSClient {
    private static final String HTTPS_SCHEME = "https";
    private static final String HTTP_SCHEME = "http";
    private static final String LOG_TAG = "GalaxyFDSClientImpl";
    private static final boolean TEST_MODE;
    private final FDSClientConfiguration config;
    private final HttpClient httpClient;

    static {
        String property = System.getProperty("java.runtime.name");
        if (property == null || !property.equals("android runtime")) {
            TEST_MODE = true;
        } else {
            TEST_MODE = false;
        }
    }

    public GalaxyFDSClientImpl(FDSClientConfiguration fDSClientConfiguration) {
        this.config = fDSClientConfiguration;
        this.httpClient = createHttpClient(this.config);
    }

    @Deprecated
    public GalaxyFDSClientImpl(String str, GalaxyFDSCredential galaxyFDSCredential, FDSClientConfiguration fDSClientConfiguration) {
        this.config = fDSClientConfiguration;
        this.config.setCredential(galaxyFDSCredential);
        this.httpClient = createHttpClient(this.config);
    }

    private void abortMultipartUpload(String str, String str2, String str3) {
        InputStream inputStream = null;
        String str4 = this.config.getUploadBaseUri() + "/" + str + "/" + str2 + "?uploadId=" + str3;
        try {
            HttpResponse execute = this.httpClient.execute(RequestFactory.createRequest(str4, this.config.getCredential(), HttpMethod.DELETE, null));
            inputStream = execute.getEntity().getContent();
            if (execute.getStatusLine().getStatusCode() != C2799f.f14282t) {
                throw new GalaxyFDSClientException("Unable to upload object[" + str + "/" + str2 + "] to URI :" + str4 + ". Fail to abort multipart upload: " + execute.getStatusLine().toString());
            } else if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable e2) {
            throw new GalaxyFDSClientException("Fail to abort multipart upload. URI:" + str4, e2);
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        }
    }

    private PutObjectResult completeMultipartUpload(String str, String str2, String str3, ObjectMetadata objectMetadata, UploadPartResultList uploadPartResultList, List<UserParam> list) {
        Map allMetadata;
        Throwable e;
        InputStream inputStream;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.config.getUploadBaseUri() + "/" + str2 + "/" + str3 + "?uploadId=" + str);
        if (list != null) {
            for (UserParam userParam : list) {
                stringBuilder.append('&');
                stringBuilder.append(userParam.toString());
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (objectMetadata != null) {
            try {
                allMetadata = objectMetadata.getAllMetadata();
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                try {
                    throw new GalaxyFDSClientException("Fail to complete multipart upload. URI:" + stringBuilder2, e);
                } catch (Throwable th) {
                    e = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                inputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw e;
            }
        }
        allMetadata = null;
        HttpUriRequest createRequest = RequestFactory.createRequest(stringBuilder2, this.config.getCredential(), HttpMethod.PUT, allMetadata);
        ((HttpPut) createRequest).setEntity(new StringEntity(new Gson().toJson((Object) uploadPartResultList)));
        HttpResponse execute = this.httpClient.execute(createRequest);
        inputStream = execute.getEntity().getContent();
        try {
            if (execute.getStatusLine().getStatusCode() != C2799f.f14282t) {
                throw new GalaxyFDSClientException("Unable to upload object[" + str2 + "/" + str3 + "] to URI :" + stringBuilder2 + ". Fail to complete multipart upload: " + execute.getStatusLine().toString());
            }
            PutObjectResult putObjectResult = (PutObjectResult) new Gson().fromJson(new InputStreamReader(inputStream), PutObjectResult.class);
            if (putObjectResult == null || putObjectResult.getAccessKeyId() == null || putObjectResult.getSignature() == null || putObjectResult.getExpires() == 0) {
                throw new GalaxyFDSClientException("Fail to parse the result of completing multipart upload. bucket name:" + str2 + ", object name:" + str3 + ", upload ID:" + str);
            }
            putObjectResult.setFdsServiceBaseUri(this.config.getBaseUri());
            putObjectResult.setCdnServiceBaseUri(this.config.getCdnBaseUri());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
            return putObjectResult;
        } catch (IOException e5) {
            e = e5;
            throw new GalaxyFDSClientException("Fail to complete multipart upload. URI:" + stringBuilder2, e);
        }
    }

    private HttpClient createHttpClient(FDSClientConfiguration fDSClientConfiguration) {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, fDSClientConfiguration.getConnectionTimeoutMs());
        HttpConnectionParams.setSoTimeout(basicHttpParams, fDSClientConfiguration.getSocketTimeoutMs());
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        int i = fDSClientConfiguration.getSocketBufferSizeHints()[0];
        int i2 = fDSClientConfiguration.getSocketBufferSizeHints()[1];
        if (i > 0 || i2 > 0) {
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, Math.max(i, i2));
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        if (fDSClientConfiguration.isHttpsEnabled()) {
            SocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme(HTTPS_SCHEME, socketFactory, 443));
        }
        return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    private InitMultipartUploadResult initMultipartUpload(String str, String str2, long j) {
        String str3 = this.config.getUploadBaseUri() + "/" + str + "/" + (str2 == null ? C2915a.f14760f : str2) + "?uploads";
        InputStream inputStream = null;
        try {
            Map hashMap = new HashMap();
            hashMap.put(Consts.ESTIMATED_OBJECT_SIZE, Long.toString(j));
            HttpResponse execute = this.httpClient.execute(RequestFactory.createRequest(str3, this.config.getCredential(), str2 == null ? HttpMethod.POST : HttpMethod.PUT, hashMap));
            inputStream = execute.getEntity().getContent();
            if (execute.getStatusLine().getStatusCode() != C2799f.f14282t) {
                throw new GalaxyFDSClientException("Unable to upload object[" + str + "/" + str2 + "] to URI :" + str3 + ". Fail to initiate multipart upload: " + execute.getStatusLine().toString());
            }
            InitMultipartUploadResult initMultipartUploadResult = (InitMultipartUploadResult) new Gson().fromJson(new InputStreamReader(inputStream), InitMultipartUploadResult.class);
            if (initMultipartUploadResult == null || initMultipartUploadResult.getUploadId() == null || initMultipartUploadResult.getObjectName() == null || initMultipartUploadResult.getBucketName() == null) {
                throw new GalaxyFDSClientException("Fail to parse the result of init multipart upload. bucket name:" + str + ", object name:" + str2);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return initMultipartUploadResult;
        } catch (Throwable e2) {
            throw new GalaxyFDSClientException("Fail to initiate multipart upload. URI:" + str3, e2);
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        }
    }

    private boolean isGetThumbnail(List<UserParam> list) {
        if (list != null) {
            for (UserParam userParam : list) {
                if (userParam instanceof ThumbParam) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult uploadPart(java.lang.String r13, java.lang.String r14, java.lang.String r15, int r16, com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream r17, long r18) {
        /*
        r12 = this;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r12.config;
        r3 = r3.getUploadBaseUri();
        r2 = r2.append(r3);
        r3 = "/";
        r2 = r2.append(r3);
        r2 = r2.append(r14);
        r3 = "/";
        r2 = r2.append(r3);
        r2 = r2.append(r15);
        r3 = "?uploadId=";
        r2 = r2.append(r3);
        r2 = r2.append(r13);
        r3 = "&partNumber=";
        r2 = r2.append(r3);
        r0 = r16;
        r2 = r2.append(r0);
        r6 = r2.toString();
        r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r4 = new byte[r2];
        r5 = new java.io.ByteArrayOutputStream;
        r0 = r18;
        r2 = (int) r0;
        r5.<init>(r2);
        r2 = r18;
    L_0x004b:
        r8 = 0;
        r7 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r7 == 0) goto L_0x0061;
    L_0x0051:
        r7 = r4.length;	 Catch:{ IOException -> 0x011e }
        r8 = (int) r2;	 Catch:{ IOException -> 0x011e }
        r7 = java.lang.Math.min(r7, r8);	 Catch:{ IOException -> 0x011e }
        r8 = 0;
        r0 = r17;
        r7 = r0.read(r4, r8, r7);	 Catch:{ IOException -> 0x011e }
        r8 = -1;
        if (r7 != r8) goto L_0x0116;
    L_0x0061:
        r7 = r5.toByteArray();
        r5.close();	 Catch:{ IOException -> 0x01ea }
    L_0x0068:
        r2 = 0;
        r4 = 0;
        r5 = r2;
    L_0x006b:
        r2 = r12.config;	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r2 = r2.getCredential();	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r3 = com.xiaomi.infra.galaxy.fds.android.model.HttpMethod.PUT;	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r8 = 0;
        r3 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.createRequest(r6, r2, r3, r8);	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r0 = r3;
        r0 = (org.apache.http.client.methods.HttpPut) r0;	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r2 = r0;
        r8 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r8.<init>(r7);	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r2.setEntity(r8);	 Catch:{ GalaxyFDSClientException -> 0x01f8, all -> 0x01f4 }
        r2 = r12.httpClient;	 Catch:{ IOException -> 0x01fc }
        r2 = r2.execute(r3);	 Catch:{ IOException -> 0x01fc }
        r3 = r2.getEntity();	 Catch:{ IOException -> 0x01fc }
        r3 = r3.getContent();	 Catch:{ IOException -> 0x01fc }
        r4 = r2.getStatusLine();	 Catch:{ IOException -> 0x00e9 }
        r4 = r4.getStatusCode();	 Catch:{ IOException -> 0x00e9 }
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 == r8) goto L_0x013a;
    L_0x009e:
        r4 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;	 Catch:{ IOException -> 0x00e9 }
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e9 }
        r8.<init>();	 Catch:{ IOException -> 0x00e9 }
        r9 = "Unable to upload object[";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e9 }
        r8 = r8.append(r14);	 Catch:{ IOException -> 0x00e9 }
        r9 = "/";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e9 }
        r8 = r8.append(r15);	 Catch:{ IOException -> 0x00e9 }
        r9 = "] to URI :";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e9 }
        r8 = r8.append(r6);	 Catch:{ IOException -> 0x00e9 }
        r9 = ". Fail to upload part ";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e9 }
        r0 = r16;
        r8 = r8.append(r0);	 Catch:{ IOException -> 0x00e9 }
        r9 = ": ";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e9 }
        r2 = r2.getStatusLine();	 Catch:{ IOException -> 0x00e9 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x00e9 }
        r2 = r8.append(r2);	 Catch:{ IOException -> 0x00e9 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x00e9 }
        r4.<init>(r2);	 Catch:{ IOException -> 0x00e9 }
        throw r4;	 Catch:{ IOException -> 0x00e9 }
    L_0x00e9:
        r2 = move-exception;
    L_0x00ea:
        r4 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r8 = new java.lang.StringBuilder;	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r8.<init>();	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r9 = "Fail to put part. URI:";
        r8 = r8.append(r9);	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r8 = r8.append(r6);	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r8 = r8.toString();	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        r4.<init>(r8, r2);	 Catch:{ GalaxyFDSClientException -> 0x0103 }
        throw r4;	 Catch:{ GalaxyFDSClientException -> 0x0103 }
    L_0x0103:
        r2 = move-exception;
    L_0x0104:
        r4 = r5 + 1;
        r5 = r12.config;	 Catch:{ all -> 0x010f }
        r5 = r5.getMaxRetryTimes();	 Catch:{ all -> 0x010f }
        if (r4 < r5) goto L_0x0191;
    L_0x010e:
        throw r2;	 Catch:{ all -> 0x010f }
    L_0x010f:
        r2 = move-exception;
    L_0x0110:
        if (r3 == 0) goto L_0x0115;
    L_0x0112:
        r3.close();	 Catch:{ IOException -> 0x01f1 }
    L_0x0115:
        throw r2;
    L_0x0116:
        r8 = 0;
        r5.write(r4, r8, r7);	 Catch:{ IOException -> 0x011e }
        r8 = (long) r7;
        r2 = r2 - r8;
        goto L_0x004b;
    L_0x011e:
        r2 = move-exception;
        r3 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Fail to read data from input stream, size:";
        r4 = r4.append(r5);
        r0 = r18;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x013a:
        r2 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00e9 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00e9 }
        r4 = new com.google.gson.Gson;	 Catch:{ IOException -> 0x00e9 }
        r4.<init>();	 Catch:{ IOException -> 0x00e9 }
        r8 = com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult.class;
        r2 = r4.fromJson(r2, r8);	 Catch:{ IOException -> 0x00e9 }
        r2 = (com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult) r2;	 Catch:{ IOException -> 0x00e9 }
        if (r2 == 0) goto L_0x015e;
    L_0x014e:
        r4 = r2.getEtag();	 Catch:{ IOException -> 0x00e9 }
        if (r4 == 0) goto L_0x015e;
    L_0x0154:
        r8 = r2.getPartSize();	 Catch:{ IOException -> 0x00e9 }
        r10 = 0;
        r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r4 != 0) goto L_0x018b;
    L_0x015e:
        r2 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;	 Catch:{ IOException -> 0x00e9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e9 }
        r4.<init>();	 Catch:{ IOException -> 0x00e9 }
        r8 = "Fail to parse the result of uploading part. bucket name:";
        r4 = r4.append(r8);	 Catch:{ IOException -> 0x00e9 }
        r4 = r4.append(r14);	 Catch:{ IOException -> 0x00e9 }
        r8 = ", object name:";
        r4 = r4.append(r8);	 Catch:{ IOException -> 0x00e9 }
        r4 = r4.append(r15);	 Catch:{ IOException -> 0x00e9 }
        r8 = ", upload ID:";
        r4 = r4.append(r8);	 Catch:{ IOException -> 0x00e9 }
        r4 = r4.append(r13);	 Catch:{ IOException -> 0x00e9 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00e9 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x00e9 }
        throw r2;	 Catch:{ IOException -> 0x00e9 }
    L_0x018b:
        if (r3 == 0) goto L_0x0190;
    L_0x018d:
        r3.close();	 Catch:{ IOException -> 0x01ed }
    L_0x0190:
        return r2;
    L_0x0191:
        r5 = TEST_MODE;	 Catch:{ all -> 0x010f }
        if (r5 != 0) goto L_0x01e1;
    L_0x0195:
        r5 = "GalaxyFDSClientImpl";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010f }
        r8.<init>();	 Catch:{ all -> 0x010f }
        r9 = "Retry the upload of object:";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r8 = r8.append(r15);	 Catch:{ all -> 0x010f }
        r9 = " bucket";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r9 = ":";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r8 = r8.append(r14);	 Catch:{ all -> 0x010f }
        r9 = " upload id:";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r8 = r8.append(r13);	 Catch:{ all -> 0x010f }
        r9 = " part number:";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r0 = r16;
        r8 = r8.append(r0);	 Catch:{ all -> 0x010f }
        r9 = " cause:";
        r8 = r8.append(r9);	 Catch:{ all -> 0x010f }
        r2 = com.xiaomi.infra.galaxy.fds.android.util.Util.getStackTrace(r2);	 Catch:{ all -> 0x010f }
        r2 = r8.append(r2);	 Catch:{ all -> 0x010f }
        r2 = r2.toString();	 Catch:{ all -> 0x010f }
        android.util.Log.i(r5, r2);	 Catch:{ all -> 0x010f }
    L_0x01e1:
        if (r3 == 0) goto L_0x01e6;
    L_0x01e3:
        r3.close();	 Catch:{ IOException -> 0x01ef }
    L_0x01e6:
        r5 = r4;
        r4 = r3;
        goto L_0x006b;
    L_0x01ea:
        r2 = move-exception;
        goto L_0x0068;
    L_0x01ed:
        r3 = move-exception;
        goto L_0x0190;
    L_0x01ef:
        r2 = move-exception;
        goto L_0x01e6;
    L_0x01f1:
        r3 = move-exception;
        goto L_0x0115;
    L_0x01f4:
        r2 = move-exception;
        r3 = r4;
        goto L_0x0110;
    L_0x01f8:
        r2 = move-exception;
        r3 = r4;
        goto L_0x0104;
    L_0x01fc:
        r2 = move-exception;
        r3 = r4;
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.uploadPart(java.lang.String, java.lang.String, java.lang.String, int, com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream, long):com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult");
    }

    public boolean doesObjectExist(String str, String str2) {
        Args.notNull(str, "bucket name");
        Args.notEmpty(str, "bucket name");
        Args.notNull(str2, "object name");
        Args.notEmpty(str2, "object name");
        String str3 = this.config.getBaseUri() + "/" + str + "/" + str2;
        try {
            HttpResponse execute = this.httpClient.execute(RequestFactory.createRequest(str3, this.config.getCredential(), HttpMethod.HEAD, null));
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == C2799f.f14282t) {
                return true;
            }
            if (statusCode == 404) {
                return false;
            }
            throw new GalaxyFDSClientException("Unable to head object[" + str + "/" + str2 + "] from URI :" + str3 + ". Cause:" + execute.getStatusLine().toString());
        } catch (Throwable e) {
            throw new GalaxyFDSClientException("Unable to head object[" + str + "/" + str2 + "] from URI :" + str3 + " Exception:" + e.getMessage(), e);
        }
    }

    public FDSObject getObject(String str, long j, List<UserParam> list, ProgressListener progressListener) {
        InputStream content;
        Throwable e;
        InputStream inputStream;
        GalaxyFDSClientException galaxyFDSClientException;
        GalaxyFDSClientException galaxyFDSClientException2 = null;
        Args.notNull(str, "URI");
        Args.notNegative(j, "offset in content");
        if (list != null) {
            StringBuilder stringBuilder = new StringBuilder(str);
            for (UserParam userParam : list) {
                if (stringBuilder.indexOf("?") == -1) {
                    stringBuilder.append('?');
                } else {
                    stringBuilder.append('&');
                }
                stringBuilder.append(userParam.toString());
            }
            str = stringBuilder.toString();
        }
        try {
            String path = new URI(str).getPath();
            int indexOf = path.indexOf(47, 1);
            if (indexOf == -1) {
                throw new URISyntaxException(str, "not a valid object URI");
            }
            String substring = path.substring(0, indexOf);
            String substring2 = path.substring(indexOf + 1);
            try {
                Map hashMap = new HashMap();
                if (j > 0 && !isGetThumbnail(list)) {
                    hashMap.put(C3004e.f15003O, "bytes=" + j + "-");
                }
                HttpResponse execute = this.httpClient.execute(RequestFactory.createRequest(str, this.config.getCredential(), HttpMethod.GET, hashMap));
                content = execute.getEntity().getContent();
                try {
                    int statusCode = execute.getStatusLine().getStatusCode();
                    if (statusCode == C2799f.f14282t || statusCode == 206) {
                        FDSObject fDSObject = new FDSObject(substring, substring2);
                        ObjectMetadata parseObjectMetadata = ObjectMetadata.parseObjectMetadata(execute.getAllHeaders());
                        fDSObject.setObjectContent(new ObjectInputStream(content, parseObjectMetadata, progressListener));
                        fDSObject.setObjectMetadata(parseObjectMetadata);
                        if (!(null == null || content == null)) {
                            try {
                                content.close();
                            } catch (IOException e2) {
                            }
                        }
                        return fDSObject;
                    }
                    GalaxyFDSClientException galaxyFDSClientException3 = new GalaxyFDSClientException("Unable to get object[" + substring + "/" + substring2 + "] from URI :" + str + ". Cause:" + execute.getStatusLine().toString());
                    try {
                        throw galaxyFDSClientException3;
                    } catch (IOException e3) {
                        e = e3;
                        galaxyFDSClientException2 = galaxyFDSClientException3;
                        inputStream = content;
                    } catch (Throwable th) {
                        e = th;
                        galaxyFDSClientException2 = galaxyFDSClientException3;
                        if (!(galaxyFDSClientException2 == null || content == null)) {
                            try {
                                content.close();
                            } catch (IOException e4) {
                            }
                        }
                        throw e;
                    }
                } catch (IOException e5) {
                    e = e5;
                    inputStream = content;
                    try {
                        galaxyFDSClientException = new GalaxyFDSClientException("Unable to get object[" + substring + "/" + substring2 + "] from URI :" + str + " Exception:" + e.getMessage(), e);
                        try {
                            throw galaxyFDSClientException;
                        } catch (Throwable th2) {
                            e = th2;
                            galaxyFDSClientException2 = galaxyFDSClientException;
                            content = inputStream;
                        }
                    } catch (Throwable th3) {
                        e = th3;
                        content = inputStream;
                        content.close();
                        throw e;
                    }
                } catch (Throwable th4) {
                    e = th4;
                    content.close();
                    throw e;
                }
            } catch (IOException e6) {
                e = e6;
                inputStream = null;
                galaxyFDSClientException = new GalaxyFDSClientException("Unable to get object[" + substring + "/" + substring2 + "] from URI :" + str + " Exception:" + e.getMessage(), e);
                throw galaxyFDSClientException;
            } catch (Throwable th5) {
                e = th5;
                content = null;
                content.close();
                throw e;
            }
        } catch (Throwable e7) {
            throw new GalaxyFDSClientException("Invalid URI, can't parse bucket nameand object name form it:" + str, e7);
        }
    }

    public FDSObject getObject(String str, String str2) {
        return getObject(str, str2, 0, null);
    }

    public FDSObject getObject(String str, String str2, long j, List<UserParam> list) {
        return getObject(str, str2, j, (List) list, null);
    }

    public FDSObject getObject(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener) {
        Args.notNull(str, "bucket name");
        Args.notEmpty(str, "bucket name");
        Args.notNull(str2, "object name");
        Args.notEmpty(str2, "object name");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.config.getDownloadBaseUri());
        stringBuilder.append("/" + str + "/" + str2);
        return getObject(stringBuilder.toString(), j, (List) list, progressListener);
    }

    @Deprecated
    public FDSObject getObject(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener, boolean z) {
        return getObject(str, str2, j, (List) list, progressListener);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata getObject(java.lang.String r11, java.io.File r12, java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam> r13, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener r14) {
        /*
        r10 = this;
        r7 = 0;
        r0 = "Destination file";
        com.xiaomi.infra.galaxy.fds.android.util.Args.notNull(r12, r0);
        r9 = r10.isGetThumbnail(r13);
        r8 = r7;
    L_0x000b:
        if (r8 == 0) goto L_0x0027;
    L_0x000d:
        if (r9 != 0) goto L_0x0027;
    L_0x000f:
        r0 = 1;
        r6 = r0;
    L_0x0011:
        if (r6 == 0) goto L_0x0029;
    L_0x0013:
        r2 = r12.length();	 Catch:{ GalaxyFDSClientException -> 0x002c }
    L_0x0017:
        r0 = r10;
        r1 = r11;
        r4 = r13;
        r5 = r14;
        r0 = r0.getObject(r1, r2, r4, r5);	 Catch:{ GalaxyFDSClientException -> 0x002c }
        com.xiaomi.infra.galaxy.fds.android.util.Util.downloadObjectToFile(r0, r12, r6);	 Catch:{ GalaxyFDSClientException -> 0x002c }
        r0 = r0.getObjectMetadata();	 Catch:{ GalaxyFDSClientException -> 0x002c }
        return r0;
    L_0x0027:
        r6 = r7;
        goto L_0x0011;
    L_0x0029:
        r2 = 0;
        goto L_0x0017;
    L_0x002c:
        r0 = move-exception;
        r1 = r8 + 1;
        r2 = r10.config;
        r2 = r2.getMaxRetryTimes();
        if (r1 < r2) goto L_0x0038;
    L_0x0037:
        throw r0;
    L_0x0038:
        r2 = TEST_MODE;
        if (r2 != 0) goto L_0x0070;
    L_0x003c:
        r2 = "GalaxyFDSClientImpl";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Retry the download of object:";
        r3 = r3.append(r4);
        r3 = r3.append(r11);
        r4 = " to file: ";
        r3 = r3.append(r4);
        r4 = r12.getAbsolutePath();
        r3 = r3.append(r4);
        r4 = " cause:";
        r3 = r3.append(r4);
        r0 = com.xiaomi.infra.galaxy.fds.android.util.Util.getStackTrace(r0);
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.i(r2, r0);
    L_0x0070:
        r8 = r1;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.getObject(java.lang.String, java.io.File, java.util.List, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener):com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata");
    }

    public ObjectMetadata getObject(String str, String str2, File file) {
        return getObject(str, str2, file, null);
    }

    public ObjectMetadata getObject(String str, String str2, File file, List<UserParam> list) {
        return getObject(str, str2, file, (List) list, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata getObject(java.lang.String r11, java.lang.String r12, java.io.File r13, java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam> r14, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener r15) {
        /*
        r10 = this;
        r0 = "Destination file";
        com.xiaomi.infra.galaxy.fds.android.util.Args.notNull(r13, r0);
        r0 = 0;
        r9 = r10.isGetThumbnail(r14);
        r8 = r0;
    L_0x000b:
        if (r8 == 0) goto L_0x0027;
    L_0x000d:
        if (r9 != 0) goto L_0x0027;
    L_0x000f:
        r0 = 1;
    L_0x0010:
        if (r0 == 0) goto L_0x0029;
    L_0x0012:
        r4 = r13.length();	 Catch:{ GalaxyFDSClientException -> 0x002c }
    L_0x0016:
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r6 = r14;
        r7 = r15;
        r1 = r1.getObject(r2, r3, r4, r6, r7);	 Catch:{ GalaxyFDSClientException -> 0x002c }
        com.xiaomi.infra.galaxy.fds.android.util.Util.downloadObjectToFile(r1, r13, r0);	 Catch:{ GalaxyFDSClientException -> 0x002c }
        r0 = r1.getObjectMetadata();	 Catch:{ GalaxyFDSClientException -> 0x002c }
        return r0;
    L_0x0027:
        r0 = 0;
        goto L_0x0010;
    L_0x0029:
        r4 = 0;
        goto L_0x0016;
    L_0x002c:
        r0 = move-exception;
        r1 = r8 + 1;
        r2 = r10.config;
        r2 = r2.getMaxRetryTimes();
        if (r1 < r2) goto L_0x0038;
    L_0x0037:
        throw r0;
    L_0x0038:
        r2 = TEST_MODE;
        if (r2 != 0) goto L_0x0080;
    L_0x003c:
        r2 = "GalaxyFDSClientImpl";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Retry the download of object:";
        r3 = r3.append(r4);
        r3 = r3.append(r12);
        r4 = " bucket";
        r3 = r3.append(r4);
        r4 = ":";
        r3 = r3.append(r4);
        r3 = r3.append(r11);
        r4 = " to file: ";
        r3 = r3.append(r4);
        r4 = r13.getAbsolutePath();
        r3 = r3.append(r4);
        r4 = " cause:";
        r3 = r3.append(r4);
        r0 = com.xiaomi.infra.galaxy.fds.android.util.Util.getStackTrace(r0);
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.i(r2, r0);
    L_0x0080:
        r8 = r1;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.getObject(java.lang.String, java.lang.String, java.io.File, java.util.List, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener):com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata");
    }

    @Deprecated
    public ObjectMetadata getObject(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener, boolean z) {
        return getObject(str, str2, file, (List) list, progressListener);
    }

    public PutObjectResult putObject(String str, File file) {
        return putObject(str, file, null);
    }

    public PutObjectResult putObject(String str, File file, List<UserParam> list) {
        return putObject(str, file, (List) list, null);
    }

    public PutObjectResult putObject(String str, File file, List<UserParam> list, ProgressListener progressListener) {
        return putObject(str, null, file, (List) list, progressListener);
    }

    public PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata) {
        return putObject(str, inputStream, objectMetadata, null);
    }

    public PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) {
        return putObject(str, inputStream, objectMetadata, (List) list, null);
    }

    public PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener) {
        return putObject(str, null, inputStream, objectMetadata, list, progressListener);
    }

    public PutObjectResult putObject(String str, String str2, File file) {
        return putObject(str, str2, file, null);
    }

    public PutObjectResult putObject(String str, String str2, File file, List<UserParam> list) {
        return putObject(str, str2, file, (List) list, null);
    }

    public PutObjectResult putObject(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener) {
        Args.notNull(file, UriUtil.LOCAL_FILE_SCHEME);
        try {
            InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.length());
            objectMetadata.setContentType(Util.getMimeType(file));
            objectMetadata.setLastModified(new Date(file.lastModified()));
            return putObject(str, str2, bufferedInputStream, objectMetadata, list, progressListener);
        } catch (Throwable e) {
            throw new GalaxyFDSClientException("Unable to find the file to be uploaded:" + file.getAbsolutePath(), e);
        }
    }

    public PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        return putObject(str, str2, inputStream, objectMetadata, null);
    }

    public PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) {
        return putObject(str, str2, inputStream, objectMetadata, list, null);
    }

    public PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener) {
        GalaxyFDSClientException e;
        Args.notNull(str, "bucket name");
        Args.notEmpty(str, "bucket name");
        Args.notNull(inputStream, "input stream");
        Args.notNull(objectMetadata, "metadata");
        long contentLength = objectMetadata.getContentLength();
        Args.notNegative(contentLength, "content length");
        if (objectMetadata.getContentType() == null) {
            objectMetadata.setContentType(Consts.APPLICATION_OCTET_STREAM);
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream, objectMetadata, progressListener);
        String objectName;
        try {
            InitMultipartUploadResult initMultipartUpload = initMultipartUpload(str, str2, objectMetadata.getContentLength());
            objectName = initMultipartUpload.getObjectName();
            try {
                String uploadId = initMultipartUpload.getUploadId();
                int uploadPartSize = this.config.getUploadPartSize();
                int i = ((int) ((((long) uploadPartSize) + contentLength) - 1)) / uploadPartSize;
                List arrayList = new ArrayList(i);
                int i2 = 1;
                long j = contentLength;
                while (i2 <= i) {
                    contentLength = Math.min((long) uploadPartSize, j);
                    arrayList.add(uploadPart(uploadId, str, objectName, i2, objectInputStream, contentLength));
                    i2++;
                    j -= contentLength;
                }
                UploadPartResultList uploadPartResultList = new UploadPartResultList();
                uploadPartResultList.setUploadPartResultList(arrayList);
                PutObjectResult completeMultipartUpload = completeMultipartUpload(uploadId, str, objectName, objectMetadata, uploadPartResultList, list);
                try {
                    objectInputStream.close();
                } catch (IOException e2) {
                }
                return completeMultipartUpload;
            } catch (GalaxyFDSClientException e3) {
                e = e3;
                if (null != null) {
                    try {
                        abortMultipartUpload(str, objectName, null);
                    } catch (Throwable th) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                }
                throw e;
            }
        } catch (GalaxyFDSClientException e5) {
            e = e5;
            objectName = str2;
            if (null != null) {
                abortMultipartUpload(str, objectName, null);
            }
            throw e;
        }
    }
}
