package com.google.zxing.client.result;

public final class WifiParsedResult extends ParsedResult {
    private final String networkEncryption;
    private final String password;
    private final String ssid;

    public WifiParsedResult(String str, String str2, String str3) {
        super(ParsedResultType.WIFI);
        this.ssid = str2;
        this.networkEncryption = str;
        this.password = str3;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(80);
        ParsedResult.maybeAppend(this.ssid, stringBuffer);
        ParsedResult.maybeAppend(this.networkEncryption, stringBuffer);
        ParsedResult.maybeAppend(this.password, stringBuffer);
        return stringBuffer.toString();
    }

    public String getNetworkEncryption() {
        return this.networkEncryption;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSsid() {
        return this.ssid;
    }
}
