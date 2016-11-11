package com.xiaomi.account.openauth;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.auth.AuthConstants;

public class XiaomiOAuthResults {
    private final Bundle contentBundle;
    private final Error errorResult;
    private final Success successResult;

    public class Error {
        public final int errorCode;
        public final String errorMessage;

        public Error(int i, String str) {
            this.errorCode = i;
            this.errorMessage = str;
        }

        private static Error parseBundle(Bundle bundle) {
            return new Error(XiaomiOAuthResults.getIntCompatibly(bundle, AuthConstants.EXTRA_ERROR_CODE, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2), XiaomiOAuthResults.getStringCompatibly(bundle, AuthConstants.EXTRA_ERROR_DESCRIPTION, XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2));
        }

        public String toString() {
            return "errorCode=" + this.errorCode + ",errorMessage=" + this.errorMessage;
        }
    }

    class Success {
        public final String accessToken;
        public final String code;
        public final String expiresIn;
        public final String macAlgorithm;
        public final String macKey;
        public final String scopes;
        public final String state;
        public final String tokenType;

        public Success(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.accessToken = str;
            this.expiresIn = str2;
            this.scopes = str3;
            this.state = str4;
            this.tokenType = str5;
            this.macKey = str6;
            this.macAlgorithm = str7;
            this.code = str8;
        }

        private static Success parseBundle(Bundle bundle) {
            return new Success(XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, AuthConstants.EXTRA_ACCESS_TOKEN), XiaomiOAuthResults.getIntOrStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, AuthConstants.EXTRA_EXPIRES_IN), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_SCOPE_2, AuthConstants.EXTRA_SCOPE), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_STATE_2, AuthConstants.EXTRA_STATE), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_TOKEN_TYPE_2, AuthConstants.EXTRA_TOKEN_TYPE), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_MAC_KEY_2, AuthConstants.EXTRA_MAC_KEY), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_MAC_ALGORITHM_2, AuthConstants.EXTRA_MAC_ALGORITHM), XiaomiOAuthResults.getStringCompatibly(bundle, XiaomiOAuthConstants.EXTRA_CODE_2, XiaomiOAuthConstants.EXTRA_CODE));
        }

        public String toString() {
            return "accessToken=" + this.accessToken + ",expiresIn=" + this.expiresIn + ",scope=" + this.scopes + ",state=" + this.state + ",tokenType=" + this.tokenType + ",macKey=" + this.macKey + ",macAlogorithm=" + this.macAlgorithm + ",code=" + this.code;
        }
    }

    private XiaomiOAuthResults(Bundle bundle, Error error) {
        this.contentBundle = bundle;
        this.successResult = null;
        this.errorResult = error;
    }

    private XiaomiOAuthResults(Bundle bundle, Success success) {
        this.contentBundle = bundle;
        this.successResult = success;
        this.errorResult = null;
    }

    private static int getIntCompatibly(Bundle bundle, String str, String str2) {
        return bundle.containsKey(str) ? bundle.getInt(str) : bundle.getInt(str2);
    }

    private static String getIntOrStringCompatibly(Bundle bundle, String str, String str2) {
        for (String str3 : new String[]{str, str2}) {
            if (!TextUtils.isEmpty(str3) && bundle.containsKey(str3)) {
                Object obj = bundle.get(str3);
                if (obj != null) {
                    return obj instanceof Integer ? ((Integer) obj).toString() : obj.toString();
                }
            }
        }
        return null;
    }

    private static String getStringCompatibly(Bundle bundle, String str, String str2) {
        return bundle.containsKey(str) ? bundle.getString(str) : bundle.getString(str2);
    }

    public static XiaomiOAuthResults parseBundle(Bundle bundle) {
        return bundle == null ? null : getIntCompatibly(bundle, AuthConstants.EXTRA_ERROR_CODE, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2) != 0 ? new XiaomiOAuthResults(bundle, Error.parseBundle(bundle)) : new XiaomiOAuthResults(bundle, Success.parseBundle(bundle));
    }

    public String getAccessToken() {
        return this.successResult != null ? this.successResult.accessToken : null;
    }

    public String getCode() {
        return this.successResult != null ? this.successResult.code : null;
    }

    public Bundle getContentBundle() {
        return this.contentBundle;
    }

    public int getErrorCode() {
        return this.errorResult != null ? this.errorResult.errorCode : 0;
    }

    public String getErrorMessage() {
        return this.errorResult != null ? this.errorResult.errorMessage : null;
    }

    public String getExpiresIn() {
        return this.successResult != null ? this.successResult.expiresIn : null;
    }

    public String getMacAlgorithm() {
        return this.successResult != null ? this.successResult.macAlgorithm : null;
    }

    public String getMacKey() {
        return this.successResult != null ? this.successResult.macKey : null;
    }

    public String getScopes() {
        return this.successResult != null ? this.successResult.scopes : null;
    }

    public String getState() {
        return this.successResult != null ? this.successResult.state : null;
    }

    public String getTokenType() {
        return this.successResult != null ? this.successResult.tokenType : null;
    }

    public boolean hasError() {
        return this.errorResult != null;
    }

    public String toString() {
        if (this.successResult != null) {
            return this.successResult.toString();
        }
        if (this.errorResult != null) {
            return this.errorResult.toString();
        }
        throw new IllegalStateException("should not be here.");
    }
}
