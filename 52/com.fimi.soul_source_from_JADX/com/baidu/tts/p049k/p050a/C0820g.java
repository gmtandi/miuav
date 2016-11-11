package com.baidu.tts.p049k.p050a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.loopj.RequestHandle;
import com.baidu.tts.loopj.RequestParams;
import com.baidu.tts.loopj.SyncHttpClient;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0803o;
import com.tencent.connect.common.Constants;
import java.util.concurrent.Callable;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.k.a.g */
public class C0820g implements Callable<ModelBags> {
    private Conditions f4586a;
    private RequestHandle f4587b;

    public C0820g(Conditions conditions) {
        this.f4586a = conditions;
    }

    private StringEntity m6809b() {
        JSONObject jSONConditions = this.f4586a.getJSONConditions();
        try {
            jSONConditions.put(C0794g.IVERSION.m6742b(), Constants.VIA_TO_TYPE_QQ_GROUP);
            jSONConditions.put(C0794g.FUNCTION.m6741a(), "getList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject = jSONConditions.toString();
        LoggerProxy.m6515d("GetServerModelsWork", "getlist params=" + jSONObject);
        return new StringEntity(jSONObject);
    }

    public ModelBags m6810a() {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        String a = C0803o.MODEL_SERVER.m6754a();
        HttpEntity b = m6809b();
        Object c0814a = new C0814a();
        this.f4587b = syncHttpClient.post(null, a, b, RequestParams.APPLICATION_JSON, c0814a);
        return c0814a.m6801a();
    }

    public /* synthetic */ Object call() {
        return m6810a();
    }
}
