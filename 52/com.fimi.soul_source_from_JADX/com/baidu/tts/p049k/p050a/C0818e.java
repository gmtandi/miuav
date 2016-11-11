package com.baidu.tts.p049k.p050a;

import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.jni.EmbeddedSynthesizerEngine;
import com.baidu.tts.loopj.RequestHandle;
import com.baidu.tts.loopj.RequestParams;
import com.baidu.tts.loopj.SyncHttpClient;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0803o;
import java.util.concurrent.Callable;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.k.a.e */
public class C0818e implements Callable<ModelBags> {
    private RequestHandle f4583a;

    private StringEntity m6805b() {
        try {
            JSONObject jSONObject = new JSONObject(EmbeddedSynthesizerEngine.bdTTSGetEngineParam());
            jSONObject.put(C0794g.FUNCTION.m6741a(), "getDefaultList");
            return new StringEntity(jSONObject.toString());
        } catch (JSONException e) {
            return null;
        }
    }

    public ModelBags m6806a() {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        String a = C0803o.MODEL_SERVER.m6754a();
        HttpEntity b = m6805b();
        Object c0814a = new C0814a();
        this.f4583a = syncHttpClient.post(null, a, b, RequestParams.APPLICATION_JSON, c0814a);
        return c0814a.m6801a();
    }

    public /* synthetic */ Object call() {
        return m6806a();
    }
}
