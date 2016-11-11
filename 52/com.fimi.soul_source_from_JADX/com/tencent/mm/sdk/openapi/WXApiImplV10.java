package com.tencent.mm.sdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import com.tencent.mm.algorithm.MD5;
import com.tencent.mm.sdk.Build;
import com.tencent.mm.sdk.MMSharedPreferences;
import com.tencent.mm.sdk.channel.ConstantsMMessage;
import com.tencent.mm.sdk.channel.MMessage;
import com.tencent.mm.sdk.channel.MMessageAct;
import com.tencent.mm.sdk.openapi.GetMessageFromWX.Req;
import com.tencent.mm.sdk.openapi.SendAuth.Resp;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

final class WXApiImplV10 implements IWXAPI {
    private Context f11796R;
    private String f11797S;
    private boolean f11798T;

    protected WXApiImplV10(Context context, String str) {
        this(context, str, false);
    }

    protected WXApiImplV10(Context context, String str, boolean z) {
        this.f11798T = false;
        this.f11796R = context;
        this.f11797S = str;
        this.f11798T = z;
    }

    private boolean m13528a(String str) {
        if (this.f11798T) {
            try {
                return m13530a(this.f11796R.getPackageManager().getPackageInfo(str, 64).signatures);
            } catch (NameNotFoundException e) {
                return false;
            }
        }
        Log.m13539d("MicroMsg.SDK.WXApiImplV10", "ignore wechat app signature validation");
        return true;
    }

    private static boolean m13529a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, invalid arguments");
            return false;
        } else if (bArr.length != bArr2.length) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, length is different");
            return false;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean m13530a(Signature[] signatureArr) {
        if (this.f11798T) {
            for (Signature toCharsString : signatureArr) {
                String toCharsString2 = toCharsString.toCharsString();
                Log.m13539d("MicroMsg.SDK.WXApiImplV10", "check signature:" + toCharsString2);
                if (toCharsString2.equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    Log.m13539d("MicroMsg.SDK.WXApiImplV10", "pass");
                    return true;
                }
            }
            return false;
        }
        Log.m13539d("MicroMsg.SDK.WXApiImplV10", "ignore wechat app signature validation");
        return true;
    }

    public final int getWXAppSupportAPI() {
        if (isWXAppInstalled()) {
            return new MMSharedPreferences(this.f11796R).getInt("_build_info_sdk_int_", 0);
        }
        Log.m13541e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
        return 0;
    }

    public final boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        boolean z;
        if (intent == null) {
            z = false;
        } else {
            String stringExtra = intent.getStringExtra(ConstantsAPI.WX_TOKEN_KEY);
            z = stringExtra != null && stringExtra.equals(ConstantsAPI.WX_TOKEN_VALUE);
        }
        if (!z) {
            return false;
        }
        stringExtra = intent.getStringExtra(ConstantsMMessage.CONTENT);
        int intExtra = intent.getIntExtra(ConstantsMMessage.SDK_VERSION, 0);
        String stringExtra2 = intent.getStringExtra(ConstantsMMessage.APP_PACKAGE);
        if (stringExtra2 == null || stringExtra2.length() == 0) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "invalid argument");
            return false;
        }
        byte[] byteArrayExtra = intent.getByteArrayExtra(ConstantsMMessage.CHECK_SUM);
        StringBuffer stringBuffer = new StringBuffer();
        if (stringExtra != null) {
            stringBuffer.append(stringExtra);
        }
        stringBuffer.append(intExtra);
        stringBuffer.append(stringExtra2);
        stringBuffer.append("mMcShCsTr");
        if (m13529a(byteArrayExtra, MD5.getMessageDigest(stringBuffer.toString().substring(1, 9).getBytes()).getBytes())) {
            switch (intent.getIntExtra("_wxapi_command_type", 0)) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    iWXAPIEventHandler.onResp(new Resp(intent.getExtras()));
                    return true;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    iWXAPIEventHandler.onResp(new SendMessageToWX.Resp(intent.getExtras()));
                    return true;
                case Type.BYTE /*3*/:
                    iWXAPIEventHandler.onReq(new Req(intent.getExtras()));
                    return true;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    iWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(intent.getExtras()));
                    return true;
                default:
                    return false;
            }
        }
        Log.m13541e("MicroMsg.SDK.WXApiImplV10", "checksum fail");
        return false;
    }

    public final boolean isWXAppInstalled() {
        boolean z = false;
        try {
            PackageInfo packageInfo = this.f11796R.getPackageManager().getPackageInfo(PluginIntent.APP_PACKAGE_PATTERN, 64);
            if (packageInfo != null) {
                z = m13530a(packageInfo.signatures);
            }
        } catch (NameNotFoundException e) {
        }
        return z;
    }

    public final boolean isWXAppSupportAPI() {
        return getWXAppSupportAPI() >= Build.SDK_INT;
    }

    public final boolean openWXApp() {
        if (isWXAppInstalled()) {
            try {
                this.f11796R.startActivity(this.f11796R.getPackageManager().getLaunchIntentForPackage(PluginIntent.APP_PACKAGE_PATTERN));
                return true;
            } catch (Exception e) {
                Log.m13541e("MicroMsg.SDK.WXApiImplV10", "startActivity fail, exception = " + e.getMessage());
                return false;
            }
        }
        Log.m13541e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
        return false;
    }

    public final boolean registerApp(String str) {
        if (m13528a(PluginIntent.APP_PACKAGE_PATTERN)) {
            if (str != null) {
                this.f11797S = str;
            }
            Log.m13539d("MicroMsg.SDK.WXApiImplV10", "register app " + this.f11796R.getPackageName());
            MMessage.send(this.f11796R, PluginIntent.APP_PACKAGE_PATTERN, ConstantsAPI.ACTION_HANDLE_APP_REGISTER, "weixin://registerapp?appid=" + this.f11797S);
            return true;
        }
        Log.m13541e("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
        return false;
    }

    public final boolean sendReq(BaseReq baseReq) {
        if (!m13528a(PluginIntent.APP_PACKAGE_PATTERN)) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "sendReq failed for wechat app signature check failed");
            return false;
        } else if (baseReq.checkArgs()) {
            Bundle bundle = new Bundle();
            baseReq.toBundle(bundle);
            return MMessageAct.sendToWx(this.f11796R, "weixin://sendreq?appid=" + this.f11797S, bundle);
        } else {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "sendReq checkArgs fail");
            return false;
        }
    }

    public final boolean sendResp(BaseResp baseResp) {
        if (!m13528a(PluginIntent.APP_PACKAGE_PATTERN)) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "sendResp failed for wechat app signature check failed");
            return false;
        } else if (baseResp.checkArgs()) {
            Bundle bundle = new Bundle();
            baseResp.toBundle(bundle);
            return MMessageAct.sendToWx(this.f11796R, "weixin://sendresp?appid=" + this.f11797S, bundle);
        } else {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "sendResp checkArgs fail");
            return false;
        }
    }

    public final void unregisterApp() {
        if (!m13528a(PluginIntent.APP_PACKAGE_PATTERN)) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
        } else if (this.f11797S == null || this.f11797S.length() == 0) {
            Log.m13541e("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
        } else {
            Log.m13539d("MicroMsg.SDK.WXApiImplV10", "unregister app " + this.f11796R.getPackageName());
            MMessage.send(this.f11796R, PluginIntent.APP_PACKAGE_PATTERN, ConstantsAPI.ACTION_HANDLE_APP_UNREGISTER, "weixin://unregisterapp?appid=" + this.f11797S);
        }
    }
}
