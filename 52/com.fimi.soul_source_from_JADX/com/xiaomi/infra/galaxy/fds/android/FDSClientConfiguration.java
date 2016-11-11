package com.xiaomi.infra.galaxy.fds.android;

import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.util.Args;
import org.p122a.p123a.C2915a;

public class FDSClientConfiguration {
    public static final int DEFAULT_CONNECTION_TIMEOUT_MS = 50000;
    public static final int DEFAULT_MAX_RETRY_TIMES = 3;
    public static final int DEFAULT_SOCKET_TIMEOUT_MS = 50000;
    public static final int DEFAULT_UPLOAD_PART_SIZE = 4096;
    private static final String URI_CDN = "cdn";
    private static final String URI_FDS_SSL_SUFFIX = ".fds-ssl.api.xiaomi.com";
    private static final String URI_FDS_SUFFIX = ".fds.api.xiaomi.com";
    private static final String URI_FILES = "files";
    private static final String URI_HTTPS_PREFIX = "https://";
    private static final String URI_HTTP_PREFIX = "http://";
    private String baseUriForUnitTest;
    private int connectionTimeoutMs;
    private GalaxyFDSCredential credential;
    private boolean enableCdnForDownload;
    private boolean enableCdnForUpload;
    private boolean enableHttps;
    private boolean enableUnitTestMode;
    private int maxRetryTimes;
    private String regionName;
    private int socketReceiveBufferSizeHint;
    private int socketSendBufferSizeHint;
    private int socketTimeoutMs;
    private int uploadPartSize;

    public FDSClientConfiguration() {
        this.socketTimeoutMs = DEFAULT_SOCKET_TIMEOUT_MS;
        this.connectionTimeoutMs = DEFAULT_SOCKET_TIMEOUT_MS;
        this.socketSendBufferSizeHint = 0;
        this.socketReceiveBufferSizeHint = 0;
        this.maxRetryTimes = DEFAULT_MAX_RETRY_TIMES;
        this.uploadPartSize = DEFAULT_UPLOAD_PART_SIZE;
        this.regionName = C2915a.f14760f;
        this.enableHttps = true;
        this.enableCdnForUpload = false;
        this.enableCdnForDownload = true;
        this.enableUnitTestMode = false;
        this.baseUriForUnitTest = C2915a.f14760f;
    }

    private String getBaseUriPrefix(boolean z, String str) {
        return str.isEmpty() ? z ? URI_CDN : URI_FILES : z ? str + "-" + URI_CDN : str + "-" + URI_FILES;
    }

    private String getBaseUriSuffix(boolean z, boolean z2) {
        return (z && z2) ? URI_FDS_SSL_SUFFIX : URI_FDS_SUFFIX;
    }

    String buildBaseUri(boolean z) {
        if (this.enableUnitTestMode) {
            return this.baseUriForUnitTest;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.enableHttps ? URI_HTTPS_PREFIX : URI_HTTP_PREFIX);
        stringBuilder.append(getBaseUriPrefix(z, this.regionName));
        stringBuilder.append(getBaseUriSuffix(z, this.enableHttps));
        return stringBuilder.toString();
    }

    public void enableCdnForDownload(boolean z) {
        this.enableCdnForDownload = z;
    }

    public void enableCdnForUpload(boolean z) {
        this.enableCdnForUpload = z;
    }

    public void enableHttps(boolean z) {
        this.enableHttps = z;
    }

    void enableUnitTestMode(boolean z) {
        this.enableUnitTestMode = z;
    }

    String getBaseUri() {
        return buildBaseUri(false);
    }

    String getBaseUriForUnitTest() {
        return this.baseUriForUnitTest;
    }

    String getCdnBaseUri() {
        return buildBaseUri(true);
    }

    @Deprecated
    public String getCdnServiceBaseUri() {
        return getCdnBaseUri();
    }

    public int getConnectionTimeoutMs() {
        return this.connectionTimeoutMs;
    }

    public GalaxyFDSCredential getCredential() {
        return this.credential;
    }

