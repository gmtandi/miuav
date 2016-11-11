package com.baidu.tts.loopj;

import android.content.Context;
import android.os.Looper;
import android.support.v4.view.MotionEventCompat;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p159n.C2967c;

public class AsyncHttpClient {
    public static final int DEFAULT_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_MAX_RETRIES = 5;
    public static final int DEFAULT_RETRY_SLEEP_TIME_MILLIS = 1500;
    public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    public static final int DEFAULT_SOCKET_TIMEOUT = 10000;
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String LOG_TAG = "AsyncHttpClient";
    public static LogInterface log;
    private final Map<String, String> clientHeaderMap;
    private int connectTimeout;
    private final DefaultHttpClient httpClient;
    private final HttpContext httpContext;
    private boolean isUrlEncodingEnabled;
    private int maxConnections;
    private final Map<Context, List<RequestHandle>> requestMap;
    private int responseTimeout;
    private ExecutorService threadPool;

    /* renamed from: com.baidu.tts.loopj.AsyncHttpClient.1 */
    class C08321 implements HttpRequestInterceptor {
        C08321() {
        }

        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            if (!httpRequest.containsHeader(AsyncHttpClient.HEADER_ACCEPT_ENCODING)) {
                httpRequest.addHeader(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
            }
            for (String str : AsyncHttpClient.this.clientHeaderMap.keySet()) {
                if (httpRequest.containsHeader(str)) {
                    Header firstHeader = httpRequest.getFirstHeader(str);
                    AsyncHttpClient.log.m6888d(AsyncHttpClient.LOG_TAG, String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{str, AsyncHttpClient.this.clientHeaderMap.get(str), firstHeader.getName(), firstHeader.getValue()}));
                    httpRequest.removeHeader(firstHeader);
                }
                httpRequest.addHeader(str, (String) AsyncHttpClient.this.clientHeaderMap.get(str));
            }
        }
    }

    /* renamed from: com.baidu.tts.loopj.AsyncHttpClient.2 */
    class C08332 implements HttpResponseInterceptor {
        C08332() {
        }

        public void process(HttpResponse httpResponse, HttpContext httpContext) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                Header contentEncoding = entity.getContentEncoding();
                if (contentEncoding != null) {
                    for (HeaderElement name : contentEncoding.getElements()) {
                        if (name.getName().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                            httpResponse.setEntity(new InflatingEntity(entity));
                            return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.baidu.tts.loopj.AsyncHttpClient.3 */
    class C08343 implements HttpRequestInterceptor {
        C08343() {
        }

        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            AuthState authState = (AuthState) httpContext.getAttribute(C2968a.f14895i);
            CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.getAttribute(C2968a.f14893g);
            HttpHost httpHost = (HttpHost) httpContext.getAttribute(C2967c.f14884q);
            if (authState.getAuthScheme() == null) {
                Credentials credentials = credentialsProvider.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()));
                if (credentials != null) {
                    authState.setAuthScheme(new BasicScheme());
                    authState.setCredentials(credentials);
                }
            }
        }
    }

    /* renamed from: com.baidu.tts.loopj.AsyncHttpClient.4 */
    class C08354 implements Runnable {
        final /* synthetic */ boolean val$mayInterruptIfRunning;
        final /* synthetic */ List val$requestList;

        C08354(List list, boolean z) {
            this.val$requestList = list;
            this.val$mayInterruptIfRunning = z;
        }

        public void run() {
            AsyncHttpClient.this.cancelRequests(this.val$requestList, this.val$mayInterruptIfRunning);
        }
    }

    class InflatingEntity extends HttpEntityWrapper {
        GZIPInputStream gzippedStream;
        PushbackInputStream pushbackStream;
        InputStream wrappedStream;

        public InflatingEntity(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public void consumeContent() {
            AsyncHttpClient.silentCloseInputStream(this.wrappedStream);
            AsyncHttpClient.silentCloseInputStream(this.pushbackStream);
            AsyncHttpClient.silentCloseInputStream(this.gzippedStream);
            super.consumeContent();
        }

        public InputStream getContent() {
            this.wrappedStream = this.wrappedEntity.getContent();
            this.pushbackStream = new PushbackInputStream(this.wrappedStream, 2);
            if (!AsyncHttpClient.isInputStreamGZIPCompressed(this.pushbackStream)) {
                return this.pushbackStream;
            }
            this.gzippedStream = new GZIPInputStream(this.pushbackStream);
            return this.gzippedStream;
        }

        public long getContentLength() {
            return this.wrappedEntity == null ? 0 : this.wrappedEntity.getContentLength();
        }
    }

    static {
        log = new LogHandler();
    }

    public AsyncHttpClient() {
        this(false, 80, 443);
    }

    public AsyncHttpClient(int i) {
        this(false, i, 443);
    }

    public AsyncHttpClient(int i, int i2) {
        this(false, i, i2);
    }

    public AsyncHttpClient(SchemeRegistry schemeRegistry) {
        boolean z = true;
        this.maxConnections = DEFAULT_MAX_CONNECTIONS;
        this.connectTimeout = DEFAULT_SOCKET_TIMEOUT;
        this.responseTimeout = DEFAULT_SOCKET_TIMEOUT;
        this.isUrlEncodingEnabled = true;
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) this.connectTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(this.maxConnections));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, DEFAULT_MAX_CONNECTIONS);
        HttpConnectionParams.setSoTimeout(basicHttpParams, this.responseTimeout);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.connectTimeout);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, DEFAULT_SOCKET_BUFFER_SIZE);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        ClientConnectionManager createConnectionManager = createConnectionManager(schemeRegistry, basicHttpParams);
        if (createConnectionManager == null) {
            z = false;
        }
        Utils.asserts(z, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.threadPool = getDefaultThreadPool();
        this.requestMap = Collections.synchronizedMap(new WeakHashMap());
        this.clientHeaderMap = new HashMap();
        this.httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        this.httpClient = new DefaultHttpClient(createConnectionManager, basicHttpParams);
        this.httpClient.addRequestInterceptor(new C08321());
        this.httpClient.addResponseInterceptor(new C08332());
        this.httpClient.addRequestInterceptor(new C08343(), 0);
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(DEFAULT_MAX_RETRIES, DEFAULT_RETRY_SLEEP_TIME_MILLIS));
    }

    public AsyncHttpClient(boolean z, int i, int i2) {
        this(getDefaultSchemeRegistry(z, i, i2));
    }

    private HttpEntityEnclosingRequestBase addEntityToRequestBase(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }

    public static void allowRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToWhitelist(cls);
        }
    }

    public static void blockRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToBlacklist(cls);
        }
    }

    private void cancelRequests(List<RequestHandle> list, boolean z) {
        if (list != null) {
            for (RequestHandle cancel : list) {
                cancel.cancel(z);
            }
        }
    }

    public static void endEntityViaReflection(HttpEntity httpEntity) {
        if (httpEntity instanceof HttpEntityWrapper) {
            try {
                for (Field field : HttpEntityWrapper.class.getDeclaredFields()) {
                    if (field.getName().equals("wrappedEntity")) {
                        break;
                    }
                }
                Field field2 = null;
                if (field2 != null) {
                    field2.setAccessible(true);
                    HttpEntity httpEntity2 = (HttpEntity) field2.get(httpEntity);
                    if (httpEntity2 != null) {
                        httpEntity2.consumeContent();
                    }
                }
            } catch (Throwable th) {
                log.m6891e(LOG_TAG, "wrappedEntity consume", th);
            }
        }
    }

    private static SchemeRegistry getDefaultSchemeRegistry(boolean z, int i, int i2) {
        if (z) {
            log.m6888d(LOG_TAG, "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            log.m6888d(LOG_TAG, "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = 443;
            log.m6888d(LOG_TAG, "Invalid HTTPS port number specified, defaulting to 443");
        }
        SocketFactory fixedSocketFactory = z ? MySSLSocketFactory.getFixedSocketFactory() : SSLSocketFactory.getSocketFactory();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), i));
        schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, fixedSocketFactory, i2));
        return schemeRegistry;
    }

    public static String getUrlWithQueryString(boolean z, String str, RequestParams requestParams) {
        if (str == null) {
            return null;
        }
        String toASCIIString;
        String trim;
        if (z) {
            try {
                URL url = new URL(URLDecoder.decode(str, C1142e.f5201a));
                toASCIIString = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
            } catch (Throwable e) {
                log.m6891e(LOG_TAG, "getUrlWithQueryString encoding URL", e);
            }
            if (requestParams != null) {
                return toASCIIString;
            }
            trim = requestParams.getParamString().trim();
            if (trim.equals(C2915a.f14760f) && !trim.equals("?")) {
                return (toASCIIString + (toASCIIString.contains("?") ? "&" : "?")) + trim;
            }
        }
        toASCIIString = str;
        if (requestParams != null) {
            return toASCIIString;
        }
        trim = requestParams.getParamString().trim();
        return trim.equals(C2915a.f14760f) ? toASCIIString : toASCIIString;
    }

    public static boolean isInputStreamGZIPCompressed(PushbackInputStream pushbackInputStream) {
        boolean z = true;
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int read = pushbackInputStream.read(bArr);
        pushbackInputStream.unread(bArr);
        int i = ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[0] & Util.MASK_8BIT);
        if (!(read == 2 && 35615 == i)) {
            z = false;
        }
        return z;
    }

    private HttpEntity paramsToEntity(RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpEntity httpEntity = null;
        if (requestParams != null) {
            try {
                httpEntity = requestParams.getEntity(responseHandlerInterface);
            } catch (Throwable e) {
                if (responseHandlerInterface != null) {
                    responseHandlerInterface.sendFailureMessage(0, httpEntity, httpEntity, e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        return httpEntity;
    }

    public static void silentCloseInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                log.m6897w(LOG_TAG, "Cannot close input stream", e);
            }
        }
    }

    public static void silentCloseOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable e) {
                log.m6897w(LOG_TAG, "Cannot close output stream", e);
            }
        }
    }

    public void addHeader(String str, String str2) {
        this.clientHeaderMap.put(str, str2);
    }

    public void cancelAllRequests(boolean z) {
        for (List<RequestHandle> list : this.requestMap.values()) {
            if (list != null) {
                for (RequestHandle cancel : list) {
                    cancel.cancel(z);
                }
            }
        }
        this.requestMap.clear();
    }

    public void cancelRequests(Context context, boolean z) {
        if (context == null) {
            log.m6890e(LOG_TAG, "Passed null Context to cancelRequests");
            return;
        }
        List list = (List) this.requestMap.get(context);
        this.requestMap.remove(context);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.threadPool.submit(new C08354(list, z));
            return;
        }
        cancelRequests(list, z);
    }

    public void cancelRequestsByTAG(Object obj, boolean z) {
        if (obj == null) {
            log.m6888d(LOG_TAG, "cancelRequestsByTAG, passed TAG is null, cannot proceed");
            return;
        }
        for (List<RequestHandle> list : this.requestMap.values()) {
            if (list != null) {
                for (RequestHandle requestHandle : list) {
                    if (obj.equals(requestHandle.getTag())) {
                        requestHandle.cancel(z);
                    }
                }
            }
        }
    }

    public void clearCredentialsProvider() {
        this.httpClient.getCredentialsProvider().clear();
    }

    protected ClientConnectionManager createConnectionManager(SchemeRegistry schemeRegistry, BasicHttpParams basicHttpParams) {
        return new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
    }

    public RequestHandle delete(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpDelete(getURI(str)), null, responseHandlerInterface, context);
    }

    public RequestHandle delete(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpDelete(URI.create(str).normalize()), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle delete(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpDelete = new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpDelete, null, responseHandlerInterface, context);
    }

    public RequestHandle delete(Context context, String str, Header[] headerArr, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpDelete = new HttpDelete(getURI(str));
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpDelete, null, responseHandlerInterface, context);
    }

    public RequestHandle delete(String str, ResponseHandlerInterface responseHandlerInterface) {
        return delete(null, str, responseHandlerInterface);
    }

    public void delete(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        sendRequest(this.httpClient, this.httpContext, new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), null, asyncHttpResponseHandler, null);
    }

    public RequestHandle get(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), null, responseHandlerInterface, context);
    }

    public RequestHandle get(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return get(context, str, null, responseHandlerInterface);
    }

    public RequestHandle get(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpGet(URI.create(str).normalize()), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle get(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpGet = new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpGet.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpGet, null, responseHandlerInterface, context);
    }

    public RequestHandle get(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return get(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle get(String str, ResponseHandlerInterface responseHandlerInterface) {
        return get(null, str, null, responseHandlerInterface);
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    protected ExecutorService getDefaultThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public HttpContext getHttpContext() {
        return this.httpContext;
    }

    public LogInterface getLogInterface() {
        return log;
    }

    public int getLoggingLevel() {
        return log.getLoggingLevel();
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public int getResponseTimeout() {
        return this.responseTimeout;
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    protected URI getURI(String str) {
        return URI.create(str).normalize();
    }

    public RequestHandle head(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), null, responseHandlerInterface, context);
    }

    public RequestHandle head(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return head(context, str, null, responseHandlerInterface);
    }

    public RequestHandle head(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpHead = new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpHead.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpHead, null, responseHandlerInterface, context);
    }

    public RequestHandle head(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return head(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle head(String str, ResponseHandlerInterface responseHandlerInterface) {
        return head(null, str, null, responseHandlerInterface);
    }

    public boolean isLoggingEnabled() {
        return log.isLoggingEnabled();
    }

    public boolean isUrlEncodingEnabled() {
        return this.isUrlEncodingEnabled;
    }

    protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        return new AsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, responseHandlerInterface);
    }

    public RequestHandle patch(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return patch(context, str, paramsToEntity(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle patch(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPatch(getURI(str)), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle patch(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(new HttpPatch(getURI(str)), httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, responseHandlerInterface, context);
    }

    public RequestHandle patch(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return patch(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle patch(String str, ResponseHandlerInterface responseHandlerInterface) {
        return patch(null, str, null, responseHandlerInterface);
    }

    public RequestHandle post(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return post(context, str, paramsToEntity(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle post(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPost(getURI(str)), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle post(Context context, String str, Header[] headerArr, RequestParams requestParams, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpPost = new HttpPost(getURI(str));
        if (requestParams != null) {
            httpPost.setEntity(paramsToEntity(requestParams, responseHandlerInterface));
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpPost, str2, responseHandlerInterface, context);
    }

    public RequestHandle post(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(new HttpPost(getURI(str)), httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, responseHandlerInterface, context);
    }

    public RequestHandle post(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return post(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle post(String str, ResponseHandlerInterface responseHandlerInterface) {
        return post(null, str, null, responseHandlerInterface);
    }

    public RequestHandle put(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return put(context, str, paramsToEntity(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle put(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPut(getURI(str)), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle put(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(new HttpPut(getURI(str)), httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, responseHandlerInterface, context);
    }

    public RequestHandle put(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return put(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle put(String str, ResponseHandlerInterface responseHandlerInterface) {
        return put(null, str, null, responseHandlerInterface);
    }

    public void removeAllHeaders() {
        this.clientHeaderMap.clear();
    }

    public void removeHeader(String str) {
        this.clientHeaderMap.remove(str);
    }

    protected RequestHandle sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (httpUriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (responseHandlerInterface == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!responseHandlerInterface.getUseSynchronousMode() || responseHandlerInterface.getUsePoolThread()) {
            if (str != null) {
                if ((httpUriRequest instanceof HttpEntityEnclosingRequestBase) && ((HttpEntityEnclosingRequestBase) httpUriRequest).getEntity() != null && httpUriRequest.containsHeader(HEADER_CONTENT_TYPE)) {
                    log.m6896w(LOG_TAG, "Passed contentType will be ignored because HttpEntity sets content type");
                } else {
                    httpUriRequest.setHeader(HEADER_CONTENT_TYPE, str);
                }
            }
            responseHandlerInterface.setRequestHeaders(httpUriRequest.getAllHeaders());
            responseHandlerInterface.setRequestURI(httpUriRequest.getURI());
            Object newAsyncHttpRequest = newAsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context);
            this.threadPool.submit(newAsyncHttpRequest);
            RequestHandle requestHandle = new RequestHandle(newAsyncHttpRequest);
            if (context != null) {
                List list;
                synchronized (this.requestMap) {
                    list = (List) this.requestMap.get(context);
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.requestMap.put(context, list);
                    }
                }
                list.add(requestHandle);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((RequestHandle) it.next()).shouldBeGarbageCollected()) {
                        it.remove();
                    }
                }
            }
            return requestHandle;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    public void setAuthenticationPreemptive(boolean z) {
        if (z) {
            this.httpClient.addRequestInterceptor(new PreemptiveAuthorizationHttpRequestInterceptor(), 0);
        } else {
            this.httpClient.removeRequestInterceptorByClass(PreemptiveAuthorizationHttpRequestInterceptor.class);
        }
    }

    public void setBasicAuth(String str, String str2) {
        setBasicAuth(str, str2, false);
    }

    public void setBasicAuth(String str, String str2, AuthScope authScope) {
        setBasicAuth(str, str2, authScope, false);
    }

    public void setBasicAuth(String str, String str2, AuthScope authScope, boolean z) {
        setCredentials(authScope, new UsernamePasswordCredentials(str, str2));
        setAuthenticationPreemptive(z);
    }

    public void setBasicAuth(String str, String str2, boolean z) {
        setBasicAuth(str, str2, null, z);
    }

    public void setConnectTimeout(int i) {
        if (i < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            i = DEFAULT_SOCKET_TIMEOUT;
        }
        this.connectTimeout = i;
        HttpParams params = this.httpClient.getParams();
        ConnManagerParams.setTimeout(params, (long) this.connectTimeout);
        HttpConnectionParams.setConnectionTimeout(params, this.connectTimeout);
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.httpContext.setAttribute(C2968a.f14892f, cookieStore);
    }

    public void setCredentials(AuthScope authScope, Credentials credentials) {
        if (credentials == null) {
            log.m6888d(LOG_TAG, "Provided credentials are null, not setting");
            return;
        }
        CredentialsProvider credentialsProvider = this.httpClient.getCredentialsProvider();
        if (authScope == null) {
            authScope = AuthScope.ANY;
        }
        credentialsProvider.setCredentials(authScope, credentials);
    }

    public void setEnableRedirects(boolean z) {
        setEnableRedirects(z, z, z);
    }

    public void setEnableRedirects(boolean z, boolean z2) {
        setEnableRedirects(z, z2, true);
    }

    public void setEnableRedirects(boolean z, boolean z2, boolean z3) {
        this.httpClient.getParams().setBooleanParameter("http.protocol.reject-relative-redirect", !z2);
        this.httpClient.getParams().setBooleanParameter("http.protocol.allow-circular-redirects", z3);
        this.httpClient.setRedirectHandler(new MyRedirectHandler(z));
    }

    public void setLogInterface(LogInterface logInterface) {
        if (logInterface != null) {
            log = logInterface;
        }
    }

    public void setLoggingEnabled(boolean z) {
        log.setLoggingEnabled(z);
    }

    public void setLoggingLevel(int i) {
        log.setLoggingLevel(i);
    }

    public void setMaxConnections(int i) {
        if (i < 1) {
            i = DEFAULT_MAX_CONNECTIONS;
        }
        this.maxConnections = i;
        ConnManagerParams.setMaxConnectionsPerRoute(this.httpClient.getParams(), new ConnPerRouteBean(this.maxConnections));
    }

    public void setMaxRetriesAndTimeout(int i, int i2) {
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(i, i2));
    }

    public void setProxy(String str, int i) {
        this.httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(str, i));
    }

    public void setProxy(String str, int i, String str2, String str3) {
        this.httpClient.getCredentialsProvider().setCredentials(new AuthScope(str, i), new UsernamePasswordCredentials(str2, str3));
        this.httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(str, i));
    }

    public void setRedirectHandler(RedirectHandler redirectHandler) {
        this.httpClient.setRedirectHandler(redirectHandler);
    }

    public void setResponseTimeout(int i) {
        if (i < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            i = DEFAULT_SOCKET_TIMEOUT;
        }
        this.responseTimeout = i;
        HttpConnectionParams.setSoTimeout(this.httpClient.getParams(), this.responseTimeout);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme(UriUtil.HTTPS_SCHEME, sSLSocketFactory, 443));
    }

    public void setThreadPool(ExecutorService executorService) {
        this.threadPool = executorService;
    }

    public void setTimeout(int i) {
        if (i < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            i = DEFAULT_SOCKET_TIMEOUT;
        }
        setConnectTimeout(i);
        setResponseTimeout(i);
    }

    public void setURLEncodingEnabled(boolean z) {
        this.isUrlEncodingEnabled = z;
    }

    public void setUserAgent(String str) {
        HttpProtocolParams.setUserAgent(this.httpClient.getParams(), str);
    }
}
