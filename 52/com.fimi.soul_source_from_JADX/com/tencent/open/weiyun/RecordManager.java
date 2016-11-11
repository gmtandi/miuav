package com.tencent.open.weiyun;

import android.content.Context;
import android.os.Bundle;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

public class RecordManager extends BaseApi {

    /* renamed from: com.tencent.open.weiyun.RecordManager.1 */
    class C23721 implements IUiListener {
        final /* synthetic */ IUiListener f12138a;
        final /* synthetic */ RecordManager f12139b;

        C23721(RecordManager recordManager, IUiListener iUiListener) {
            this.f12139b = recordManager;
            this.f12138a = iUiListener;
        }

        public void onCancel() {
            this.f12138a.onCancel();
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.getInt("ret") == 0) {
                    this.f12138a.onComplete(C2915a.f14760f);
                } else {
                    this.f12138a.onError(new UiError(-4, jSONObject.toString(), null));
                }
            } catch (JSONException e) {
                this.f12138a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12138a.onError(uiError);
        }
    }

    /* renamed from: com.tencent.open.weiyun.RecordManager.2 */
    class C23732 implements IUiListener {
        final /* synthetic */ IUiListener f12140a;
        final /* synthetic */ RecordManager f12141b;

        C23732(RecordManager recordManager, IUiListener iUiListener) {
            this.f12141b = recordManager;
            this.f12140a = iUiListener;
        }

        public void onCancel() {
            this.f12140a.onCancel();
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.getInt("ret") == 0) {
                    this.f12140a.onComplete(C2915a.f14760f);
                } else {
                    this.f12140a.onError(new UiError(-4, jSONObject.toString(), null));
                }
            } catch (JSONException e) {
                this.f12140a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12140a.onError(uiError);
        }
    }

    /* renamed from: com.tencent.open.weiyun.RecordManager.3 */
    class C23743 implements IUiListener {
        final /* synthetic */ IUiListener f12142a;
        final /* synthetic */ RecordManager f12143b;

        C23743(RecordManager recordManager, IUiListener iUiListener) {
            this.f12143b = recordManager;
            this.f12142a = iUiListener;
        }

        public void onCancel() {
            this.f12142a.onCancel();
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.getInt("ret") == 0) {
                    this.f12142a.onComplete(Util.hexToString(jSONObject.getJSONObject(UriUtil.DATA_SCHEME).getString(SharedPref.VALUE)));
                    return;
                }
                this.f12142a.onError(new UiError(-4, jSONObject.toString(), null));
            } catch (JSONException e) {
                this.f12142a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12142a.onError(uiError);
        }
    }

    /* renamed from: com.tencent.open.weiyun.RecordManager.4 */
    class C23754 implements IUiListener {
        final /* synthetic */ IUiListener f12144a;
        final /* synthetic */ RecordManager f12145b;

        C23754(RecordManager recordManager, IUiListener iUiListener) {
            this.f12145b = recordManager;
            this.f12144a = iUiListener;
        }

        public void onCancel() {
            this.f12144a.onCancel();
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.getInt("ret") == 0) {
                    this.f12144a.onComplete(C2915a.f14760f);
                } else {
                    this.f12144a.onError(new UiError(-4, jSONObject.toString(), null));
                }
            } catch (JSONException e) {
                this.f12144a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12144a.onError(uiError);
        }
    }

    /* renamed from: com.tencent.open.weiyun.RecordManager.5 */
    class C23765 implements IUiListener {
        final /* synthetic */ IUiListener f12146a;
        final /* synthetic */ RecordManager f12147b;

        C23765(RecordManager recordManager, IUiListener iUiListener) {
            this.f12147b = recordManager;
            this.f12146a = iUiListener;
        }

        public void onCancel() {
            this.f12146a.onCancel();
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.getInt("ret") == 0) {
                    List arrayList = new ArrayList();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME);
                    if (!jSONObject2.isNull("keys")) {
                        JSONArray jSONArray = jSONObject2.getJSONArray("keys");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add(Util.hexToString(jSONArray.getJSONObject(i).getString(SharedPref.KEY)));
                        }
                    }
                    this.f12146a.onComplete(arrayList);
                    return;
                }
                this.f12146a.onError(new UiError(-4, jSONObject.toString(), null));
            } catch (JSONException e) {
                this.f12146a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12146a.onError(uiError);
        }
    }

    /* renamed from: com.tencent.open.weiyun.RecordManager.6 */
    class C23776 implements IUiListener {
        final /* synthetic */ IUiListener f12148a;
        final /* synthetic */ RecordManager f12149b;

        C23776(RecordManager recordManager, IUiListener iUiListener) {
            this.f12149b = recordManager;
            this.f12148a = iUiListener;
        }

        public void onCancel() {
            this.f12148a.onCancel();
        }

        public void onComplete(Object obj) {
            try {
                if (((JSONObject) obj).getInt("ret") == 0) {
                    this.f12148a.onComplete(Boolean.TRUE);
                } else {
                    this.f12148a.onComplete(Boolean.FALSE);
                }
            } catch (JSONException e) {
                this.f12148a.onError(new UiError(-4, e.getMessage(), null));
            }
        }

        public void onError(UiError uiError) {
            this.f12148a.onError(uiError);
        }
    }

    public RecordManager(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public RecordManager(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void checkRecord(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        IRequestListener tempRequestListener = new TempRequestListener(new C23776(this, iUiListener));
        composeCGIParams.putString(SharedPref.KEY, Util.toHexString(str));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/check_record", composeCGIParams, C2951i.f14860a, tempRequestListener);
    }

    public void createRecord(String str, String str2, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        IRequestListener tempRequestListener = new TempRequestListener(new C23721(this, iUiListener));
        composeCGIParams.putString(SharedPref.KEY, Util.toHexString(str));
        try {
            composeCGIParams.putByteArray(SharedPref.VALUE, Util.toHexString(str2).getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            C2333f.m13754b("RecordManager", "-->cr, get value of utf-8 exception.");
        }
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/create_record", composeCGIParams, C2955m.f14864a, tempRequestListener);
    }

    public void deleteRecord(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        IRequestListener tempRequestListener = new TempRequestListener(new C23732(this, iUiListener));
        composeCGIParams.putString(SharedPref.KEY, Util.toHexString(str));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/delete_record", composeCGIParams, C2951i.f14860a, tempRequestListener);
    }

    public void getRecord(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        IRequestListener tempRequestListener = new TempRequestListener(new C23743(this, iUiListener));
        composeCGIParams.putString(SharedPref.KEY, Util.toHexString(str));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/get_record", composeCGIParams, C2951i.f14860a, tempRequestListener);
    }

    public void modifyRecord(String str, String str2, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        IRequestListener tempRequestListener = new TempRequestListener(new C23754(this, iUiListener));
        composeCGIParams.putString(SharedPref.KEY, Util.toHexString(str));
        try {
            composeCGIParams.putByteArray(SharedPref.VALUE, Util.toHexString(str2).getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            C2333f.m13754b("RecordManager", "-->mr, get value of utf-8 exception.");
        }
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/modify_record", composeCGIParams, C2955m.f14864a, tempRequestListener);
    }

    public void queryAllRecord(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://graph.qq.com/weiyun/query_all_record", composeCGIParams(), C2951i.f14860a, new TempRequestListener(new C23765(this, iUiListener)));
    }
}