    String getDownloadBaseUri() {
        return buildBaseUri(this.enableCdnForDownload);
    }

    @Deprecated
    public String getFdsServiceBaseUri() {
        return getBaseUri();
    }

    public int getMaxRetryTimes() {
        return this.maxRetryTimes;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public int[] getSocketBufferSizeHints() {
        return new int[]{this.socketSendBufferSizeHint, this.socketReceiveBufferSizeHint};
    }

    public int getSocketTimeoutMs() {
        return this.socketTimeoutMs;
    }

    String getUploadBaseUri() {
        return buildBaseUri(this.enableCdnForUpload);
    }

    public int getUploadPartSize() {
        return this.uploadPartSize;
    }

    public boolean isCdnEnabledForDownload() {
        return this.enableCdnForDownload;
    }

    public boolean isCdnEnabledForUpload() {
        return this.enableCdnForUpload;
    }

    boolean isEnabledUnitTestMode() {
        return this.enableUnitTestMode;
    }

    public boolean isHttpsEnabled() {
        return this.enableHttps;
    }

    void setBaseUriForUnitTest(String str) {
        this.baseUriForUnitTest = str;
    }

    @Deprecated
    public void setCdnServiceBaseUri(String str) {
    }

    public void setConnectionTimeoutMs(int i) {
        this.connectionTimeoutMs = i;
    }

    public void setCredential(GalaxyFDSCredential galaxyFDSCredential) {
        Args.notNull(galaxyFDSCredential, "credential");
        this.credential = galaxyFDSCredential;
    }

    @Deprecated
    public void setFdsServiceBaseUri(String str) {
    }

    public void setMaxRetryTimes(int i) {
        Args.notNegative(i, "max retry times");
        this.maxRetryTimes = i;
    }

    public void setRegionName(String str) {
        this.regionName = str;
    }

    public void setSocketBufferSizeHints(int i, int i2) {
        this.socketSendBufferSizeHint = i;
        this.socketReceiveBufferSizeHint = i2;
    }

    public void setSocketTimeoutMs(int i) {
        this.socketTimeoutMs = i;
    }

    public void setUploadPartSize(int i) {
        Args.positive(i, "upload part size");
        this.uploadPartSize = i;
    }

    FDSClientConfiguration withBaseUriForUnitTest(String str) {
        setBaseUriForUnitTest(str);
        return this;
    }

    public FDSClientConfiguration withCdnForDownload(boolean z) {
        enableCdnForDownload(z);
        return this;
    }

    public FDSClientConfiguration withCdnForUpload(boolean z) {
        enableCdnForUpload(z);
        return this;
    }

    @Deprecated
    public FDSClientConfiguration withCdnServiceBaseUri(String str) {
        return this;
    }

    public FDSClientConfiguration withConnectionTimeoutMs(int i) {
        setConnectionTimeoutMs(i);
        return this;
    }

    public FDSClientConfiguration withCredential(GalaxyFDSCredential galaxyFDSCredential) {
        setCredential(galaxyFDSCredential);
        return this;
    }

    @Deprecated
    public FDSClientConfiguration withFdsServiceBaseUri(String str) {
        return this;
    }

    public FDSClientConfiguration withHttps(boolean z) {
        enableHttps(z);
        return this;
    }

    public FDSClientConfiguration withMaxRetryTimes(int i) {
        setMaxRetryTimes(i);
        return this;
    }

    public FDSClientConfiguration withRegionName(String str) {
        setRegionName(str);
        return this;
    }

    public FDSClientConfiguration withSocketBufferSizeHints(int i, int i2) {
        setSocketBufferSizeHints(i, i2);
        return this;
    }

    public FDSClientConfiguration withSocketTimeoutMs(int i) {
        setSocketTimeoutMs(i);
        return this;
    }

    FDSClientConfiguration withUnitTestMode(boolean z) {
        enableUnitTestMode(z);
        return this;
    }

    public FDSClientConfiguration withUploadPartSize(int i) {
        setUploadPartSize(i);
        return this;
    }
}
