package cn.sharesdk.framework;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mob.tools.utils.DeviceHelper;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.i */
public class C0154i extends WebViewClient {
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        message.sendToTarget();
    }

    public void onLoadResource(WebView webView, String str) {
    }

    public void onPageFinished(WebView webView, String str) {
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        httpAuthHandler.cancel();
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (webView.getContext() instanceof Activity) {
            String[] strArr;
            CharSequence valueOf;
            CharSequence valueOf2;
            CharSequence valueOf3;
            Activity activity = (Activity) webView.getContext();
            if ("zh".equals(DeviceHelper.getInstance(activity).getOSLanguage())) {
                strArr = new String[]{String.valueOf(new char[]{'\u4e0d', '\u53d7', '\u4fe1', '\u4efb', '\u7684', '\u8bc1', '\u4e66', '\u3002', '\u4f60', '\u8981', '\u7ee7', '\u7eed', '\u5417', '\uff1f'}), String.valueOf(new char[]{'\u8bc1', '\u4e66', '\u5df2', '\u8fc7', '\u671f', '\u3002', '\u4f60', '\u8981', '\u7ee7', '\u7eed', '\u5417', '\uff1f'}), String.valueOf(new char[]{'\u8bc1', '\u4e66', 'I', 'D', '\u4e0d', '\u5339', '\u914d', '\u3002', '\u4f60', '\u8981', '\u7ee7', '\u7eed', '\u5417', '\uff1f'}), String.valueOf(new char[]{'\u8bc1', '\u4e66', '\u5c1a', '\u672a', '\u751f', '\u6548', '\u3002', '\u4f60', '\u8981', '\u7ee7', '\u7eed', '\u5417', '\uff1f'}), String.valueOf(new char[]{'\u8bc1', '\u4e66', '\u9519', '\u8bef', '\u3002', '\u4f60', '\u8981', '\u7ee7', '\u7eed', '\u5417', '\uff1f'})};
                valueOf = String.valueOf(new char[]{'\u8bc1', '\u4e66', '\u9519', '\u8bef'});
                valueOf2 = String.valueOf(new char[]{'\u7ee7', '\u7eed'});
                valueOf3 = String.valueOf(new char[]{'\u505c', '\u6b62'});
            } else {
                strArr = new String[]{"Certificate is untrusted. Do you want to continue anyway?", "Certificate has expired. Do you want to continue anyway?", "Certificate ID is mismatched. Do you want to continue anyway?", "Certificate is not yet valid. Do you want to continue anyway?", "Certificate error. Do you want to continue anyway?"};
                valueOf = "SSL Certificate Error";
                valueOf2 = "Yes";
                valueOf3 = "No";
            }
            Builder builder = new Builder(activity);
            builder.setTitle(valueOf);
            switch (sslError.getPrimaryError()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    builder.setMessage(strArr[3]);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    builder.setMessage(strArr[1]);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    builder.setMessage(strArr[2]);
                    break;
                case Type.BYTE /*3*/:
                    builder.setMessage(strArr[0]);
                    break;
                default:
                    builder.setMessage(strArr[4]);
                    break;
            }
            builder.setCancelable(false);
            builder.setPositiveButton(valueOf2, new C0189j(this, sslErrorHandler));
            builder.setNegativeButton(valueOf3, new C0190k(this, sslErrorHandler));
            builder.create().show();
            return;
        }
        sslErrorHandler.cancel();
    }

    public void onScaleChanged(WebView webView, float f, float f2) {
    }

    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        message.sendToTarget();
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }
}
